package com.ucpaas.sms.dto;

import java.math.BigDecimal;

public class EpayNotify {

	/**
	 * 商户编号
	 */
	private String merId;
	/**
	 * 订单编号
	 */
	private String orderId;

	/**
	 * 实际支付金额，单位为分（实际支付金额与订单提交金额可能不同）
	 */
	private BigDecimal payAmount;

	/**
	 * 支付结果 fail=失败 success=成功
	 */
	private boolean result;

	/**
	 * 商户数据，原样返回
	 */
	private String merData;

	/**
	 * 银行业务流水号（有的银行并不会返回流水号，则该字段为空）
	 */
	private String bankTransId;

	/**
	 * kc卡信息，格式：cardtype,goodsid,cardpwd。例如:2,20010,xxx
	 */
	private String cardinfo;

	/**
	 * 签名将各参数的值、md5密钥按顺序拼接成一个字符串后进行md5运算。参数值如果为null必须转换成空字符串。
	 */
	private String sign;

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMerData() {
		return merData;
	}

	public void setMerData(String merData) {
		this.merData = merData;
	}

	public String getBankTransId() {
		return bankTransId;
	}

	public void setBankTransId(String bankTransId) {
		this.bankTransId = bankTransId;
	}

	public String getCardinfo() {
		return cardinfo;
	}

	public void setCardinfo(String cardinfo) {
		this.cardinfo = cardinfo;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
