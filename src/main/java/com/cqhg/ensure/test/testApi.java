package com.cqhg.ensure.test;


import com.cqhg.ensure.util.SiChuanUtil;

import java.io.IOException;

/**
 * 调用demo
 */
public class testApi {

    /**
     * 返回编码说明
     * 200(调用正常)
     * 500(服务器内部错误)
     * 405(调用了不存在的方法,或没有权限访问该方法)
     * 400(请求参数非法,参数缺少或者未通过验证)
     * 403(权限校验错误,错误的apiKey或digest)
     * 404(请求数据不存在,通常为未在数据库中查找到请求的实体)
     * 407(请求超时，操作或传输延迟)
     * 408(签名超时，在一段时间内，用同一个时间戳，同样的内容请求)
     * 409(IP被拒绝访问)
     */

    /**
     * 每个接口的 测试参数
     */
    //车辆信息 getCar   徐永忠   510823198502202572
    //户籍信息 getHuJiDataByCardNo  黄井红 511621201310186952
    //婚姻登记信息 getMarryDataByCardNo   胡川 511621198508206950
    //出租汽车运输从业人员基本信息 getTaxiTransportation    杨波 51162119880905695X
    //道路货物运输从业人员基本信息 getCargoTransportation   丁万安   510822196908266415
    //道路旅客运输从业人员基本信息 getTrafficTransit   蒋小平 513621196209200017
    //离婚登记信息 getDivorceDataByCardNo   彭世秀 510322198907125508
    //民政对象信息 getLowinfoDataByCardNo  祝长春   654122197108022712
    //城镇职工机关养老保险状态信息 getTownWorkerInsuranceByCardNo    喻小林 510522199308143742
    public static void main(String[] args) {
        String idCard = "510522199308143742";
        String peopleName = "喻小林";
        String urlType = "getTownWorkerInsuranceByCardNo";



        try {
            String url = SiChuanUtil.getSiChuanUrl(idCard,peopleName,urlType);
            System.out.println(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
        List<String> strings = new ArrayList<>();
        strings.add("getCar");
        strings.add("getHuJiDataByCardNo");
        strings.add("getMarryDataByCardNo");
        strings.add("getTaxiTransportation");
        strings.add("getCargoTransportation");
        strings.add("getTrafficTransit");
        strings.add("getDivorceDataByCardNo");
        strings.add("getLowinfoDataByCardNo");
        strings.add("getTownWorkerInsuranceByCardNo");
        for (int i=0;i<strings.size();i++){
            try {
                String url = SiChuanUtil.getSiChuanUrl(idCard,peopleName,urlType);
                System.out.println(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
         */
        //车辆信息 getCar
        //户籍信息 getHuJiDataByCardNo
        //婚姻登记信息 getMarryDataByCardNo
        //出租汽车运输从业人员基本信息 getTaxiTransportation
        //道路货物运输从业人员基本信息 getCargoTransportation
        //道路旅客运输从业人员基本信息 getTrafficTransit
        //离婚登记信息 getDivorceDataByCardNo
        //民政对象信息 getLowinfoDataByCardNo
        //城镇职工机关养老保险状态信息 getTownWorkerInsuranceByCardNo

    }
}