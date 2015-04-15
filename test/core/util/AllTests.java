package core.util;


import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.commons.httpclient.params.HttpParams;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.HttpConnectionParams;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;


/**
 * java url 是由protocol代表通信协议，host代表主机名，file代表文件名组成
 * file是由文件路径path，文件名，文件后缀组成
 * 
 * @author Administrator
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ CRequestTest.class })
public class AllTests {

	 public static HttpClient getHttpClient(){
	       SchemeRegistry registry = new SchemeRegistry();//创建schema
	       SSLContext sslContext = null;
	       try{
	           sslContext = SSLContext.getInstance("SSL");
	           sslContext.init(null, null, null);
	       }
	       catch (Exception e) {
	           e.printStackTrace();
	       }
	 
	       SSLSocketFactory sslFactory = new SSLSocketFactory(sslContext,SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);     
	       registry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
	       registry.register(new Scheme("https", 443,sslFactory));
	       PoolingClientConnectionManager cm = new PoolingClientConnectionManager(registry);//创建connectionManager   
	       cm.setDefaultMaxPerRoute(100);//对每个指定连接的服务器（指定的ip）可以创建并发20socket进行访问
	       cm.setMaxTotal(200);//创建socket的上线是200
	      
	      
	       HttpClient httpClient = new DefaultHttpClient(cm);//使用连接池创建连接
	       HttpParams params = (HttpParams) httpClient.getParams();
//	       HttpConnectionParams.setSoTimeout(params, 60*1000);
//	       HttpConnectionParams.setConnectionTimeout(params, 60*1000);
	       return httpClient;
	    }
	   
	    public static void main(String[] args) {
	       //创建httpclient
	       HttpClient httpClient = getHttpClient();
	       //创建HttpComponentsClientHttpRequestFactory
	       HttpComponentsClientHttpRequestFactory httpFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
	       //创建resttemplate
	       RestTemplate template = new RestTemplate(httpFactory);
	      
	       //设置message的处理种类
	       List messageConverters = new ArrayList();        
	       messageConverters.add(new StringHttpMessageConverter());
	       messageConverters.add(new ByteArrayHttpMessageConverter());
	       messageConverters.add(new FormHttpMessageConverter());
	       template.setMessageConverters(messageConverters);
	      
	       //访问远程请求
	       System.out.println(template.getForObject("http://m.weather.com.cn/data/101010100.html", String.class));
	      
	    }
}
