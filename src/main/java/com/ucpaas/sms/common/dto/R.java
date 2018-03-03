package com.ucpaas.sms.common.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lpjLiu on 2017/6/2.
 */
public class R {

	private Integer code;
	private String msg;
	private Object data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public R() {
		this.setCode(0);
	}

	public static R error(int code, String msg) {
		R r = new R();
		r.setCode(code);
		r.setMsg(msg);
		return r;
	}

	public static R error(String msg) {
		return error(500, msg);
	}

	public static R ok(String msg) {
		R r = new R();
		r.setMsg(msg);
		return r;
	}

	public static R ok(String msg, Object data) {
		R r = new R();
		r.setMsg(msg);
		r.setData(data);
		return r;
	}

	public boolean isSuccess(){
		return this.code == 0;
	}

	public Map<String, Object> asMap() {
		Map<String, Object> map = new HashMap<>();
		map.put("code", this.getCode());
		map.put("msg", this.getMsg());
		map.put("data", this.getData());
		return map;
	}

	public static Map<String, Object> asMap(R r) {
		Map<String, Object> map = new HashMap<>();
		if (r == null)
			return map;

		map.put("code", r.getCode());
		map.put("msg", r.getMsg());
		map.put("data", r.getData());
		return map;
	}
}
