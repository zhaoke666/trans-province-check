package com.cqhg.ensure.util;

import com.sun.org.apache.bcel.internal.classfile.Code;

import java.util.HashMap;
import java.util.Map;

public class JsonResultUtils {

    public static final Integer sucessCode =200;
    public static final Integer failCode =300;

    /**
     * 返回成功
     * @param message
     * @return
     */
    public static Map success(String message){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", sucessCode);
        resultMap.put("message", message);
        return resultMap;
    }

    /**
     * 返回失败
     * @param message
     * @return
     */
    public static Map fail(String message){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", failCode);
        resultMap.put("message", message);
        return resultMap;
    }

}
