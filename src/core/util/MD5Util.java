package core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };

	private static MessageDigest messageDigest = null;

	static {
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public static String getFileMD5String(File file) {
		String ret = "";
		FileInputStream in = null;
		FileChannel ch = null;
		try {
			in = new FileInputStream(file);
			ch = in.getChannel();
			ByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0L, file.length());
			messageDigest.update(byteBuffer);
			ret = bytesToHex(messageDigest.digest());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (ch != null) {
				try {
					ch.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}

	public static String getFileMD5String(String fileName) {
		return getFileMD5String(new File(fileName));
	}

	public static String getMD5String(String str) {
		return getMD5String(str.getBytes());
	}

	public static String getMD5String(byte[] bytes) {
		messageDigest.update(bytes);
		return bytesToHex(messageDigest.digest());
	}

	public static boolean checkPassword(String pwd, String md5) {
		return getMD5String(pwd).equalsIgnoreCase(md5);
	}

	public static boolean checkPassword(char[] pwd, String md5) {
		return checkPassword(new String(pwd), md5);
	}

	public static boolean checkFileMD5(File file, String md5) {
		return getFileMD5String(file).equalsIgnoreCase(md5);
	}

	public static boolean checkFileMD5(String fileName, String md5) {
		return checkFileMD5(new File(fileName), md5);
	}

	public static String bytesToHex(byte[] bytes) {
		return bytesToHex(bytes, 0, bytes.length);
	}

	public static String bytesToHex(byte[] bytes, int start, int end) {
		StringBuilder sb = new StringBuilder();
		for (int i = start; i < start + end; ++i) {
			sb.append(byteToHex(bytes[i]));
		}
		return sb.toString();
	}

	public static String byteToHex(byte bt) {
		return String.valueOf(HEX_DIGITS[((bt & 0xF0) >> 4)] + HEX_DIGITS[(bt & 0xF)]);
	}

	public static void main(String[] args) throws IOException {
		long begin = System.currentTimeMillis();
		String md5 = getFileMD5String(new File("F:\\安装程序\\装机软件\\Office2003SP1.iso"));
		long end = System.currentTimeMillis();
		System.out.println("MD5:[" + md5 + "]Time:[" + (end - begin) + "ms]");
	}
}