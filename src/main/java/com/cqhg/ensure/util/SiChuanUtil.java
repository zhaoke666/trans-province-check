package com.cqhg.ensure.util;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.joda.time.DateTimeUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 川渝跨省核查工具类
 *
 */
public class SiChuanUtil {

    // 签名算法
    private static final String HMAC_SHA1 = "HmacSHA1";


    /**
     * 获取访问四川接口返回结果
     * @param cardNo 身份证号码
     * @param personName 姓名
     * @param urlType 访问地址(户籍/婚姻.. 的地址)
     * @return json
     * @throws IOException 异常
     */
    public static JSONObject getSiChuanJson(String cardNo,String personName,String urlType) throws IOException {
        if(StringUtil.isEmpty(cardNo)){
            throw new NullPointerException("身份证号码不能为空");
        }
        if(StringUtil.isEmpty(personName)){
            throw new NullPointerException("姓名不能为空");
        }
        if(StringUtil.isEmpty(cardNo)){
            throw new NullPointerException("urlType不能为空");
        }

        String serviceCode="500000_Code";//接口服务编码（从接口提供方获取）
        String apiKey="chongqing001";//身份标识（从接口提供方获取）
        String cert="A2CD1291805C445686FDB023002219C3";//密钥
        Long timeStamp= nowLong();//时间戳
        String data="cardNo="+cardNo+"&serviceCode="+serviceCode+"&apiKey="+apiKey+"&timeStamp="+timeStamp;//参数汇总（参数名称、参数顺序请保持不变）
        String digest= hmacDigest(data,cert);//数字摘要
        System.out.println(digest);
        String url="http://59.225.206.8:9039/hecv-datas/external/sichuan/"+urlType+"?personName="+personName+"&cardNo="+cardNo+"&serviceCode="+serviceCode+"&apiKey="+apiKey+"&timeStamp="+timeStamp+"&digest="+digest;

        JSONObject json = readJsonFromUrl(url);
        return json;
    }

    //获取url
    public static String getSiChuanUrl(String cardNo,String personName,String urlType) throws IOException {
        if(StringUtil.isEmpty(cardNo)){
            throw new NullPointerException("身份证号码不能为空");
        }
        if(StringUtil.isEmpty(personName)){
            throw new NullPointerException("姓名不能为空");
        }else{
            //中文进行转码
            personName = URLEncoder.encode(personName, "UTF-8");
        }

        if(StringUtil.isEmpty(cardNo)){
            throw new NullPointerException("urlType不能为空");
        }

        String serviceCode="500000_Code";//接口服务编码（从接口提供方获取）
        String apiKey="chongqing001";//身份标识（从接口提供方获取）
        String cert="A2CD1291805C445686FDB023002219C3";//密钥
        Long timeStamp= nowLong();//时间戳
        String data="cardNo="+cardNo+"&serviceCode="+serviceCode+"&apiKey="+apiKey+"&timeStamp="+timeStamp;//参数汇总（参数名称、参数顺序请保持不变）
        String digest= hmacDigest(data,cert);//数字摘要
        String url="http://59.225.206.8:9039/hecv-datas/external/sichuan/"+urlType+"?personName="+personName+"&cardNo="+cardNo+"&serviceCode="+serviceCode+"&apiKey="+apiKey+"&timeStamp="+timeStamp+"&digest="+digest;


        return url;
    }

    /**
     * 当前时间 13位时间戳
     * @return Long 当前时间
     */
    public static Long nowLong() {
        return DateTimeUtils.currentTimeMillis();
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

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

}
