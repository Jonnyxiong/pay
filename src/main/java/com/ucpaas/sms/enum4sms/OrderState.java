package com.ucpaas.sms.enum4sms;

public enum OrderState {
	/**
	 * 待付费
	 */
	pendingPay(0),
	/**
	 * 已付费
	 */
	paid(1);

	Integer value;

	private OrderState(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
