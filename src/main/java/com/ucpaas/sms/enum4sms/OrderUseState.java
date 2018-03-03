package com.ucpaas.sms.enum4sms;

public enum OrderUseState {
	/**
	 * 未使用
	 */
	unused(0),
	/**
	 * 使用中,
	 */
	inuse(1),
	/**
	 * 完成
	 */
	complete(2);
	
	Integer value;

	
	private OrderUseState(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
	
}
