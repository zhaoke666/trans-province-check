package com.cqhg.ensure.entity;

import lombok.Data;

/**
 * 户籍信息
 */
@Data
public class HuJiData {
    //匹配结果信息
    private String matchingMessage;
    //匹配结果编码
    private String matchingResult;

    public HuJiData(String matchingMessage, String matchingResult) {
        this.matchingMessage = matchingMessage;
        this.matchingResult = matchingResult;
    }
}
