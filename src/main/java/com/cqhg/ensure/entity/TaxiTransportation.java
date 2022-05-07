package com.cqhg.ensure.entity;

import lombok.Data;

/**
 * 出租汽车运输从业人员基本信息
 */
@Data
public class TaxiTransportation {
    //姓名
    private String XM;
    //证件号码
    private String CYZGZH;
    //性别
    private String XB;
    //所属地区
    private String SSDQ;

    public TaxiTransportation() {
    }

    public TaxiTransportation(String XM, String CYZGZH, String XB, String SSDQ) {
        this.XM = XM;
        this.CYZGZH = CYZGZH;
        this.XB = XB;
        this.SSDQ = SSDQ;
    }
}
