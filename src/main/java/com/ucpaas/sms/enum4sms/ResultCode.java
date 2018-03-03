package com.ucpaas.sms.enum4sms;

public enum ResultCode {
	/**
	 * 未登录或登录超时
	 */
	noLogin(10401, "未登录或登录超时，请重新登录"),
	/**
	 * 已登陆
	 */
	alreadyLogin(0, "已登陆");

	private Integer value;
	private String desc;

	private ResultCode(Integer value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
