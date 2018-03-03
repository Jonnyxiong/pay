package com.ucpaas.sms.util;


import com.ucpaas.sms.dto.SMSResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("deprecation")
public class HttpUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	/**
	 * Http 请求方法
	 * 
	 * @param url
	 * @param content
	 * @return
	 */
	public static String httpPost(String url, String content, boolean needSSL) {
		// 创建HttpPost
		String result = null;
		HttpClient httpClient = getHttpClient(needSSL, StringUtil.getHostFromURL(url));
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Content-Type", ContentType.APPLICATION_FORM_URLENCODED + ";charset=utf-8");
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
			httpPost.setConfig(requestConfig);
			BasicHttpEntity requestBody = new BasicHttpEntity();
			requestBody.setContent(new ByteArrayInputStream(content.getBytes("utf-8")));
			requestBody.setContentLength(content.getBytes("utf-8").length);
			httpPost.setEntity(requestBody);
			// 执行客户端请求
			HttpEntity entity = httpClient.execute(httpPost).getEntity();

			if (entity != null) {
				result = EntityUtils.toString(entity, "utf-8");
				EntityUtils.consume(entity);
			}

		} catch (Throwable e) {
			logger.error("【HTTP请求失败】: url={}, content={}", url, content);
		}

		return result;
	}

	public static DefaultHttpClient getHttpClient(boolean sslClient, String host) {
		DefaultHttpClient httpclient = null;
		if (sslClient) {
			try {
				SSLHttpClient chc = new SSLHttpClient();
				InetAddress address = null;
				String ip;
				try {
					address = InetAddress.getByName(host);
					ip = address.getHostAddress().toString();
					httpclient = chc.registerSSL(ip, "TLS", 443, "https");
				} catch (UnknownHostException e) {
					logger.error("获取请求服务器地址失败：host = {} " + host);
					e.getStackTrace().toString();
				}
				HttpParams hParams = new BasicHttpParams();
				hParams.setParameter("https.protocols", "SSLv3,SSLv2Hello");
				httpclient.setParams(hParams);
			} catch (KeyManagementException e) {
				logger.error(e.getStackTrace().toString());
			} catch (NoSuchAlgorithmException e) {
				logger.error(e.getStackTrace().toString());
			}
		} else {
			httpclient = new DefaultHttpClient();
		}
		return httpclient;
	}

	public static String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}

	public static String fakeSMSPHttpPost() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "a");
		map.put("b", "b");
		map.put("c", "c");
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		list.add(map);
		int i = 1;
		SMSResponse resp = new SMSResponse(i, list);
		String json = JsonUtils.toJson(resp);

		return json;
	}

	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static void main(String[] args) throws UnknownHostException {

		System.out.println(InetAddress.getByName("api.ucpaas.com").getHostAddress().toString());
	}
}
