package core.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.RandomStringUtils;

public class StringUtil
{
  public static boolean isEmptyString(String str)
  {
    return (str == null) || (str.trim().equals(""));
  }

  public static String convertGb2312ToIso8859(String in)
  {
    String out = null;
    try {
      byte[] ins = in.getBytes("gb2312");
      out = new String(ins, "iso-8859-1");
    } catch (Exception localException) {
    }
    return out;
  }

  public static String md5EncodeString(String str, boolean islong)
  {
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("MD5");
      md.update(str.getBytes());
    }
    catch (Exception e) {
      throw new IllegalArgumentException();
    }

    String res = byteToString(md.digest());
    if (!islong) {
      res = res.substring(8, 24);
    }
    return res;
  }

  private static String byteToString(byte[] b) {
    StringBuffer hexString = new StringBuffer();

    for (int i = 0; i < b.length; ++i) {
      String plainText = Integer.toHexString(0xFF & b[i]);
      if (plainText.length() < 2) {
        plainText = "0" + plainText;
      }
      hexString.append(plainText);
    }

    return hexString.toString();
  }

  public static String encodeString(String str, String enc) throws Exception
  {
    if (str == null) {
      return "";
    }
    String theDefenc = "UTF-8";
    if (!isEmptyString(enc)) {
      theDefenc = enc;
    }
    return URLEncoder.encode(str, theDefenc);
  }

  public static String decodeString(String str, String enc)
    throws Exception
  {
    if (str == null) {
      return "";
    }
    String theDefenc = "UTF-8";
    if (!isEmptyString(enc)) {
      theDefenc = enc;
    }
    return URLDecoder.decode(str, theDefenc);
  }

  public static String genRandomString(int size)
  {
    return RandomStringUtils.random(size, "0123456789abcedfghijklmnpqrstuvwxyz0123456789");
  }
  public static String genRandomString(int size, String textChars) {
    return RandomStringUtils.random(size, textChars);
  }

  public static String replaceTextTplFlag(String text, Map<String, String> valueKeyMap) {
    List<String> list = strRgxMatch(".*?\\$\\{(.*?)\\}.*?", text);
    for (String key : list) {
      if (valueKeyMap.get(key) != null) {
        text = text.replaceAll("\\$\\{" + key + "\\}", (String)valueKeyMap.get(key));
      }
    }
    return text;
  }

  public static List<String> strRgxMatch(String rgx, String sourceStr) {
    List res = new ArrayList();
    Pattern pat = Pattern.compile(rgx, 10);
    Matcher mat = pat.matcher(sourceStr);
    while (mat.find()) {
      res.add(mat.group(1));
    }
    return res;
  }

  public static boolean isStrRgxMatch(String rgx, String sourceStr) {
    Pattern pat = Pattern.compile(rgx, 10);
    return pat.matcher(sourceStr).matches();
  }

  public static String genShortTextKey(String text)
  {
    String key = "genShortTextKey";

    String[] chars = { "a", "b", "c", "d", "e", "f", "g", "h", 
      "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", 
      "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", 
      "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", 
      "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", 
      "U", "V", "W", "X", "Y", "Z" };

    String sMD5EncryptResult = MD5Util.getMD5String(key + text);
    String hex = sMD5EncryptResult;
    String[] resUrl = new String[4];
    int i = 0; if (i < 4)
    {
      String sTempSubString = hex.substring(i * 8, i * 8 + 8);

      long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
      String outChars = "";
      for (int j = 0; j < 6; ++j)
      {
        long index = 0x3D & lHexLong;

        outChars = outChars + chars[(int)index];

        lHexLong >>= 5;
      }

      return outChars;
    }

    return null;
  }

  public static boolean getBooleanFromSringChar(String src, int charIndex)
  {
    if ((isEmptyString(src)) || (src.trim().length() < charIndex + 1)) {
      return false;
    }
    char c = src.charAt(charIndex);

    return c != '0';
  }

  public static String[] split(String str, String regex, int limit)
  {
    if (str != null) {
      return str.split(regex, limit);
    }
    return null;
  }
  public static String[] split(String str, String regex) {
    if (str != null) {
      return str.split(regex);
    }
    return null;
  }

  public static void main(String[] args)
    throws Exception
  {
  }
}