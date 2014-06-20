package core.util.encrypt.des;

import java.io.IOException;
import java.io.PrintStream;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DesEncryptUtil
{
  private static final String DES = "DES";

  public static void main(String[] args)
    throws Exception
  {
    String data = "JD8AW+Gf97TgNZXFBj11c73gjo1X093aelu03JP8oS8=";
    String key = "song1x3hd38q9he2c0jxlmusic";
    System.err.println(encrypt(data, key));
    System.err.println(decrypt(encrypt(data, key), key));
    System.err.println(decrypt("4OwHoG8COwQXmrSIL3lSmQ==", "song1x3hd38q9he2c0jxlmusic"));
  }

  public static String encrypt(String data, String key)
    throws Exception
  {
    byte[] bt = encrypt(data.getBytes(), key.getBytes());
    String strs = new BASE64Encoder().encode(bt);
    return strs;
  }

  public static String decrypt(String data, String key)
    throws IOException, Exception
  {
    if (data == null)
      return null;
    BASE64Decoder decoder = new BASE64Decoder();
    byte[] buf = decoder.decodeBuffer(data);
    byte[] bt = decrypt(buf, key.getBytes());
    return new String(bt);
  }

  private static byte[] encrypt(byte[] data, byte[] key)
    throws Exception
  {
    SecureRandom sr = new SecureRandom();

    DESKeySpec dks = new DESKeySpec(key);

    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
    SecretKey securekey = keyFactory.generateSecret(dks);

    Cipher cipher = Cipher.getInstance("DES");

    cipher.init(1, securekey, sr);

    return cipher.doFinal(data);
  }

  private static byte[] decrypt(byte[] data, byte[] key)
    throws Exception
  {
    SecureRandom sr = new SecureRandom();

    DESKeySpec dks = new DESKeySpec(key);

    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
    SecretKey securekey = keyFactory.generateSecret(dks);

    Cipher cipher = Cipher.getInstance("DES");

    cipher.init(2, securekey, sr);

    return cipher.doFinal(data);
  }
}