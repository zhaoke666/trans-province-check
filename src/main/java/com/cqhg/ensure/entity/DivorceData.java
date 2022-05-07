package com.cqhg.ensure.entity;

import lombok.Data;

/**
 * 离婚登记信息
 */
@Data
public class DivorceData {
    //姓名
    private String PersonName;
    //录入机构名称
    private String InputDeptName;
    //文化程度
    private String Degree;
    //离婚登记日期
    private String divorceRegistrationDate;
    //录入机构编码
    private String InputDeptCode;
    //性别
    private String Sex;
    //民族
    private String Folk;
    //配偶文化程度
    private String SpouseDegree;
    //配偶证件号码
    private String SpouseDocumentNo;
    //配偶姓名
    private String SpouseName;
    //国籍
    private String Nationality;
    //配偶国籍
    private String SpouseNationality;
    //离婚登记号
    private String DivorceNO;
    //证件号码
    private String CardNo;
    //配偶民族
    private String SpouseFolk;
    //证件类型
    private String CardType;
    //配偶证件类型
    private String SpouseDocumentType;

    public DivorceData() {
    }

    public DivorceData(String personName, String inputDeptName, String degree, String divorceRegistrationDate, String inputDeptCode, String sex, String folk, String spouseDegree, String spouseDocumentNo, String spouseName, String nationality, String spouseNationality, String divorceNO, String cardNo, String spouseFolk, String cardType, String spouseDocumentType) {
        PersonName = personName;
        InputDeptName = inputDeptName;
        Degree = degree;
        this.divorceRegistrationDate = divorceRegistrationDate;
        InputDeptCode = inputDeptCode;
        Sex = sex;
        Folk = folk;
        SpouseDegree = spouseDegree;
        SpouseDocumentNo = spouseDocumentNo;
        SpouseName = spouseName;
        Nationality = nationality;
        SpouseNationality = spouseNationality;
        DivorceNO = divorceNO;
        CardNo = cardNo;
        SpouseFolk = spouseFolk;
        CardType = cardType;
        SpouseDocumentType = spouseDocumentType;
    }
}
