package com.cqhg.ensure.test;

import lombok.Data;

import java.util.List;

@Data
public class Test_1 {

    private String familyId;

    private String peopleName;

    private String idCard;

    private String checkItem;

    private String batchNo;

    List<test_file> files;
}
