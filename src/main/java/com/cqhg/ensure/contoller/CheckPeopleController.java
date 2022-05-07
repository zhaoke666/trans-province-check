package com.cqhg.ensure.contoller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cqhg.ensure.entity.CheckPeopleMsg;
import com.cqhg.ensure.entity.PeopleFile;
import com.cqhg.ensure.util.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.*;


@RestController
public class CheckPeopleController {


    private final static String  cityCode = "510000";



    @RequestMapping(value="/postCheckPeopleMsg", method= RequestMethod.POST )
    @ResponseBody
    public Map postCheckPeopleMsg(HttpServletRequest request, HttpServletResponse respons){

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

      try {
          Map<String ,Object> mapObj = getApplyCyAndFamily(request);
          if(mapObj.isEmpty())
              throw new Exception("查询人员信息不能为空！");
          if(mapObj.get("peopleFile") == null)
              throw new Exception("人员附件信息不能为空！");
          //获取所有相关的附件信息
          List<PeopleFile> peopleFile = (List<PeopleFile>)mapObj.get("peopleFile");
          //获取所有的成员信息
          List<CheckPeopleMsg> peoples =    (List<CheckPeopleMsg>)mapObj.get("peoples");
          //获取每个成员对应的id
          List<String> peopleIdList =    (List<String>)mapObj.get("peopleIdlist");
          String peopleIds = "";
          if(peopleIdList != null&&peopleIdList.size()>0) {
              peopleIds =  StringUtils.join(peopleIdList.toArray(),",");
          }
          List<String> sqlList = fileInteraction(request, peopleFile, peoples);
          if(sqlList==null||sqlList.isEmpty())
              throw new Exception("保存附件失败！");
          conn = ConnUtil.getconn();
          conn.setAutoCommit(false);
          stmt = conn.createStatement();
          for (String sql : sqlList) {
              stmt.addBatch(sql);
          }
          conn.commit();
          //通过所有的人员地获取对应的文件信息
          String selectPeopleSql = "select t.ANNEX_ID,t.INST_TYPE,t.INST_ID from TP_ANNEX_DATA t where t.ANNEXT_TYPE = '2039' and t.INST_ID in (" + peopleIds + ")";
          System.out.printf(selectPeopleSql);
          conn = ConnUtil.getconn();
          conn.setAutoCommit(false);
          stmt = conn.createStatement();
          rs = stmt.executeQuery(selectPeopleSql);
          //生成保存成员信息的sql
          conn = ConnUtil.getconn();
          conn.setAutoCommit(false);
          stmt = conn.createStatement();
          List<String> peopleSqlListInsert =  peopleSqlListInsert(rs,peoples);
          for (String sql: peopleSqlListInsert){
              stmt.addBatch(sql);
          }
          conn.commit();


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
        return  JsonResultUtils.success("操作成功");
    }




    private Map<String ,Object> getApplyCyAndFamily(HttpServletRequest request){
        Map<String ,Object> map = new HashMap<String,Object>();
        String poastDatas = request.getParameter("poastDatas");
        System.out.println("datas:"+poastDatas);
        JSONArray jsonArray = JSONArray.parseArray(poastDatas);
        List<String> peopleIdlist = new ArrayList<>();
        if(jsonArray!=null &&jsonArray.size()>0) {
            List<CheckPeopleMsg> list =new ArrayList<CheckPeopleMsg>();
            List<PeopleFile> peopleFileList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                CheckPeopleMsg msg = new CheckPeopleMsg();
                String peopleid = DateTimeUtils.getUUID();
                msg.setCityCode(cityCode);
                msg.setPeopleId(peopleid);
                msg.setFamilyId(json.getString("familyId"));
                msg.setPeopleName(json.getString("peopleName"));
                msg.setIdCard(json.getString("idCard"));
                msg.setCheckItem(json.getString("checkItem"));
                msg.setBatchNo(json.getString("batchNo"));
                msg.setCheckState("0");
                msg.setBackReslut("0");
                list.add(msg);
                String ss = "'" + peopleid + "'";
                peopleIdlist.add(ss);

                JSONArray files = json.getJSONArray("files");
                if(files !=null && files.size()>0){
                    for(int j =0 ; j<files.size();j++){
                        JSONObject jsonFiles = files.getJSONObject(j);
                        PeopleFile filePeople = new PeopleFile();
                        filePeople.setFileName(jsonFiles.getString("fileName"));
                        filePeople.setFileType(jsonFiles.getString("fileType"));
                        filePeople.setPeopleId(peopleid);
                        peopleFileList.add(filePeople);
                        System.out.printf(peopleFileList.toString());
                    }
                    //家庭成员附件
                    if(peopleFileList!=null && peopleFileList.size()>0)map.put("peopleFile",peopleFileList);
                }
            }
            if(list!=null && list.size()>0)map.put("peoples",list);
            if(peopleIdlist!=null && peopleIdlist.size()>0) map.put("peopleIdlist",peopleIdlist);
        }

        return map;
    }


