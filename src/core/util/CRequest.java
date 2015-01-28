package core.util;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CRequest {
	static final boolean WINDOWS = System.getProperty("os.name").startsWith(
			"Windows");

	/**
	 * 解析出url请求的路径，包括页面
	 * 
	 * @param strURL
	 *            url地址
	 * @return url路径
	 */
	public static String UrlPage(String strURL) {
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
		return strPage;
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
	public static String paramToString(Map<String, String> paramSet) {
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
		System.out.println("paramUri:" + paramUri);
		String urlString = "http://192.168.21.77:8080/swp/mainPage?aa=11&bb%3D22";
		testUri(paramUri, urlString);
		return paramUri;
	}

	public static String testUri(String paramUri, String uri_str) {
		System.out.println(getEncodeUri(uri_str));// 解码
		try {
			System.out.println(getNotEncodeUri(uri_str));// 不解码
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return paramUri;
	}

	/**
	 * uri解码
	 * 
	 * @param uri_str
	 * @return
	 */
	public static String getEncodeUri(String uri_str) {
		return URI.create(uri_str).getQuery();
	}

	/**
	 * uri不解码
	 * 
	 * @param uri_str
	 * @throws MalformedURLException
	 */
	public static String getNotEncodeUri(String uri_str)
			throws MalformedURLException {
		return new URL(uri_str).getQuery();
	}

	private URI uri;

	private void initialize(String scheme, String authority, String path,
			String fragment) {
		try {
			this.uri = new URI(scheme, authority, normalizePath(path), null,
					fragment).normalize();
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 标准化path
	 * 
	 * @param path
	 * @return
	 */
	private String normalizePath(String path) { // 标准化输出Path，
		// remove double slashes & backslashes
		if (path.indexOf("//") != -1) {
			path = path.replace("//", "/");
		}
		if (path.indexOf("\\") != -1) {
			path = path.replace("\\", "/");
		}

		// trim trailing slash from non-root path (ignoring windows drive)
		int minLength = hasWindowsDrive(path, true) ? 4 : 1;
		if (path.length() > minLength && path.endsWith("/")) {
			path = path.substring(0, path.length() - 1);
		}

		return path;
	}

	private boolean hasWindowsDrive(String path, boolean slashed) {
		if (!WINDOWS)
			return false;
		int start = slashed ? 1 : 0;
		return path.length() >= start + 2
				&& (slashed ? path.charAt(0) == '/' : true)
				&& path.charAt(start + 1) == ':'
				&& ((path.charAt(start) >= 'A' && path.charAt(start) <= 'Z') || (path
						.charAt(start) >= 'a' && path.charAt(start) <= 'z'));
	}

	/** Convert this to a URI. */
	public URI toUri() {
		return uri;
	}
}