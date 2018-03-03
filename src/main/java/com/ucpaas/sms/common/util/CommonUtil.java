package com.ucpaas.sms.common.util;

import com.ucpaas.sms.common.util.file.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

	private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);

	public static String getHostFromURL(String url){
		String host = "";
		Pattern p = Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+");
		Matcher matcher = p.matcher(url);
		if (matcher.find()) {
			host = matcher.group();
		}
		return host;
	}

	public static void upload2(String path, String fileName, CommonsMultipartFile file) {
		if (!file.isEmpty()) {
			// 取文件格式后缀名
			String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
			fileName = fileName + "." + fileType;

			File destFile = new File(path);
			try {
				// 复制临时文件到指定目录下
				FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);
			} catch (IOException e) {
				logger.error("【上次文件时异常】", e);
			}

		} else {
			logger.error("【导入手机号码失败】Excel内容为空");
		}
	}

	/*public static String getUserInfo(Object obj) {
		if (obj instanceof User) {
			User user = (User) obj;
			StringBuilder builder = new StringBuilder();
			builder.append("{").append("id: ").append(user.getId()).append(" , username: ").append(user.getUsername())
					.append(" , mobile: ").append(user.getMobile()).append("}");
			return builder.toString();
		} else if (obj instanceof Account) {
			Account user = (Account) obj;
			StringBuilder builder = new StringBuilder();
			builder.append("{").append("clientid: ").append(user.getClientid()).append(" , realname: ")
					.append(user.getRealname()).append(" , parentId: ").append(user.getParentId()).append("}");
			return builder.toString();
		} else {
			return JSON.toJSONString(obj);
		}
	}*/

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

	/**
	 * 验证手机号码
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean isMobile(String phone) {
		boolean flag = false;
		List<String> regexList = new ArrayList<>();
		regexList.add("^13\\d{9}$");
		regexList.add("^14[5|7|9]\\d{8}$");
		regexList.add("^15[0|1|2|3|5|6|7|8|9]\\d{8}$");
		regexList.add("^18\\d{9}$");
		regexList.add("^170[0|1|2|3|4|5|6|7|8|9]\\d{7}$");
		regexList.add("^17[1|5|6|7|8]\\d{8}$");
		regexList.add("^173\\d{8}$");
		for (String regex : regexList) {
			try {
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(phone);
				flag = m.matches();
				if (flag) {
					break;
				}
			} catch (Exception e) {
				flag = false;
				continue;
			}
		}
		return flag;
	}

	/**
	 * 验证海外号码
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean isOverSeaMobile(String phone) {
		if (StringUtils.isEmpty(phone)) {
			return false;
		}
		if (phone.startsWith("00") && phone.length() > 10) {
			return true;
		}
		return false;
	}

}
