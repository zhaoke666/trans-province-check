package com.cqhg.ensure.util;

import com.fasterxml.jackson.databind.node.TextNode;
import jdk.nashorn.internal.parser.Lexer;

import java.io.*;
import java.sql.Blob;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    private static final int[] li_SecPosValue = { 1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594, 2787, 3106, 3212,
            3472, 3635, 3722, 3730, 3858, 4027, 4086, 4390, 4558, 4684, 4925, 5249, 5590 };

    private static final String[] lc_FirstLetter = { "a", "b", "c", "d", "e", "f", "g", "h", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "w", "x", "y", "z" };
    public static final String EMPTY_STRING = "";

    public static final boolean isNumeric(String input) {
        if (input == null) {
            return false;
        }
        int sz = input.length();
        int dotsize = 0;
        for (int i = 0; i < sz; i++) {
            if ((i > 0) && (input.charAt(i) == '.')) {
                dotsize++;
            } else if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
            if (dotsize > 1)
                return false;
        }
        return true;
    }



    public static final boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if ((ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS)
                || (ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS)
                || (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A)
                || (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION)
                || (ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION)
                || (ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS)) {

            return true;
        }
        return false;
    }

    public static Set<String> getSet(String stringList, String delimiter) {
        if (isEmpty(delimiter)) {
            throw new IllegalArgumentException("Argument 'delimiter' shouldn't be empty!");
        }
        if (isEmpty(stringList)) {
            return new HashSet();
        }
        Set<String> set = new HashSet();
        StringTokenizer st = new StringTokenizer(stringList, delimiter);
        while (st.hasMoreTokens()) {
            String tmp = st.nextToken();
            if (isNotEmpty(tmp))
                set.add(tmp.toLowerCase());
        }
        return set;
    }

    public static Set<String> getSet(String stringList) {
        return getSet(stringList, "|");
    }

    public static boolean isEmpty(String str) {
        return (str == null) || (str.trim().length() == 0);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isBlank(String str) {
        if (isEmpty(str)) {
            return true;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isWhitespace(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static String handelUrl(String url) {
        if (url == null) {
            return null;
        }
        url = url.trim();
        if ((url.equals("")) || (url.startsWith("http://")) || (url.startsWith("https://"))) {
            return url;
        }
        return "http://" + url.trim();
    }



    public static String txt2htm(String txt) {
        if (isBlank(txt)) {
            return txt;
        }
        StringBuilder sb = new StringBuilder((int) (txt.length() * 1.2D));

        boolean doub = false;
        for (int i = 0; i < txt.length(); i++) {
            char c = txt.charAt(i);
            if (c == ' ') {
                if (doub) {
                    sb.append(' ');
                    doub = false;
                } else {
                    sb.append("&nbsp;");
                    doub = true;
                }
            } else {
                doub = false;
                switch (c) {
                    case '&':
                        sb.append("&amp;");
                        break;
                    case '<':
                        sb.append("&lt;");
                        break;
                    case '>':
                        sb.append("&gt;");
                        break;
                    case '"':
                        sb.append("&quot;");
                        break;
                    case '\n':
                        sb.append("<br/>");
                        break;
                    default:
                        sb.append(c);
                }

            }
        }
        return sb.toString();
    }

    public static String textCut(String s, int len, String append) {
        if (s == null) {
            return null;
        }
        int slen = s.length();
        if (slen <= len) {
            return s;
        }

        int maxCount = len * 2;
        int count = 0;
        int i;
        for (i = 0; (count < maxCount) && (i < slen); i++) {
            if (s.codePointAt(i) < 256) {
                count++;
            } else {
                count += 2;
            }
        }
        if (i < slen) {
            if (count > maxCount) {
                i--;
            }
            if (!isBlank(append)) {
                if (s.codePointAt(i - 1) < 256) {
                    i -= 2;
                } else {
                    i--;
                }
                return s.substring(0, i) + append;
            }
            return s.substring(0, i);
        }

        return s;
    }


    public static boolean containsKeyString(String str) {
        if (isBlank(str)) {
            return false;
        }
        if ((str.contains("'")) || (str.contains("\"")) || (str.contains("\r")) || (str.contains("\n"))
                || (str.contains("\t")) || (str.contains("\b")) || (str.contains("\f"))) {

            return true;
        }
        return false;
    }

    public static String replaceKeyString(String str) {
        if (containsKeyString(str)) {
            return str.replace("'", "\\'").replace("\"", "\\\"").replace("\r", "\\r").replace("\n", "\\n")
                    .replace("\t", "\\t").replace("\b", "\\b").replace("\f", "\\f");
        }

        return str;
    }

    public static String hidden(String str, int start, int end) {
        if (isBlank(str)) {
            return str;
        }
        if (end > str.length()) {
            end = str.length();
        }
        if (str.length() - start <= 0) {
            start = (str.length() - 1) / 2 + 1;
        }
        if (start > end) {
            end = str.length();
        }

        String result = str.substring(0, start);
        for (int i = start; i < end; i++) {
            result = result + "*";
        }
        result = result + str.substring(end);

        return result;
    }

    public static String replaceString(String original, String find, String replace) {
        if (original == null) {
            original = "";
        }
        String returnStr = "";
        if (original.indexOf(find) < 0) {
            returnStr = original;
        }
        try {
            while (original.indexOf(find) >= 0) {
                int indexbegin = original.indexOf(find);
                String leftstring = original.substring(0, indexbegin);
                original = original.substring(indexbegin + find.length());
                if (original.indexOf(find) <= 0) {
                    returnStr = returnStr + leftstring + replace + original;
                } else {
                    returnStr = returnStr + leftstring + replace;
                }
            }
            return returnStr;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return original;
    }

    public static void main(String[] args) {
        System.out.println(replaceKeyString("&nbsp;\r</p>"));
    }

    public static String byte2hex(byte[] b) {
        StringBuffer hs = new StringBuffer();
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            if (stmp.length() == 1) {
                hs.append("0");
                hs.append(stmp);
            } else {
                hs.append(stmp);
            }
        }
        return hs.toString().toUpperCase();
    }

    public static byte[] hex2byte(String strkey) {
        int keylength = strkey.length() / 2;
        String strValue = "";
        byte[] key = new byte[keylength];
        for (int i = 0; i < keylength; i++) {
            strValue = strkey.substring(2 * i, 2 * (i + 1));
            key[i] = Integer.valueOf(strValue, 16).byteValue();
        }
        return key;
    }

    public static String string2Unicode(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i);
            if (ch > 255) {
                str = str + "\\u" + Integer.toHexString(ch);
            } else
                str = str + "\\" + Integer.toHexString(ch);
        }
        return str;
    }

    public static String unicode2String(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            char ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }

    public static String getCity(String urlinfo) {
        String result = "";
        String[] temp = urlinfo.split(",");
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].indexOf("city") > 0) {
                String[] city = temp[i].split(":");
                result = city[1].replace("\"", "");
                break;
            }
        }
        return result;
    }

    public static String getProvince(String urlinfo) {
        String result = "";
        String[] temp = urlinfo.split(",");
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].indexOf("province") > 0) {
                String[] city = temp[i].split(":");
                result = city[1].replace("\"", "");
                break;
            }
        }
        return result;
    }

    public static String toOldSfzmhm(String sfzmhm) {
        if ((sfzmhm == null) || (sfzmhm.length() == 0)) {
            return sfzmhm;
        }
        if (sfzmhm.length() == 18) {
            return sfzmhm.substring(0, 6) + sfzmhm.substring(8, 17);
        }
        return sfzmhm;
    }

    public static String transScriptStr(String strJava) {
        String r = null;
        if (strJava == null) {
            r = "";
        } else {
            r = replaceString(strJava, "'", "\\'");
            r = replaceString(r, "\"", "\\\"");

            r = replaceString(r, "\r", "\\r");
            r = replaceString(r, "\n", "\\n");
            r = replaceString(r, ";", " ");
        }
        return r;
    }

    public static byte[] blobToBytes(Blob blob) {
        BufferedInputStream is = null;
        int len;
        try {
            is = new BufferedInputStream(blob.getBinaryStream());
            byte[] bytes = new byte[(int) blob.length()];
            len = bytes.length;
            int offset = 0;
            int read = 0;

            while ((offset < len) && ((read = is.read(bytes, offset, len - offset)) >= 0)) {
                offset += read;
            }
            return bytes;
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (is != null) {
                    is.close();
                    is = null;
                }
            } catch (IOException e) {
                return null;
            }
        }
    }



    public static byte[] getInputStream4Path(String imgpath) {
        byte[] result = null;
        try {
            FileInputStream is = new FileInputStream(imgpath);
            int i = is.available();
            result = new byte[i];
            is.read(result);
            is.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static byte[] InputStreamToByte(InputStreamReader is) throws IOException {
        ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
        int ch;
        while ((ch = is.read()) != -1) {
            bytestream.write(ch);
        }
        byte[] imgdata = bytestream.toByteArray();
        bytestream.close();
        return imgdata;
    }


    public static String transNull(Object tj) {
        String ret = "0";
        if (tj != null) {
            ret = tj.toString().trim();
        }
        return ret;
    }

    public static String getAllFirstLetter(String str) {
        if ((str == null) || (str.trim().length() == 0)) {
            return "";
        }
        String _str = "";
        for (int i = 0; i < str.length(); i++) {
            _str = _str + getFirstLetter(str.substring(i, i + 1));
        }
        return _str;
    }

    public static String getFirstLetter(String chinese) {
        if ((chinese == null) || (chinese.trim().length() == 0)) {
            return "";
        }
        chinese = conversionStr(chinese, "GB2312", "ISO8859-1");
        if (chinese.length() > 1) {
            int li_SectorCode = chinese.charAt(0);
            int li_PositionCode = chinese.charAt(1);
            li_SectorCode -= 160;
            li_PositionCode -= 160;
            int li_SecPosCode = li_SectorCode * 100 + li_PositionCode;
            if ((li_SecPosCode > 1600) && (li_SecPosCode < 5590)) {
                for (int i = 0; i < 23; i++) {
                    if ((li_SecPosCode >= li_SecPosValue[i]) && (li_SecPosCode < li_SecPosValue[(i + 1)])) {
                        chinese = lc_FirstLetter[i];
                        break;
                    }
                }
            } else {
                chinese = conversionStr(chinese, "ISO8859-1", "GB2312");
                chinese = chinese.substring(0, 1);
            }
        }
        return chinese;
    }

    private static String conversionStr(String str, String charsetName, String toCharsetName) {
        try {
            str = new String(str.getBytes(charsetName), toCharsetName);
        } catch (UnsupportedEncodingException ex) {
            System.out.println("瀛楃涓茬紪鐮佽浆鎹㈠紓甯革細" + ex.getMessage());
        }
        return str;
    }

    public static String deleteCharacter(String s) {
        StringBuilder builder = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (((chars[i] >= '一') && (chars[i] <= 40869)) || ((chars[i] >= 'a') && (chars[i] <= 'z'))
                    || ((chars[i] >= 'A') && (chars[i] <= 'Z'))) {
                builder.append(chars[i]);
            }
        }
        return builder.toString();
    }

    public static String getXbFromSfzmhm(String sfzmhm) {
        String xb = "";
        if (isBlank(sfzmhm))
            return xb;
        int xbN = 1;
        int len = sfzmhm.length();
        if (len == 18) {
            xbN = Integer.parseInt(sfzmhm.substring(16, 17));
        } else if (len == 15) {
            xbN = Integer.parseInt(sfzmhm.substring(len - 1));
        }
        if (xbN % 2 == 1) {
            xb = "1";
        } else
            xb = "2";
        return xb;
    }

    public static boolean isLetter(char c) {
        int k = 128;
        return c / k == 0;
    }

    public static int length(String s) {
        if (s == null)
            return 0;
        char[] c = s.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++) {
            len++;
            if (!isLetter(c[i])) {
                len++;
            }
        }
        return len;
    }

    public static boolean hashValue(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }
}
