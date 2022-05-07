package com.cqhg.ensure.entity;

import lombok.Data;

@Data
public class CheckPeopleMsg {
    private String batchNo;
    private String familyId;
    private String peopleName;
    private String idCard;
    private String checkItem;
    private String checkState;
    private String backState;
    private String cityCode;
    private String backReslut;
    private String inputTime;
    private String isPass;
    private String idCardPic;
    private String sPadPic;
    private String peopleId;

    public CheckPeopleMsg(String batchNo, String familyId, String peopleName, String idCard, String checkItem, String checkState, String backState, String cityCode, String backReslut, String inputTime, String isPass, String idCardPic, String sPadPic, String peopleId) {
        this.batchNo = batchNo;
        this.familyId = familyId;
        this.peopleName = peopleName;
        this.idCard = idCard;
        this.checkItem = checkItem;
        this.checkState = checkState;
        this.backState = backState;
        this.cityCode = cityCode;
        this.backReslut = backReslut;
        this.inputTime = inputTime;
        this.isPass = isPass;
        this.idCardPic = idCardPic;
        this.sPadPic = sPadPic;
        this.peopleId = peopleId;
    }

    public CheckPeopleMsg() {

    }

}
