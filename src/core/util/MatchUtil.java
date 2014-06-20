package core.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
/**
 * 
* @ClassName: MatchUtil 
* @Description: TODO
* @author Jeckey.Liu
* @date 2014年4月15日 下午3:20:25 
*
 */
public class MatchUtil {
	private static Map<Integer, String> map = new HashMap();
	private static Map<String, Integer> mapToInt;

	static {
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

		mapToInt = new HashMap();

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
	}

	public static String getSmallEnglish(int key) {
		String value = (String) map.get(Integer.valueOf(key + 10));
		return value;
	}

	public static String getBigEnglish(int key) {
		String value = (String) map.get(Integer.valueOf(key + 10));
		value = StringUtils.upperCase(value);
		return value;
	}

	public static int getNoByEnglish(String key) {
		key = StringUtils.lowerCase(key);
		int value = ((Integer) mapToInt.get(key)).intValue() - 10;
		return value;
	}

	public static void main(String[] args) {
		System.out.println(getSmallEnglish(0));
		System.out.println(getBigEnglish(0));
		System.out.println(getNoByEnglish("a"));
	}
}