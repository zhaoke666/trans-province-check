package com.cqhg.ensure.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.UUID;

public class DateTimeUtils {



    public static String getSystime() {
        java.util.Date dt = new java.util.Date();
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String nowTime = "";
        nowTime = df.format(dt);
        return nowTime;
    }

    public static String getUUID(){
        String s = UUID.randomUUID().toString();
        //去掉“-”符号
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
    }


}
