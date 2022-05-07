package com.cqhg.ensure.task;

import com.alibaba.fastjson.JSON;
import com.cqhg.ensure.entity.CarData;
import com.cqhg.ensure.entity.LowinfoData;
import com.cqhg.ensure.service.CarDataService;
import com.cqhg.ensure.service.LowinfoDataService;
import com.cqhg.ensure.util.SiChuanUtil;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * 四川跨省核查结果反馈
 */

@Component
public class SiChuanBackResult {

    @Autowired
    private LowinfoDataService lowinfoDataService;

    @Autowired
    private CarDataService carDataService;



    //@Scheduled(cron = "0/5 * * * * ?")
    public void backResult(){
        System.out.println("开始执行.....");
    }


    //民政对象信息
    public void getLowinfoDataByCardNo(){
        //获取待核查的姓名身份证号（待实现）
        String cardNo = "";
        String personName = "";
        String urlType = "";


        try {
            //根据urlType访问接口 拿到返回json
            net.sf.json.JSONObject json = SiChuanUtil.getSiChuanJson(cardNo,personName,urlType);
            String errorCode = json.getString("code");
            String dataCount = json.getString("socialCode");
            //JSONObject dataListJson = JSONObject.parseObject(json.getString("dataList"));

            if(errorCode != null && "200".equals(errorCode)){//成功
                List<LowinfoData> lowinfoDataList =null;
                if(json.get("dataList") != null) {
                     lowinfoDataList = JSON.parseArray(json.getString("dataList"), LowinfoData.class);

                    String peopleId = "待修改commissionId";
                    String commissionId = "待修改commissionId";

                    //保存到数据库
                    lowinfoDataService.addLowinfoDataList(lowinfoDataList,peopleId,commissionId);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 示例
     */
    public void exampTest(){
        //获取待核查的姓名身份证号（这些字段需要赋值）
        String cardNo = "";    //姓名(必须赋值)
        String personName = "";//身份证(必须赋值)
        String urlType = "getCar";   //指定访问哪个接口(必须赋值)

        String peopleId = "";  //人员的ID(可选)
        String commissionId = ""; //commissionId(可选)

        try {
            //根据urlType访问接口 拿到返回json
            net.sf.json.JSONObject json = SiChuanUtil.getSiChuanJson(cardNo,personName,urlType);
            String errorCode = json.getString("code");
            String dataCount = json.getString("socialCode");
            //code为200时才进行保存
            if(errorCode != null && "200".equals(errorCode)){//成功
                List<CarData> dataList =null;
                if(json.get("dataList") != null) {
                    dataList = JSON.parseArray(json.getString("dataList"), CarData.class);
                    if(dataList != null && dataList.size() > 0){
                        //保存到数据库
                        carDataService.addList(dataList,peopleId,commissionId);
                    }
                }
            }else{
                System.out.println("输出错误信息..."+errorCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String cardNo = "511522198504271713";
        String personName = "颜泽彬";
        String urlType = "getCar";

        try {
            //根据urlType访问接口 拿到返回json
            net.sf.json.JSONObject json = SiChuanUtil.getSiChuanJson(cardNo,personName,urlType);
            String errorCode = json.getString("code");
            String dataCount = json.getString("socialCode");
            //JSONObject dataListJson = JSONObject.parseObject(json.getString("dataList"));

            if(errorCode != null && "200".equals(errorCode)){//成功
                List<LowinfoData> lowinfoDataList =null;
                if(json.get("dataList") != null) {
                    lowinfoDataList = JSON.parseArray(json.getString("dataList"), LowinfoData.class);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
