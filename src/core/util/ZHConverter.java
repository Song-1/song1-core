package core.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ZHConverter {
	
	
	private Properties charMap = new Properties();
	private Set conflictingSets = new HashSet();
	public static final int TRADITIONAL = 0;
	public static final int SIMPLIFIED = 1;
	private static final int NUM_OF_CONVERTERS = 2;
	private static final ZHConverter[] converters = new ZHConverter[2];
	private static final String[] propertyFiles = new String[2];
	
	public static void main(String[] args) {
		ZHConverter converter = ZHConverter.getInstance(ZHConverter.TRADITIONAL);
		String simplifiedStr = converter.convert("有背光的機械式鍵盤");
		System.out.println(simplifiedStr);
	}

	public static ZHConverter getInstance(int converterType) {
		if ((converterType >= 0) && (converterType < 2)) {
			if (converters[converterType] == null) {
				synchronized (ZHConverter.class) {
					if (converters[converterType] == null) {
						converters[converterType] = new ZHConverter(
								propertyFiles[converterType]);
					}
				}
			}
			return converters[converterType];
		}

		return null;
	}

	public static String convert(String text, int converterType) {
		ZHConverter instance = getInstance(converterType);
		return instance.convert(text);
	}

	private ZHConverter(String propertyFile) {
		InputStream is = null;

		is = super.getClass().getResourceAsStream(propertyFile);

		if (is != null) {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(is));
				this.charMap.load(reader);
			} catch (FileNotFoundException e) {
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (reader != null)
						reader.close();
					if (is != null)
						is.close();
				} catch (IOException e) {
				}
			}
		}
		initializeHelper();
	}

	private void initializeHelper() {
		Map stringPossibilities = new HashMap();
		Iterator iter = this.charMap.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			if (key.length() >= 1) {
				for (int i = 0; i < key.length(); ++i) {
					String keySubstring = key.substring(0, i + 1);
					if (stringPossibilities.containsKey(keySubstring)) {
						Integer integer = (Integer) (Integer) stringPossibilities
								.get(keySubstring);
						stringPossibilities.put(keySubstring, new Integer(
								integer.intValue() + 1));
					} else {
						stringPossibilities.put(keySubstring, new Integer(1));
					}
				}
			}

		}

		iter = stringPossibilities.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			if (((Integer) (Integer) stringPossibilities.get(key)).intValue() > 1)
				this.conflictingSets.add(key);
		}
	}

	public String convert(String in) {
		StringBuilder outString = new StringBuilder();
		StringBuilder stackString = new StringBuilder();

		for (int i = 0; i < in.length(); ++i) {
			char c = in.charAt(i);
			String key = "" + c;
			stackString.append(key);

			if (!this.conflictingSets.contains(stackString.toString())) {
				if (this.charMap.containsKey(stackString.toString())) {
					outString.append(this.charMap.get(stackString.toString()));
					stackString.setLength(0);
				} else {
					CharSequence sequence = stackString.subSequence(0,
							stackString.length() - 1);
					stackString.delete(0, stackString.length() - 1);
					flushStack(outString, new StringBuilder(sequence));
				}
			}
		}
		flushStack(outString, stackString);

		return outString.toString();
	}

	private void flushStack(StringBuilder outString, StringBuilder stackString) {
		while (stackString.length() > 0) {
			while (this.charMap.containsKey(stackString.toString())) {
				outString.append(this.charMap.get(stackString.toString()));
				stackString.setLength(0);
			}

			outString.append("" + stackString.charAt(0));
			stackString.delete(0, 1);
		}
	}

	String parseOneChar(String c) {
		if (this.charMap.containsKey(c)) {
			return (String) this.charMap.get(c);
		}

		return c;
	}

	static {
		propertyFiles[0] = "zh2Hant.properties";
		propertyFiles[1] = "zh2Hans.properties";
	}
}