package com.cqhg.ensure.util;
import java.security.Key;
import java.security.Security;
import java.util.*;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.alibaba.fastjson.JSON;
import com.cqhg.ensure.entity.CheckPeopleMsg;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

/**
 * sm4加密工具类
 */
public class Sm4Util {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final String ENCODING = "UTF-8";
    public static final String ALGORITHM_NAME = "SM4";
    public static final String ALGORITHM_NAME_ECB_PADDING = "SM4/ECB/PKCS5Padding";
    public static final int DEFAULT_KEY_SIZE = 128;

    // 生成ECB暗号
    private static Cipher generateEcbCipher(String algorithmName, int mode, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithmName, BouncyCastleProvider.PROVIDER_NAME);
        Key sm4Key = new SecretKeySpec(key, ALGORITHM_NAME);
        cipher.init(mode, sm4Key);
        return cipher;
    }

    // sm4加密
    public static String encryptEcb(String hexKey, String paramStr) {
        try {
            String cipherText = "";
            byte[] keyData = ByteUtils.fromHexString(hexKey);
            byte[] srcData = paramStr.getBytes(ENCODING);
            // 加密后的数组
            byte[] cipherArray = encrypt_Ecb_Padding(keyData, srcData);
            cipherText = ByteUtils.toHexString(cipherArray);
            return cipherText;
        } catch (Exception e) {
            return paramStr;
        }
    }

    // 加密模式之Ecb
    public static byte[] encrypt_Ecb_Padding(byte[] key, byte[] data) throws Exception {
        Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    // sm4解密
    public static String decryptEcb(String hexKey, String cipherText) {
        // 用于接收解密后的字符串
        String decryptStr = "";
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        byte[] cipherData = ByteUtils.fromHexString(cipherText);
        // 解密
        byte[] srcData = new byte[0];
        try {
            srcData = decrypt_Ecb_Padding(keyData, cipherData);
            decryptStr = new String(srcData, ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptStr;
    }

    // 解密
    public static byte[] decrypt_Ecb_Padding(byte[] key, byte[] cipherText) throws Exception {
        Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(cipherText);
    }

    // 校验加密前后的字符串是否为同一数据
    public static boolean verifyEcb(String hexKey, String cipherText, String paramStr) throws Exception {
        boolean flag = false;
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        byte[] cipherData = ByteUtils.fromHexString(cipherText);
        // 解密
        byte[] decryptData = decrypt_Ecb_Padding(keyData, cipherData);
        byte[] srcData = paramStr.getBytes(ENCODING);
        flag = Arrays.equals(decryptData, srcData);
        return flag;
    }

    public static void main(String[] args) {
     /*   List<CheckPeopleMsg> list = new ArrayList<>();
        List<Map> list1 = new ArrayList<>();
        CheckPeopleMsg checkPeopleMsg = new CheckPeopleMsg("20210323","FAMILYid","张三","500102155555","11,2,3");
        Map<String,String> map = new HashMap<>();
        map.put("batchNo","20210323");
        map.put("familyId","familyId");
        map.put("peopleName","zhangSan");
        map.put("idCard","5001021555555");
        map.put("checkItem","1,2,3");
        list.add(checkPeopleMsg);
        list1.add(map);
        String aa = Sm4Util.encryptEcb("36D6525304C247F39B055000200B0E1A",JSON.toJSONString(list));

        String bb = Sm4Util.encryptEcb("36D6525304C247F39B055000200B0E1A",JSON.toJSONString(list1));
        System.out.println(aa);
        System.out.println(bb);*/

        String cc = "175c82e707a72258eb03d6212a5a33b0e602ce903e7c600dc56026319dfb3282b4930f012e3ea7879d70761eba6c39cf6a3ffe0dbec1d2072f9d533e68387a1a6edb16565533f12f23d3af789340e5581c4d5b769399f51bd285e781147893e548c8f596be93e5decb63262b4aaaeea30523f4da1a4a6a2dfc3ffd73264a0f3e549730aeb7edf09994f9ec95f14ae5a1f866959c29be62684bb1ff538be49bd5ff972d906326bff59d734cbe5844b0f46185453ad2ec4986a0594ce2e8818b18e305568c37ec89f6054a3a78bc7466a8ffd25f8c4c2d849b03c8c7cbd1af37a8281308f01816379f962fb2b0ce7639589ee22bf369c3d6c7b712684a1760447882f7a20e9a3316127d764a6f4a4b12eeb5717856a1f1f48c19f3df38d8fca754591a85359c9d967c81c4130e674936089c7ad7f94f709c4084b54278241cab970ed6301603d2352b6432e80a40e5dc47132d48d41256f1cb8cdde69f7dfab0842f467058079de5c26f456b24f2ca83eeb019ccffd98a68da92f32e55a17a7cd73a9124b444c5d438c474ebb2bd91d188035355eb3c0de23dbbefd0e7b4c421038edfcc421debe650607bd20a345583f79cf425d809e3003e62a5032d20e9310f8973629644a8af097f5aad6b211b61d12fb6b4ff88fef4d38c0a72527c8e669da3b3c6f4607bb864747de0e742ec36d33c8d68017acdebcfe8c527a4885e54bc9e56581e7f18950c4c6388ccda74bf5fd1fb599129658aad0dd61665d47a9682ab7b49dc4a846fa733590c638e9b2d874acd8e3e3ca0d7c04bdf440588e438226a27aaa55e659344f56e051a659cc486bccaea0ca5f57d841ebf46d7b74b5621defa4fc70ff8d353dd6c2774440d432985eebb135add5a6fbebd05804b6d014d5032d77bd66d7f28e2e96630339ebaab469a2e4fffc1265107c769360cc12e3bc770080ebe2b7f6d897e0006005c9da054921419e9568fb64dbea9b31256be8b974d8011d09cd25b2263f84ceee73b06e50099ca0148852f4dbef0504cabc92b4224065fdbeeab6d07211495e0646c22dc8d8aa173ae4ad397d593f85f6b873d9f5809e1264d49f94f514134651501a22a048bb76d6e456171b5d771eec18512c6a72a36492ffa4d710eae7fc097233b65609a8a45786fc823c1042c8dbb8295539c3c3d949da82deddf48f2f43f2ea6b5328bad9cc0273a38c1c6e9f9699986";
        String dd = Sm4Util.decryptEcb("36D6525304C247F39B055000200B0E1B",cc);
        System.out.println(dd);
    }
}
