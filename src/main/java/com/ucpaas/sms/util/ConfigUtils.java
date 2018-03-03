package com.ucpaas.sms.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 系统配置工具类
 * 
 * @author huangwenjie
 */
@Component
public class ConfigUtils {
	private static final Logger logger = LoggerFactory.getLogger(ConfigUtils.class);
 

	private String config_file_path;

	/**
	 * 运行环境：development（开发）、devtest（开发测试）、test（测试）、production（线上）
	 */
	// public static String spring_profiles_active;

	/**
	 * 系统版本号
	 */
	public static String system_version;

	/**
	 * 应用ID
	 */
	public static String web_id;
	/**
	 * epay的商户id
	 */
	public static String epay_merId;
	/**
	 * epay的密钥
	 */
	public static String epay_key;


	/**
	 * 是否自动登录
	 */
	public static boolean is_auto_login;



	/**
	 * UEdiotr配置文件路径
	 */
	public static String ueditor_config_file_path;

	/**
	 * smsp-access短信请求URL
	 */
	public static String smsp_access_url;

	/**
	 * smsp-access短信请求clientid
	 */
	public static String smsp_access_clientid;

	/**
	 * smsp-access短信请求password
	 */
	public static String smsp_access_password;

	/**
	 * 重置密码路径
	 */
	public static String smap_resetpwd_url;

	/**
	 * 公用的代理商服务器站点地址
	 */
	public static String agent_site_url;

	/**
	 * oem代理商服务器站点地址
	 */
	public static String oem_agent_site_url;

	/**
	 * 公用的代理商服务器站点地址
	 */
	public static String client_site_url;
	/**
	 * 页面允许导出Excel最大记录数
	 */
	public static String max_export_excel_num;
	/**
	 * <pre>
	 * 接口地址：刷新前台缓存信息
	 *
	 * 主账号key=main:[sid]
	 * 应用key=app:[appSid]
	 * 子账户key=client:[clientNumber]
	 * 白名单key=wl:[appSid]
	 * 短信模板key=tl:[templateId]
	 * </pre>
	 */
	public static String interface_url_flush;

	/**
	 * 接口地址：app审核通过后，分配短信号码
	 */
	public static String interface_url_getMsgNbr;

	/**
	 * rest接口的域名
	 */
	public static String rest_domain;
	/**
	 * rest接口的版本
	 */
	public static String rest_version;
	/**
	 * 前台站点的域名
	 */
	public static String ucpaas_domain;
	/**
	 * 文件本地保存路径
	 */
	public static String save_path;
	/**
	 * 平台标志 ,用于区分客户OEM平台
	 */
	public static String platform_order_identify;
	/**
	 * 图片服务器地址
	 */
	public static String smsp_img_url;
	/**
	 * 模板审核结果推送频率 ,单位秒
	 */
	public static String template_authorize_period;
	/**
	 * 模板审核结果推送次数
	 */
	public static String template_authorize_time;

	/**
	 * 模板审核结果推送超时,单位秒
	 */
	public static String template_authorize_timeout;

	/**
	 * 运营平台下单标识为4
	 */
	public static String platform_oem_agent_order_identify;

	public static String environmentFlag;

	public static String client_oauth_pic;

	/**
	 * 初始化
	 */
	@PostConstruct
	public void init() {
		String path = ConfigUtils.class.getClassLoader().getResource("").getPath();
		// spring_profiles_active =
		// System.getProperty("spring.profiles.active");
		config_file_path = path + "system.properties";

		initValue();

		logger.info("\n\n-------------------------【smsc-pay -{} 启动】\n加载配置文件：\n{}\n", system_version, config_file_path);
	}

	/**
	 * 初始化配置项的值
	 */
	private void initValue() {
		Field[] fields = ConfigUtils.class.getFields();
		Object fieldValue = null;
		String name = null, value = null, tmp = null;
		Class<?> type = null;
		Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}");
		Matcher matcher = null;
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(config_file_path));

			for (Field field : fields) {
				name = field.getName();
				value = properties.getProperty(name);
				if (StringUtils.isNotBlank(value)) {
					matcher = pattern.matcher(value);
					while (matcher.find()) {
						tmp = properties.getProperty(matcher.group(1));
						if (StringUtils.isBlank(tmp)) {
							logger.error("配置{}存在其它配置{}，请检查您的配置文件", name, matcher.group(1));
						}
						value = value.replace(matcher.group(0), tmp);
					}

					type = field.getType();
					if (String.class.equals(type)) {
						fieldValue = value;
					} else if (Integer.class.equals(type)) {
						fieldValue = Integer.valueOf(value);
					} else if (Boolean.class.equals(type)) {
						fieldValue = Boolean.valueOf(value);
					} else {
						fieldValue = value;
					}
					field.set(this, fieldValue);
				}
				logger.info("加载配置：{}={}", name, field.get(this));
			}
		} catch (Throwable e) {
			logger.error("初始化配置项的值失败：" + name + "=" + value, e);
		}
	}

}
