package core.util;

import core.bean.ImageBean;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class HttpClientUtil {
	private static String textName = "test3";

	protected static int _defaultTimeOut = 120000;
	protected static MultiThreadedHttpConnectionManager httpConnectionManager;
	private static boolean useProxy = true;
	private static Map<String, Object> imgeMap;

	static {
		httpConnectionManager = new MultiThreadedHttpConnectionManager();
		httpConnectionManager.getParams().setConnectionTimeout(_defaultTimeOut);
		httpConnectionManager.getParams().setSoTimeout(_defaultTimeOut);
		httpConnectionManager.getParams().setDefaultMaxConnectionsPerHost(500);
		httpConnectionManager.getParams().setMaxTotalConnections(500);

		imgeMap = new HashMap();

		imgeMap.put("bmp", Integer.valueOf(1));
		imgeMap.put("gif", Integer.valueOf(1));
		imgeMap.put("jpeg", Integer.valueOf(1));
		imgeMap.put("jpe", Integer.valueOf(1));
		imgeMap.put("png", Integer.valueOf(1));
		imgeMap.put("jpg", Integer.valueOf(1));
		imgeMap.put("tif", Integer.valueOf(1));
		imgeMap.put("ico", Integer.valueOf(1));
	}

	public static String getCode(String _url) throws IOException {
		HttpURLConnection request = null;
		String responseString = "";
		URL url = null;
		try {
			url = new URL(_url);
		} catch (MalformedURLException e) {
			return "400";
		}
		int responseCode = 500;
		try {
			request = (HttpURLConnection) url.openConnection();
			responseCode = request.getResponseCode();
		} catch (IOException e) {
			System.out.println(url);
		}
		return String.valueOf(responseCode);
	}

	public static void setProxy(HttpClient client) {
		String host = System.getProperty("http.proxyHost");
		String port = System.getProperty("http.proxyPort");
		if ((host == null) || ("".equals(host)) || (port == null) || ("".equals(port)))
			return;
		client.getHostConfiguration().setProxy(host, Integer.parseInt(port.trim()));
	}

	public static String getHttpRequest(String url) {
		GetMethod getMethod = null;
		try {
			HttpClient client = new HttpClient();
			client.getParams().setParameter("http.protocol.content-charset", "UTF-8");
			getMethod = new GetMethod(url);
			getMethod.getParams().setParameter("http.socket.timeout", Integer.valueOf(60000));

			int status = client.executeMethod(getMethod);
			if (status == 200) {
				InputStream resStream = getMethod.getResponseBodyAsStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(resStream, getMethod.getRequestCharSet()));
				StringBuffer resBuffer = new StringBuffer();
				String resTemp = "";
				while ((resTemp = br.readLine()) != null) {
					resBuffer.append(resTemp);
				}
				String response = resBuffer.toString();
				String str1 = response;
				return str1;
			}
			return null;
		} catch (Exception localException) {
		} finally {
			if (getMethod != null) {
				getMethod.releaseConnection();
			}
		}

		return null;
	}

	public static String getHttpRequestPostMethod(String url) {
		GetMethod getMethod = null;
		try {
			HttpClient client = new HttpClient(httpConnectionManager);
			if (useProxy) {
				setProxy(client);
			}

			PostMethod postMethod = new PostMethod(url);
			NameValuePair[] data = { new NameValuePair("id", "youUserName"), new NameValuePair("passwd", "yourPwd") };
			postMethod.setRequestBody(data);

			getMethod.getParams().setParameter("http.socket.timeout", Integer.valueOf(60000));

			int status = client.executeMethod(getMethod);
			if (status == 200) {
				InputStream resStream = getMethod.getResponseBodyAsStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(resStream));
				StringBuffer resBuffer = new StringBuffer();
				String resTemp = "";
				while ((resTemp = br.readLine()) != null) {
					resBuffer.append(resTemp);
				}
				String response = resBuffer.toString();

				String str1 = response;
				return str1;
			}
			return null;
		} catch (Exception localException) {
		} finally {
			if (getMethod != null) {
				getMethod.releaseConnection();
			}
		}

		return null;
	}

	public static Document parseXMLContent(String xml) {
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			doc = reader.read(new ByteArrayInputStream(xml.trim().getBytes()));
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		return doc;
	}

	public static List parseResponse(Document doc) {
		List list = new ArrayList();
		Element treeroot2 = doc.getRootElement();
		Element treeroot = treeroot2.element("props");
		Iterator it = treeroot.elementIterator();
		while (it.hasNext()) {
			Element element = (Element) it.next();

			if (element != null) {
				list.add(element.getText());
			}
		}

		return list;
	}

	public static List findStr(String value, String str) {
		StringBuffer newValue = new StringBuffer(value);
		int next = 0;
		List list = new ArrayList();
		for (int i = 0; i < newValue.length(); i = next) {
			int where = newValue.indexOf(str, next);
			if (where > -1) {
				next = where;
				list.add(Integer.valueOf(where));
			}
			++next;
		}
		return list;
	}

	public static void getImageImpl(String url, String outUrl) {
		if ((url == null) || (!url.startsWith("h"))) {
			return;
		}
		try {
			Image image = ImageIO.read(new URL(url));

			int width = image.getWidth(null);
			int height = image.getHeight(null);

			BufferedImage bufferedImage = new BufferedImage(width, height, 1);
			bufferedImage.getGraphics().drawImage(image, 0, 0, width, height, null);

			File dirFile = new File(outUrl);
			if (!dirFile.exists())
				dirFile.mkdir();

			File file = new File(outUrl + url.substring(url.lastIndexOf("/"), url.length()));
			if (!file.exists())
				file.createNewFile();
			ImageIO.write(bufferedImage, url.substring(url.lastIndexOf(".") + 1, url.length()), file);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getPic(String url) {
		String value = getHttpRequest(url);
		List listbegin = findStr(value, "src=\"");
		List listlast = findStr(value, "\"");

		int j = 0;

		for (int i = 0; i < listlast.size(); ++i) {
			if ((j >= listbegin.size())
					|| (((Integer) listlast.get(i)).intValue() <= ((Integer) listbegin.get(j)).intValue()))
				continue;
			String image = value.substring(((Integer) listbegin.get(j)).intValue() + 5,
					((Integer) listlast.get(i + 1)).intValue());
			image.endsWith("jpg");

			++j;
		}
	}

	private static String getUrl(String url) {
		try {
			URL urls = new URL(url);
			return urls.getHost();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<ImageBean> getPicList(String url) {
		String wzt = "";
		List list = new ArrayList();
		if (url.startsWith("http")) {
			wzt = "http://" + getUrl(url);
		} else if (url.startsWith("www")) {
			url = "http://" + url;
			wzt = "http://" + getUrl(url);
		} else {
			url = "http://www." + url;
			wzt = "http://www." + getUrl(url);
		}
		String value = getHttpRequest(url);
		String[] listImg = StringUtils.substringsBetween(value, "<img", ">");
		if ((listImg != null) && (listImg.length > 0)) {
			for (int i = 0; i < listImg.length; ++i) {
				ImageBean imageBean = new ImageBean();
				imageBean.setTitle(StringUtils.substringBetween(listImg[i], "alt=\"", "\""));
				String src = StringUtils.substringBetween(listImg[i], "src=\"", "\"");
				if ((src != null) && (!src.startsWith("http://"))) {
					if (src.startsWith(".."))
						src = src.replaceFirst("..", wzt);
					else if (src.startsWith("."))
						src = src.replaceFirst(".", wzt);
					else if (src.startsWith("www"))
						src = "http://" + src;
					else
						src = wzt + src;
				}
				imageBean.setSrc(src);
				list.add(imageBean);
			}
		}
		return list;
	}

	public static void copyFile(File from, File to) {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(from);
			out = new FileOutputStream(to);

			byte[] bt = new byte[1024];
			int c;
			while ((c = in.read(bt)) > 0) {
				out.write(bt, 0, c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		for (int j = 1; j <= 3; ++j)
			System.out.println(j);
	}
}