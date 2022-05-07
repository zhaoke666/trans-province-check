package com.cqhg.ensure.contoller;

import com.alibaba.fastjson.JSON;
import com.cqhg.ensure.util.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class FeedbackCheckResult {

    @PostMapping("/feedbackCheckResult")
    public Map feedbackCheckResult(String cityCode,String batchNo){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData md= null;
        String secretKey = null;//密钥
        secretKey="C1B7908EB200435EE0543863BB4C8EE2";
        if(StringUtil.isBlank(cityCode) || StringUtil.isBlank(batchNo)){
            return JsonResultUtils.fail("参数不能为空");
        }else{
            try {
                conn = ConnUtil.getconn();
                conn.setAutoCommit(false);
                stmt = conn.createStatement();

                    String whereSql = " AND city_code = '"+cityCode+"' AND BATCH_NO = '"+batchNo+"'";

                //公安户籍信息
                String a05Sql = "SELECT D204A050007,D204A050008,D204A050015,D204A050016 FROM TPD204A05 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listA05 = this.getSqlResult(stmt,a05Sql);

                //投资人信息
                String b08Sql = "SELECT D204B080001,D204B080002,D204B080004,D204B080007,D204B080009 FROM TPD204B08 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listB08 = this.getSqlResult(stmt,b08Sql);

                //企业基本信息
                String b09Sql = "SELECT D204B090001,D204B090002,D204B090003,D204B090004,D204B090005,D204B090006,D204B090008 FROM TPD204B09 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listB09 = this.getSqlResult(stmt,b09Sql);

                //临时救助信息
                String b15Sql = "SELECT D204B150001,D204B150002,D204B150003,D204B150004 FROM TPD204B15 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listB15 = this.getSqlResult(stmt,b15Sql);

                //低保救助信息
                String b16Sql = "SELECT D204B160001,D204B160002,D204B160003,D204B160004 FROM TPD204B16 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listB16 = this.getSqlResult(stmt,b16Sql);

                //特困救助信息
                String b17Sql = "SELECT D204B170001,D204B170002,D204B170003,D204B170004 FROM TPD204B17 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listB17 = this.getSqlResult(stmt,b17Sql);

                //参保人员信息
                String c06Sql = "SELECT D204C060001,D204C060002,D204C060003,D204C060004,D204C060005,D204C060006,D204C060007,D204C060009,D204C060011,D204C060013 FROM TPD204C06 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listC06 = this.getSqlResult(stmt,c06Sql);

                //领取失业金人员信息
                String c07Sql = "SELECT D204C070001,D204C070002,D204C070003,D204C070004,D204C070005,D204C070006,D204C070007 FROM TPD204C07 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listC07 = this.getSqlResult(stmt,c07Sql);
    
                //领取工伤保险待遇人员信息
                String c08Sql = "SELECT D204C080001,D204C080002,D204C080003,D204C080004,D204C080005,D204C080006,D204C080007,D204C080008 FROM TPD204C08 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listC08 = this.getSqlResult(stmt,c08Sql);

                //领取养老保险待遇人员信息
                String c09Sql = "SELECT D204C090001,D204C090002,D204C090003,D204C090004,D204C090005,D204C090006,D204C090007,D204C090008,D204C090009 FROM TPD204C09 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listC09 = this.getSqlResult(stmt,c09Sql);

                //车辆信息
                String c21Sql = "SELECT D204C210001,D204C210002,D204C210003,D204C210004,D204C210005,D204C210006,D204C210007,D204C210008,D204C210009,D204C210010,D204C210011,D204C210012,D204C210013,D204C210014,D204C210015 FROM TPD204C21 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listC21 = this.getSqlResult(stmt,c21Sql);

                //房产登记
                String c22Sql = "SELECT D204C220002,D204C220004,D204C220005,D204C220007,D204C220008,D204C220010,D204C220011 FROM TPD204C22 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listC22 = this.getSqlResult(stmt,c22Sql);

                //口岸出入境信息
                String c31Sql = "SELECT D204C310001,D204C310002,D204C310004,D204C310005,D204C310007,D204C310009,D204C310010 FROM TPD204C31 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listC31 = this.getSqlResult(stmt,c31Sql);

                //公积金缴纳情况
                String d34Sql = "SELECT D204D340001,D204D340002,D204D340003,D204D340004,D204D340005,D204D340006,D204D340007,D204D340008,D204D340009,D204D340010,D204D340011,D204D340012,D204D340013,D204D340014,D204D340015,D204D340016,D204D340017,D204D340018,D204D340019,D204D340020,D204D340021,D204D340022,D204D340023,D204D340024,D204D340025,D204D340026,D204D340027 FROM TPD204D34 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listD34 = this.getSqlResult(stmt,d34Sql);

                //税务-企业/个体税务登记
                String d40Sql = "SELECT D204D400004,D204D400005,D204D400006,D204D400007,D204D400008,D204D400009,D204D400010 FROM TPD204D40 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listD40 = this.getSqlResult(stmt,d40Sql);

                //税务-企业/个体涉税
                String d41Sql = "SELECT D204D410004,D204D410005,D204D410006,D204D410007,D204D410008,D204D410012,D204D410015 FROM TPD204D41 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listD41 = this.getSqlResult(stmt,d41Sql);

                //税务-个人涉税费
                String d42Sql = "SELECT D204D420001,D204D420002,D204D420003,D204D420004,D204D420006,D204D420007,D204D420009,D204D420010 FROM TPD204D42 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listD42 = this.getSqlResult(stmt,d42Sql);

                //税务-契税
                String d44Sql = "SELECT D204D440001,D204D440003,D204D440004,D204D440005,D204D440006,D204D440007,D204D440008,D204D440009 FROM TPD204D44 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listD44 = this.getSqlResult(stmt,d44Sql);

                //公积金个人贷款信息
                String d45Sql = "SELECT D204D450001,D204D450002,D204D450003,D204D450004,D204D450009,D204D450010 FROM TPD204D45 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listD45 = this.getSqlResult(stmt,d45Sql);

                //医疗保险参保情况
                String d46Sql = "SELECT D204D460001,D204D460002,D204D460003,D204D460004 FROM TPD204D46 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listD46 = this.getSqlResult(stmt,d46Sql);

                //医疗保险待遇信息
                String d47Sql = "SELECT D204D470001,D204D470002,D204D470003,D204D470004,D204D470005,D204D470006 FROM TPD204D47 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listD47 = this.getSqlResult(stmt,d47Sql);

                //医疗保险领取信息
                String d48Sql = "SELECT D204D480001,D204D480002,D204D480003,D204D480004,D204D480005,D204D480006,D204D480007,D204D480008 FROM TPD204D48 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listD48 = this.getSqlResult(stmt,d48Sql);

                //工会组织会员信息
                String z07Sql = "SELECT D204Z070001,D204Z070002,D204Z070003,D204Z070004,D204Z070005,D204Z070006,D204Z070007 FROM TPD204Z07 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listZ07 = this.getSqlResult(stmt,z07Sql);

                //婚姻登记信息
                String z63Sql = "SELECT D204Z630001,D204Z630003,D204Z630004,D204Z630005,D204Z630006,D204Z630007,D204Z630008,D204Z630010 FROM TPD204Z63 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listZ63 = this.getSqlResult(stmt,z63Sql);

                //工商信息
                String z74Sql = "SELECT D204Z740001,D204Z740002,D204Z740003,D204Z740004,D204Z740005,D204Z740006,D204Z740008,D204Z740010,D204Z740011,D204Z740012,D204Z740013,D204Z740014,D204Z740015,D204Z740016,D204Z740017,D204Z740018,D204Z740019 FROM TPD204Z74 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listZ74 = this.getSqlResult(stmt,z74Sql);

                //高院判决婚姻信息
                String z75Sql = "SELECT D204Z750001,D204Z750002,D204Z750003,D204Z750004,D204Z750005,D204Z750006 FROM TPD204Z75 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listZ75 = this.getSqlResult(stmt,z75Sql);

                //计财殡葬信息
                String z76Sql = "SELECT D204Z760001,D204Z760002,D204Z760003,D204Z760004 FROM TPD204Z76 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listZ76 = this.getSqlResult(stmt,z76Sql);

                //卫计委死亡信息
                String z77Sql = "SELECT D204Z770001,D204Z770002,D204Z770003,D204Z770004 FROM TPD204Z77 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listZ77 = this.getSqlResult(stmt,z77Sql);

                //卫计委人口信息
                String z78Sql = "SELECT D204Z780001,D204Z780002,D204Z780003,D204Z780004,D204Z780005 FROM TPD204Z78 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listZ78 = this.getSqlResult(stmt,z78Sql);

                //社会组织民非信息
                String z79Sql = "SELECT D204Z790001,D204Z790002,D204Z790003,D204Z790004,D204Z790005 FROM TPD204Z79 WHERE 1=1 "+whereSql;
                List<Map<String, Object>> listZ79 = this.getSqlResult(stmt,z79Sql);

                Map<String,Object> rtnMap = new HashMap<>();
                rtnMap.put("code","200");
                rtnMap.put("message","成功");
                rtnMap.put("cityCode",cityCode);
                rtnMap.put("batchNo",batchNo);
                Map<String,Object> dataMap = new HashMap<>();
                dataMap.put("D204A05",listA05);
                dataMap.put("D204B08",listB08);
                dataMap.put("D204B09",listB09);
                dataMap.put("D204B15",listB15);
                dataMap.put("D204B16",listB16);
                dataMap.put("D204B17",listB17);
                dataMap.put("D204C06",listC06);
                dataMap.put("D204C07",listC07);
                dataMap.put("D204C08",listC08);
                dataMap.put("D204C09",listC09);
                dataMap.put("D204C21",listC21);
                dataMap.put("D204C22",listC22);
                dataMap.put("D204C31",listC31);
                dataMap.put("D204D34",listD34);
                dataMap.put("D204D40",listD40);
                dataMap.put("D204D41",listD41);
                dataMap.put("D204D42",listD42);
                dataMap.put("D204D44",listD44);
                dataMap.put("D204D45",listD45);
                dataMap.put("D204D46",listD46);
                dataMap.put("D204D47",listD47);
                dataMap.put("D204D48",listD48);
                dataMap.put("D204Z07",listZ07);
                dataMap.put("D204Z63",listZ63);
                dataMap.put("D204Z74",listZ74);
                dataMap.put("D204Z75",listZ75);
                dataMap.put("D204Z76",listZ76);
                dataMap.put("D204Z77",listZ77);
                dataMap.put("D204Z78",listZ78);
                dataMap.put("D204Z79",listZ79);


                System.out.println("DataMap:"+dataMap.toString());
                String bb = Sm4Util.encryptEcb(secretKey,JSON.toJSONString(dataMap));
                System.out.println("bb:"+bb);
                rtnMap.put("data",bb);

                return rtnMap;
            } catch (Exception e) {
                if(conn!=null){
                    try {
                        conn.rollback(); //设定setAutoCommit(false)时，需要防止死锁现象
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
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
                return JsonResultUtils.fail("失败："+e.getMessage());
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
    }


    public List<Map<String, Object>> getSqlResult(Statement stmt,String sql) throws SQLException {
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
