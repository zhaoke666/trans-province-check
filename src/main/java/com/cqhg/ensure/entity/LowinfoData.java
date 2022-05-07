package com.cqhg.ensure.entity;

import lombok.Data;

/**
 * 民政对象信息
 */
@Data
public class LowinfoData {
    //收入
    private String income;
    //姓名(new)
    private String PersonName;
    //地址
    private String address;
    //特困类别(new)
    private String ExceptionalPovertyType;
    //户主姓名
    private String HouseholderName;
    //性别编码
    private String SexCode;
    //地区编码
    private String AREA_CODE;
    //证件号码
    private String CardNo;
    //证件类型
    private String CardType;
    //电话
    private String telphone;
    //户主身份证号码
    private String HouseholderCardNo;
    //供养方式(new)
    private String GuaranteeMode;
    //救助金额
    private String salvage;
    //项目名称(new)
    private String projectName;

    public LowinfoData(String income, String personName, String address, String exceptionalPovertyType, String householderName, String sexCode, String AREA_CODE, String cardNo, String cardType, String telphone, String householderCardNo, String guaranteeMode, String salvage, String projectName) {
        this.income = income;
        PersonName = personName;
        this.address = address;
        ExceptionalPovertyType = exceptionalPovertyType;
        HouseholderName = householderName;
        SexCode = sexCode;
        this.AREA_CODE = AREA_CODE;
        CardNo = cardNo;
        CardType = cardType;
        this.telphone = telphone;
        HouseholderCardNo = householderCardNo;
        GuaranteeMode = guaranteeMode;
        this.salvage = salvage;
        this.projectName = projectName;
    }

    public LowinfoData() {
    }
}
