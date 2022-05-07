package com.cqhg.ensure.contoller;

import com.alibaba.fastjson.JSON;
import com.cqhg.ensure.util.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CommonBackCheck {

    @RequestMapping("feedbackCheckResult1")
    public void feedbackCheckResult(String address,String cityCode){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData md= null;

        String secretKey = null;//密钥
        String checkItem = null;
        String batchNo = null;
        Map<String,String> rtnMap = new HashMap<>();
        try {
            conn = ConnUtil.getconn();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            //查询待反馈的批次
            String backSql = "select t.CITY_CODE,t.CHECK_ITEM,t.BATCH_NO,tcc.SECRET_KEY from tp_ensure_people t " +
                    "LEFT JOIN TP_CITY_CONFIG tcc ON t.CITY_CODE = tcc.city_code " +
                    "where t.back_state = '0' AND t.CHECK_STATE = '1' AND tcc.zt= '1'";
            if(StringUtil.isNotBlank(cityCode)){
                backSql += " AND t.CITY_CODE = '"+cityCode+"'";
            }
            List<Map<String, Object>> listBack =  this.getSqlResult(stmt,backSql);


            if(listBack.size() > 0){
                for (Map<String, Object> backMap: listBack) {
                    //获取区划代码和批次号
                    cityCode = backMap.get("CITY_CODE").toString();
                    batchNo = backMap.get("BATCH_NO").toString();
                    checkItem = backMap.get("CHECK_ITEM").toString();
                    secretKey = backMap.get("SECRET_KEY").toString();

                    rtnMap.put("cityCode",cityCode);
                    rtnMap.put("batchNo",batchNo);

                    //根据表名查询字段
                    String columnSql = " select wm_concat(column_id) COLUMN_IDS,TABLE_CODE from ( " +
                            "                                select table_code,column_id from tp_ensure_trans_meta_source t " +
                            "                                where t.table_code in " +
                            "                                        (SELECT column_value as TABLE_CODE from table(bas_comm_pkg.split('"+checkItem+"',','))) " +
                            "                                group by table_code,column_id " +
                            "                                ORDER BY table_code,column_id " +
                            "                        ) GROUP BY table_code";
                    List<Map<String, Object>> itemColumnList =  this.getSqlResult(stmt,columnSql);

                    //查询每个核查项的具体信息
                    String itemSql = null;
                    String whereSql = " AND city_code = '"+cityCode+"' AND BATCH_NO = '"+batchNo+"'";//where条件
                    Map<String,Object> dataMap = new HashMap<>();
                    for (Map<String, Object> itemColumn: itemColumnList) {
                        itemSql = "SELECT ";
                        itemSql = itemSql + itemColumn.get("COLUMN_IDS").toString() + " FROM TP" + itemColumn.get("TABLE_CODE").toString() + " WHERE 1=1 " + whereSql;
                        List<Map<String, Object>> listItem = this.getSqlResult(stmt,itemSql);
                        dataMap.put(itemColumn.get("TABLE_CODE").toString(),listItem);
                    }

                    //加密
                    rtnMap.put("data",Sm4Util.encryptEcb(secretKey, JSON.toJSONString(dataMap)));
                    rtnMap.put("code","200");
                    rtnMap.put("message","成功");

                    //post请求URL地址
                    String postResult = HTTPSUtil.post(address,rtnMap,null);

                    //修改已反馈状态
                    String updateResultSql = "UPDATE TP_ENSURE_PEOPLE set BACK_STATE = '1',BACK_RESLUT = '"+postResult+"' where 1=1 "+whereSql;
                    stmt.addBatch(updateResultSql);
                    stmt.executeBatch();
                    conn.commit();
                }
            }


        } catch (Exception e) {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException exception) {
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException exception) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException exception) {
            }
            //return JsonResultUtils.fail("失败："+e.getMessage());
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException exception) {
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException exception) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException exception) {
            }
        }

    }


    public List<Map<String, Object>> getSqlResult(Statement stmt, String sql) throws SQLException {
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据
        int columnCount = md.getColumnCount();   //获得列数
        List<Map<String, Object>> list = new ArrayList<>();
        while (rs.next()) {
            Map<String,Object> rowData = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(rowData);
        }
        return list;
    }
}
