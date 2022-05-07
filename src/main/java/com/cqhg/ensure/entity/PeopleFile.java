package com.cqhg.ensure.entity;


import lombok.Data;

@Data
public class PeopleFile {

    private String fileType;  //文件类型   01  身份证  02 授权书  03 委托书

    private String fileName;  //文件名称

    private String peopleId;

    private byte[] fileData;

    private String type;// 文件类型，jgp 或者是pdf

}
