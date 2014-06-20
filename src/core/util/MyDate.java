package core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate {

	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式

	public static String currentDateString() {
		return df.format(new Date());
	}
}
