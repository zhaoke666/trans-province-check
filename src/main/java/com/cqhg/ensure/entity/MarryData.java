package com.cqhg.ensure.entity;

import lombok.Data;

/**
 * 婚姻登记信息
 */
@Data
public class MarryData {
    //姓名
    private String PersonName;
    //录入机构名称
    private String InputDeptName;
    //文化程度
    private String Degree;
    //录入机构编码
    private String InputDeptCode;
    //结婚登记日期
    private String MarriageRegistrationDate;
    //性别
    private String Sex;
    //民族
    private String Folk;
    //配偶文化程度
    private String SpouseDegree;
    //婚姻状况
    private String MarriageState;
    //配偶婚姻状况
    private String SpouseMarriageState;
    //配偶姓名
    private String SpouseName;
    //配偶证件号码
    private String SpouseDocumentNo;
    //结婚登记号
    private String MarriageNO;
    //国籍
    private String Nationality;
    //配偶国籍
    private String SpouseNationality;
    //证件号码
    private String CardNo;
    //配偶民族
    private String SpouseFolk;
    //证件类型
    private String CardType;
    //配偶证件类型
    private String SpouseDocumentType;

    public MarryData() {
    }

    public MarryData(String personName, String inputDeptName, String degree, String inputDeptCode, String marriageRegistrationDate, String sex, String folk, String spouseDegree, String marriageState, String spouseMarriageState, String spouseName, String spouseDocumentNo, String marriageNO, String nationality, String spouseNationality, String cardNo, String spouseFolk, String cardType, String spouseDocumentType) {
        PersonName = personName;
        InputDeptName = inputDeptName;
        Degree = degree;
        InputDeptCode = inputDeptCode;
        MarriageRegistrationDate = marriageRegistrationDate;
        Sex = sex;
        Folk = folk;
        SpouseDegree = spouseDegree;
        MarriageState = marriageState;
        SpouseMarriageState = spouseMarriageState;
        SpouseName = spouseName;
        SpouseDocumentNo = spouseDocumentNo;
        MarriageNO = marriageNO;
        Nationality = nationality;
        SpouseNationality = spouseNationality;
        CardNo = cardNo;
        SpouseFolk = spouseFolk;
        CardType = cardType;
        SpouseDocumentType = spouseDocumentType;
    }
}
