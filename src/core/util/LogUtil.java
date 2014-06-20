package core.util;

import java.util.List;

public class LogUtil {

	public static void sysout(String message) {
		System.out.println("message:" + message);
	}
	
	public static void printList(List list){
		String message=null;
		for (Object object : list) {
			
		}
		sysout(message);
	}
}