    public List<String> fileInteraction(HttpServletRequest request, List<PeopleFile> peopleFile,
                                     List<CheckPeopleMsg> families) throws Exception {
        //获取上传文件
       /* MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
        List<MultipartFile> allfiles = multipartRequest.getFiles("files");*/
        //转换 HttpServletRequest
        MultipartHttpServletRequest mulReq=(MultipartHttpServletRequest)request;
        List<MultipartFile> allfiles = mulReq.getFiles("files");
        //获取上传的文件
        //List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("files");
        Integer filenum = 0;
        for (MultipartFile multipartFile:allfiles) {
             if(!multipartFile.isEmpty()){
                 filenum++;
                 String fileName = multipartFile.getOriginalFilename();
                 for (int i = 0; i<peopleFile.size(); i++){
                     if(fileName.equals(peopleFile.get(i).getFileName())){
                         byte[] fileStream = multipartFile.getBytes();
                         peopleFile.get(i).setFileData(fileStream);
                        String type = "";
                         fileName = fileName.toLowerCase();
                         if(fileName.endsWith(".jgp")||fileName.endsWith(".png")){
                             type = "jgp";
                         }else if(fileName.endsWith(".pdf")){
                             type = "pdf";
                         }else{
                             String [] str = fileName.split(".");
                             type = str[str.length-1];

                         }
                         peopleFile.get(i).setType(type);

                     }

                 }

             }
            //保存文件
            List<String> fileInfo = saveFileList(peopleFile);
            return fileInfo;
        }

        throw new Exception("没有解析出文件");

    }



    private List<String> saveFileList(List<PeopleFile> peopleFile) {
        String insertStart = "insert into TP_ANNEX_DATA (ANNEX_ID,ANNEX_NAME,ANNEXT_TYPE,ANNEXT_DATA,ZT,INST_ID,ANNEX_EXT,INST_TYPE,INST_TYPE_VALUE) values";
        List<String>  listSql = new ArrayList<String>();
         for (PeopleFile file: peopleFile){
            String sql = insertStart + " (sys_guid(), '" + file.getFileName() + "','"+ "2039" + "',"+ file.getFileData() + ",'1','"
                                   + file.getPeopleId() + "','" + file.getFileType() +"','" + file.getType() + "','" + "川渝附件保存')";
             listSql.add(sql);
        }
    return  listSql;
    }

    private List<String> peopleSqlListInsert(ResultSet rs, List<CheckPeopleMsg> peoples) throws SQLException {
        Map<String,String> map = new HashMap<>();
        List<String> sqlList = new ArrayList<>();
        while(rs.next()){
            String annex_id = rs.getString("ANNEX_ID");
            String inst_type = rs.getString("INST_TYPE");
            String inst_id = rs.getString("INST_ID");
            String key = inst_id+'-' + inst_type;
            map.put(key,annex_id);
        }
        String intsertStart = "insert into TP_ENSURE_PEOPLE (ENSURE_ID,BATCH_NO,FAMILY_ID,PEOPLE_NAME,ID_CARD,CHECK_ITEM,CITY_CODE,INPUT_TIME,ID_CARD_PIC,S_PAD_PIC,APPIY_ID) values";
        for (CheckPeopleMsg pe:peoples){
            String peopleId = pe.getPeopleId();
            String sfzId = map.get(peopleId+"01"); // 01  身份证
            String sqsId =  map.get(peopleId+"02"); //02 授权书
            String wtsId =  map.get(peopleId+"03");  //03 委托书
            String sql = intsertStart + "(sys_guid(), '" + pe.getBatchNo() + "','"+ pe.getFamilyId() + "','"+ pe.getPeopleName() + "','"
                            + pe.getIdCard()  + "','"+ pe.getCheckItem()  + "','"+ pe.getCityCode() + "','"+ DateTimeUtils.getSystime() + "','"
                            + "','"+  sfzId + "','"+ sqsId + "','"+ wtsId +"')";
            sqlList.add(sql);
        }
        return sqlList;
    }



}
