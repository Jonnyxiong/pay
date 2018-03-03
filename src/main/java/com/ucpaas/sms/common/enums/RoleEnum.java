package com.ucpaas.sms.common.enums;

/**
 * 角色常量
 */
public class RoleEnum {

	/** 角色级别 */
	public enum RoleLevel {
		系统(0, "系统级别"), 用户(1, "用户级别");

		private Integer value;
		private String desc;

		RoleLevel(Integer value, String desc) {
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
			for (RoleLevel s : RoleLevel.values()) {
				if (value == s.getValue()) {
					result = s.getDesc();
					break;
				}
			}
			return result;
		}
	}

	/** 角色类型 */
	public enum RoleType {
		普通角色(0, "普通角色"), 超级管理员(1, "超级管理员");

		private Integer value;
		private String desc;

		RoleType(Integer value, String desc) {
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
			for (RoleType s : RoleType.values()) {
				if (value == s.getValue()) {
					result = s.getDesc();
					break;
				}
			}
			return result;
		}
	}

	/** 角色类型 */
	public enum RoleStatus {
		失效(0, "失效"), 生效(1, "生效");

		private Integer value;
		private String desc;

		RoleStatus(Integer value, String desc) {
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
			for (RoleStatus s : RoleStatus.values()) {
				if (value == s.getValue()) {
					result = s.getDesc();
					break;
				}
			}
			return result;
		}
	}
}
