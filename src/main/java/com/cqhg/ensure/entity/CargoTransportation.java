package com.cqhg.ensure.entity;

import lombok.Data;

/**
 * 道路货物运输从业人员基本信
 */
@Data
public class CargoTransportation {
    //姓名
    private String XM;
    //证件号码
    private String CYZGZH;
    //性别
    private String XB;
    //所属地区
    private String SSDQ;

    public CargoTransportation() {
    }

    public CargoTransportation(String XM, String CYZGZH, String XB, String SSDQ) {
        this.XM = XM;
        this.CYZGZH = CYZGZH;
        this.XB = XB;
        this.SSDQ = SSDQ;
    }
}
