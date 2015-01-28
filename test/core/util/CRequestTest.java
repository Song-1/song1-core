package core.util;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import core.util.CRequest;

public class CRequestTest {
	CRequest testCRequest;
	String urlString; 
	@Before
    public void setUp() throws Exception {
		testCRequest = new CRequest();
		urlString = "http://192.168.21.77:8080/swp/mainPage?aa=11&bb%3D22"; 
    }
	/**
	 * 解析出url请求的路径，包括页面
	 * 
	 * @param strURL
	 *            url地址
	 * @return url路径
	 */
//	@Test
	public void testUrlPage(String strURL) {
		String strPage = null;
		String[] arrSplit = null;
		strURL = strURL.trim().toLowerCase();
		arrSplit = strURL.split("[?]");
		if (strURL.length() > 0) {
			if (arrSplit.length > 1) {
				if (arrSplit[0] != null) {
					strPage = arrSplit[0];
				}
			}
		}
	}

	/**
	 * 去掉url中的路径，留下请求参数部分
	 * 
	 * @param strURL
	 *            url地址
	 * @return url请求参数部分
	 */
	private static String TruncateUrlPage(String strURL) {
		String strAllParam = null;
		String[] arrSplit = null;
		strURL = strURL.trim().toLowerCase();
		arrSplit = strURL.split("[?]");
		if (strURL.length() > 1) {
			if (arrSplit.length > 1) {
				if (arrSplit[1] != null) {
					strAllParam = arrSplit[1];
				}
			}
		}
		return strAllParam;
	}

	/**
	 * 解析出url参数中的键值对 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
	 * 
	 * @param URL
	 *            url地址
	 * @return url请求参数部分
	 */
	public static Map<String, String> URLRequest(String URL) {
		Map<String, String> mapRequest = new HashMap<String, String>();
		String[] arrSplit = null;
		String strUrlParam = TruncateUrlPage(URL);
		if (strUrlParam == null) {
			return mapRequest;
		}
		// 每个键值为一组
		arrSplit = strUrlParam.split("[&]");
		for (String strSplit : arrSplit) {
			String[] arrSplitEqual = null;
			arrSplitEqual = strSplit.split("[=]");
			// 解析出键值
			if (arrSplitEqual.length > 1) {
				// 正确解析
				mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
			} else {
				if (arrSplitEqual[0] != "") {
					// 只有参数没有值，不加入
					mapRequest.put(arrSplitEqual[0], "");
				}
			}
		}
		return mapRequest;
	}
	
	/**
	 * URL拼接
	 * 
	 * @param URL
	 *            url地址
	 * @return url请求参数部分
	 */
	public String paramToString(Map<String, String> paramSet) {
		String paramUri = new String();
		Set<String> keySet = paramSet.keySet();
		String concat_mark = "&";
		String concat_equal_str = "=";
		for (String ks : keySet) {
			String param = paramSet.get(ks);
			paramUri = paramUri.concat(concat_mark);
			paramUri = paramUri.concat(ks);
			paramUri = paramUri.concat(concat_equal_str).concat(param);
		}
		System.out.println("paramUri:"+paramUri);
		String urlString = "http://192.168.21.77:8080/swp/mainPage?aa=11&bb%3D22";   
		CRequest.testUri(paramUri, urlString);
	     return paramUri;
	}
	@Test
	public void testEncodeUri() {
		String uri_str = "?aa=11&bb%3D22";
//		URI uri = new URI(uri_str);
		String encodeUri = CRequest.testUri(uri_str, urlString);
		URI uri = URI.create(urlString);   
	    System.out.println(uri.getPath());   
	    System.out.println("解码 :"+uri.getQuery());//解码   
	    URL url2 = null;
		try {
			url2 = new URL(urlString);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}   
	    System.out.println("不解码 :"+url2.getQuery());//不解码 
	}
}