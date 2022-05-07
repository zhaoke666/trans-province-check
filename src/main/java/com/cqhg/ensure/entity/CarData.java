package com.cqhg.ensure.entity;

import lombok.Data;

/**
 * 车辆信息
 */
@Data
public class CarData {
    //姓名
    private String PersonName;
    //证件号码
    private String CardNo;
    //批次号
    private String MR_BatchNo;
    //车辆用途
    private String UseProperty;
    //数据反馈截止时间
    private String FeedEndTime;
    //地区编码
    private String AREA_CODE;
    //发证机构
    private String FZJG;
    //车牌号
    private String CarNo;
    //
    private String CarID;
    //车辆类型
    private String CarType;
    //车辆品牌
    private String CarBrand;
    //车辆所有人
    private String OwnerCar;
    //导入ID
    private String IMPORT_ID;

    public CarData() {
    }

    public CarData(String personName, String cardNo, String MR_BatchNo, String useProperty, String feedEndTime, String AREA_CODE, String FZJG, String carNo, String carID, String carType, String carBrand, String ownerCar, String IMPORT_ID) {
        PersonName = personName;
        CardNo = cardNo;
        this.MR_BatchNo = MR_BatchNo;
        UseProperty = useProperty;
        FeedEndTime = feedEndTime;
        this.AREA_CODE = AREA_CODE;
        this.FZJG = FZJG;
        CarNo = carNo;
        CarID = carID;
        CarType = carType;
        CarBrand = carBrand;
        OwnerCar = ownerCar;
        this.IMPORT_ID = IMPORT_ID;
    }
}
