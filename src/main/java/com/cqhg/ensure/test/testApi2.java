package com.cqhg.ensure.test;

import com.alibaba.fastjson.JSON;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.joda.time.DateTimeUtils;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * 调用demo
 * @author tianhj
 * @since 2017-3-24
 */
public class testApi2 {

    // 签名算法
    private static final String HMAC_SHA1 = "HmacSHA1";

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /**
     * 根据url地址获取json数据
     * @param url
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = JSONObject.fromObject(jsonText);
            return json;
        } finally {
            is.close();
            // System.out.println("同时 从这里也能看出 即便return了，仍然会执行finally的！");
        }
    }

    /**
     * 生成数字摘要
     *
     * @param data
     *            数据
     * @param key
     *            秘钥
     * @return
     */
    public static String hmacDigest(String data, String key) {
        try {
            return macDigest(data.getBytes(), key.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String macDigest(byte[] data, byte[] key) throws InvalidKeyException, NoSuchAlgorithmException {
        SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);
        Mac mac = Mac.getInstance(HMAC_SHA1);
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(data);
        return md5Encrypt(rawHmac);
    }


    private static String md5Encrypt(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data);
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            // 32位加密
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 当前时间 13位时间戳
     * @return Long 当前时间
     */
    public static Long nowLong() {
        return DateTimeUtils.currentTimeMillis();
    }

    public static void main(String[] args) throws IOException, JSONException {
         String cardNo="51192119941219221";//身份证号码
         String personName="唐勇";//姓名
         String batchNo = "101010202112081637225";
         String familyId = "00766f22ba094ed6837879993f757a98";
         String checkItem = "D20215,D56520,D65623,D4564654,D8952,D23365,D8952,D23365,D8952,D23365,D8952,D23365,D8952,D23365";
         List<Test_1> lsit = new ArrayList<Test_1>();
         for (int i = 0 ; i<3; i++){
            Test_1 t = new Test_1();
            t.setBatchNo(batchNo+i);
            t.setBatchNo(cardNo+i);
            t.setCheckItem(checkItem);
            t.setFamilyId(familyId);
            t.setPeopleName(personName+i);
            List<test_file> s = new ArrayList<>();
            test_file f_1 = new test_file();
            f_1.setFileType("01");
            f_1.setFileName("applyBook"+i+1+".pdf");
            test_file f_2 = new test_file();
            f_2.setFileType("02");
            f_2.setFileName("authBook"+i+1 + ".jgp");
            s.add(f_1);
            s.add(f_2);
            t.setFiles(s);
             lsit.add(t);
        }
        String josn = JSON.toJSONString(lsit);
        System.out.println(josn);

    }
}