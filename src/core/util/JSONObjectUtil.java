package core.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
/**
 * 
* @ClassName: JSONObjectUtil 
* @Description: TODO
* @author Jeckey.Liu
* @date 2014年4月15日 下午2:57:01 
*
 */
public class JSONObjectUtil {
	public static String getByObject(Object object) {
		String jo = JSONObject.fromObject(object).toString();
		return jo;
	}

	public static void responseWriteOut(HttpServletResponse response, Object object) {
		responseWriteOut(response, getByObject(object));
	}

	public static void responseWriteOut(HttpServletResponse response, String json) {
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}