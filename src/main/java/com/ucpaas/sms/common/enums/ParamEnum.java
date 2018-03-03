package com.ucpaas.sms.common.enums;

/**
 * 参数常量
 */
public class ParamEnum {

	/** 参数状态 */
	public enum ParamStatus {
		正常((byte)1, "有效"), 锁定((byte)2, "无效");

		private Byte value;
		private String desc;

		ParamStatus(Byte value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public Byte getValue() {
			return value;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDescByValue(Byte value) {
			if (value == null) {
				return null;
			}
			String result = null;
			for (ParamStatus s : ParamStatus.values()) {
				if (value == s.getValue()) {
					result = s.getDesc();
					break;
				}
			}
			return result;
		}
	}

}
