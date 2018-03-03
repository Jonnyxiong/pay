package com.ucpaas.sms.enum4sms;

public enum PaymentState {
	/**
	 * 未支付
	 */
	pendingPay(0),
	/**
	 * 支付已提交
	 */
	paymentSubmit(1),
	/**
	 * 已支付
	 */
	paid(2),
	/**
	 * 支付失败
	 */
	payFail(3);

	Integer value;

	private PaymentState(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
