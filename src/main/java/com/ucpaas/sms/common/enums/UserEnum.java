package com.ucpaas.sms.common.enums;

/**
 * 员工常量
 */
public class UserEnum {

	/** 员工状态 */
	public enum UserStatus {
		正常(1, "正常"), 锁定(2, "锁定"), 关闭(3, "关闭");

		private Integer value;
		private String desc;

		UserStatus(Integer value, String desc) {
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
			for (UserStatus s : UserStatus.values()) {
				if (value == s.getValue()) {
					result = s.getDesc();
					break;
				}
			}
			return result;
		}
	}

}
