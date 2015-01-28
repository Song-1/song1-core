package core.util;

import org.apache.log4j.Logger;

public class LogUtil {

	public static final Logger log = Logger.getLogger(LogUtil.class);
	public static final String enter_next_line = "\r\n";
	/**
	 * 记录错误
	 * @param e
	 */
	public static void printDebugException(Exception e) {
		StackTraceElement[] stackTrace = e.getStackTrace();
		System.out.println(e.getMessage());
		StringBuffer error_message = new StringBuffer(e.toString()+enter_next_line);
		for (StackTraceElement stackTraceElement : stackTrace) {
			error_message.append(stackTraceElement.toString()+enter_next_line);
		}
		log.error(error_message);
	}
}
