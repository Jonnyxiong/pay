package com.ucpaas.sms.common.enums;

/**
 * 客户常量
 */
public class ClientEnum {

	/** 客户状态 */
	public enum ClientStatus {
		注册完成(1, "注册完成"), 冻结(5, "冻结"), 注销(6, "注销"), 锁定(7, "锁定");

		private Integer value;
		private String desc;

		ClientStatus(Integer value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public Integer getValue() {
			return value;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDescByValue(Integer value) {
			if (value == null) {
				return null;
			}
			String result = null;
			for (ClientStatus s : ClientStatus.values()) {
				if (value == s.getValue()) {
					result = s.getDesc();
					break;
				}
			}
			return result;
		}

		public static ClientStatus getEnumByValue(Integer value) {
			if (value == null) {
				return null;
			}
			ClientStatus result = null;
			for (ClientStatus s : ClientStatus.values()) {
				if (value == s.getValue()) {
					result = s;
					break;
				}
			}
			return result;
		}
	}

}
