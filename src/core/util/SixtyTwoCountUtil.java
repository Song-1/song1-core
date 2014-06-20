package core.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: SixtyTwoCountUtil
 * @Description: 编码工具 62*62=3844 工具类
 * @author Jeckey.Liu
 * @date 2014年5月19日 下午3:14:03
 *
 */
public class SixtyTwoCountUtil {
	private static Map<Integer, String> map = new HashMap<Integer, String>();
	private static Map<String, Integer> mapToInt;

	static {
		map.put(Integer.valueOf(0), "0");
		map.put(Integer.valueOf(1), "1");
		map.put(Integer.valueOf(2), "2");
		map.put(Integer.valueOf(3), "3");
		map.put(Integer.valueOf(4), "4");
		map.put(Integer.valueOf(5), "5");
		map.put(Integer.valueOf(6), "6");
		map.put(Integer.valueOf(7), "7");
		map.put(Integer.valueOf(8), "8");
		map.put(Integer.valueOf(9), "9");
		map.put(Integer.valueOf(10), "a");
		map.put(Integer.valueOf(11), "b");
		map.put(Integer.valueOf(12), "c");
		map.put(Integer.valueOf(13), "d");
		map.put(Integer.valueOf(14), "e");
		map.put(Integer.valueOf(15), "f");
		map.put(Integer.valueOf(16), "g");
		map.put(Integer.valueOf(17), "h");
		map.put(Integer.valueOf(18), "i");
		map.put(Integer.valueOf(19), "j");
		map.put(Integer.valueOf(20), "k");
		map.put(Integer.valueOf(21), "l");
		map.put(Integer.valueOf(22), "m");
		map.put(Integer.valueOf(23), "n");
		map.put(Integer.valueOf(24), "o");
		map.put(Integer.valueOf(25), "p");
		map.put(Integer.valueOf(26), "q");
		map.put(Integer.valueOf(27), "r");
		map.put(Integer.valueOf(28), "s");
		map.put(Integer.valueOf(29), "t");
		map.put(Integer.valueOf(30), "u");
		map.put(Integer.valueOf(31), "v");
		map.put(Integer.valueOf(32), "w");
		map.put(Integer.valueOf(33), "x");
		map.put(Integer.valueOf(34), "y");
		map.put(Integer.valueOf(35), "z");
		map.put(Integer.valueOf(36), "A");
		map.put(Integer.valueOf(37), "B");
		map.put(Integer.valueOf(38), "C");
		map.put(Integer.valueOf(39), "D");
		map.put(Integer.valueOf(40), "E");
		map.put(Integer.valueOf(41), "F");
		map.put(Integer.valueOf(42), "G");
		map.put(Integer.valueOf(43), "H");
		map.put(Integer.valueOf(44), "I");
		map.put(Integer.valueOf(45), "J");
		map.put(Integer.valueOf(46), "K");
		map.put(Integer.valueOf(47), "L");
		map.put(Integer.valueOf(48), "M");
		map.put(Integer.valueOf(49), "N");
		map.put(Integer.valueOf(50), "O");
		map.put(Integer.valueOf(51), "P");
		map.put(Integer.valueOf(52), "Q");
		map.put(Integer.valueOf(53), "R");
		map.put(Integer.valueOf(54), "S");
		map.put(Integer.valueOf(55), "T");
		map.put(Integer.valueOf(56), "U");
		map.put(Integer.valueOf(57), "V");
		map.put(Integer.valueOf(58), "W");
		map.put(Integer.valueOf(59), "X");
		map.put(Integer.valueOf(60), "Y");
		map.put(Integer.valueOf(61), "Z");

		mapToInt = new HashMap<String,Integer>();

		mapToInt.put("0", Integer.valueOf(0));
		mapToInt.put("1", Integer.valueOf(1));
		mapToInt.put("2", Integer.valueOf(2));
		mapToInt.put("3", Integer.valueOf(3));
		mapToInt.put("4", Integer.valueOf(4));
		mapToInt.put("5", Integer.valueOf(5));
		mapToInt.put("6", Integer.valueOf(6));
		mapToInt.put("7", Integer.valueOf(7));
		mapToInt.put("8", Integer.valueOf(8));
		mapToInt.put("9", Integer.valueOf(9));
		mapToInt.put("a", Integer.valueOf(10));
		mapToInt.put("b", Integer.valueOf(11));
		mapToInt.put("c", Integer.valueOf(12));
		mapToInt.put("d", Integer.valueOf(13));
		mapToInt.put("e", Integer.valueOf(14));
		mapToInt.put("f", Integer.valueOf(15));
		mapToInt.put("g", Integer.valueOf(16));
		mapToInt.put("h", Integer.valueOf(17));
		mapToInt.put("i", Integer.valueOf(18));
		mapToInt.put("j", Integer.valueOf(19));
		mapToInt.put("k", Integer.valueOf(20));
		mapToInt.put("l", Integer.valueOf(21));
		mapToInt.put("m", Integer.valueOf(22));
		mapToInt.put("n", Integer.valueOf(23));
		mapToInt.put("o", Integer.valueOf(24));
		mapToInt.put("p", Integer.valueOf(25));
		mapToInt.put("q", Integer.valueOf(26));
		mapToInt.put("r", Integer.valueOf(27));
		mapToInt.put("s", Integer.valueOf(28));
		mapToInt.put("t", Integer.valueOf(29));
		mapToInt.put("u", Integer.valueOf(30));
		mapToInt.put("v", Integer.valueOf(31));
		mapToInt.put("w", Integer.valueOf(32));
		mapToInt.put("x", Integer.valueOf(33));
		mapToInt.put("y", Integer.valueOf(34));
		mapToInt.put("z", Integer.valueOf(35));
		mapToInt.put("A", Integer.valueOf(36));
		mapToInt.put("B", Integer.valueOf(37));
		mapToInt.put("C", Integer.valueOf(38));
		mapToInt.put("D", Integer.valueOf(39));
		mapToInt.put("E", Integer.valueOf(40));
		mapToInt.put("F", Integer.valueOf(41));
		mapToInt.put("G", Integer.valueOf(42));
		mapToInt.put("H", Integer.valueOf(43));
		mapToInt.put("I", Integer.valueOf(44));
		mapToInt.put("J", Integer.valueOf(45));
		mapToInt.put("K", Integer.valueOf(46));
		mapToInt.put("L", Integer.valueOf(47));
		mapToInt.put("M", Integer.valueOf(48));
		mapToInt.put("N", Integer.valueOf(49));
		mapToInt.put("O", Integer.valueOf(50));
		mapToInt.put("P", Integer.valueOf(51));
		mapToInt.put("Q", Integer.valueOf(52));
		mapToInt.put("R", Integer.valueOf(53));
		mapToInt.put("S", Integer.valueOf(54));
		mapToInt.put("T", Integer.valueOf(55));
		mapToInt.put("U", Integer.valueOf(56));
		mapToInt.put("V", Integer.valueOf(57));
		mapToInt.put("W", Integer.valueOf(58));
		mapToInt.put("X", Integer.valueOf(59));
		mapToInt.put("Y", Integer.valueOf(60));
		mapToInt.put("Z", Integer.valueOf(61));
	}

