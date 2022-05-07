package com.cqhg.ensure.entity;

import lombok.Data;

/**
 * 城镇职工机关养老保险状态信息
 */
@Data
public class TownWorkerInsurance {

    //参保日期
    private String aac030;
    //缴费状态
    private String aac031;
    //证件号码
    private String aac002;
    //参保单位名称
    private String aab004;
    //行政区划代码
    private String aab301;
    //姓名
    private String aac003;
    //缴费基数
    private String aae180;

    public TownWorkerInsurance() {
    }

    public TownWorkerInsurance(String aac030, String aac031, String aac002, String aab004, String aab301, String aac003, String aae180) {
        this.aac030 = aac030;
        this.aac031 = aac031;
        this.aac002 = aac002;
        this.aab004 = aab004;
        this.aab301 = aab301;
        this.aac003 = aac003;
        this.aae180 = aae180;
    }
}