	/**
	 * 
	 * @Title: SixtyTwoCount 62 种编码方法
	 * @param no
	 *            需要产生的编号的数字
	 * @param value
	 *            已经产生出来的数字
	 * @param lenght
	 *            编码位数
	 * @return String 编码
	 */
	private static String SixtyTwoCount(int no, String value, int lenght) {
		int last = no % 62;
		int head = no / 62;
		if (value == null) {
			value = "";
		}
		value = (String) map.get(Integer.valueOf(last)) + value;
		if (head >= 62) {
			return SixtyTwoCount(head, value, lenght);
		}
		if (head != 0) {
			value = (String) map.get(Integer.valueOf(head)) + value;
		}
		lenght -= value.length();
		for (int i = 0; i < lenght; ++i) {
			value = "0" + value;
		}
		return value;
	}

	/**
	 * 产生编码
	 * 
	 * @Title: gen 产生编码
	 * @param no
	 * @param lenght
	 * @return String
	 */
	public static String gen(int no, int lenght) {
		return SixtyTwoCount(no, null, lenght);
	}

	public static int thirtySixCount(String value) {
		int no = 0;
		StringBuffer sb = new StringBuffer(value);
		if (sb != null) {
			for (int i = 0; i < sb.length(); ++i) {
				String getValue = sb.substring(i, i + 1);
				int getMapNo = ((Integer) mapToInt.get(getValue)).intValue();
				if (i == sb.length() - 1)
					no += getMapNo;
				else {
					no = (int) Math.pow(62.0D, sb.length() - i - 1) * getMapNo + no;
				}
			}
		}
		return no;
	}

	/**
	 * 62 类编码,默认2位编码
	 * 
	 * @Title: thirtySixCount 62 类编码,默认2位编码
	 * @param no
	 *            需要产生编码的数字
	 * @return String 编码字符串
	 */
	public static String thirtySixCount(int no) {
		return SixtyTwoCount(no, null, 2);
	}

	/**
	 * 编码反编译成数字
	 * 
	 * @Title: gen 编码反编译成数字
	 * @param value
	 *            需要反编译的编码字符串
	 * @return int
	 */
	public static int gen(String value) {
		int no = 0;
		StringBuffer sb = new StringBuffer(value);
		if (sb != null) {
			for (int i = 0; i < sb.length(); ++i) {
				String getValue = sb.substring(i, i + 1);
				int getMapNo = ((Integer) mapToInt.get(getValue)).intValue();
				if (i == sb.length() - 1)
					no += getMapNo;
				else {
					no = (int) Math.pow(62.0D, sb.length() - i - 1) * getMapNo + no;
				}
			}
		}
		return no;
	}

	public static void main(String[] args) {
		System.out.println(thirtySixCount(3));
		System.out.println(gen(31, 1));
		System.out.println(gen(388566, 4));
		System.out.println(gen(3886, 1));
		System.out.println(gen("uy"));
	}
}