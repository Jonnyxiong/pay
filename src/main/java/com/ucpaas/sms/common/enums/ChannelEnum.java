package com.ucpaas.sms.common.enums;

/**
 * 通道常量
 */
public class ChannelEnum {

	/** 通道运营商类型 */
	public enum OperatorsType {

		全网(0, "全网"), 移动(1, "移动"), 联通(2, "联通"), 电信(3, "电信"), 国际(4, "国际");

		private Integer value;
		private String desc;

		OperatorsType(Integer value, String desc) {
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
			for (OperatorsType s : OperatorsType.values()) {
				if (value == s.getValue()) {
					result = s.getDesc();
					break;
				}
			}
			return result;
		}

		public static OperatorsType getEnumByValue(Integer value) {
			if (value == null) {
				return null;
			}
			OperatorsType result = null;
			for (OperatorsType s : OperatorsType.values()) {
				if (value == s.getValue()) {
					result = s;
					break;
				}
			}
			return result;
		}
	}

	/** 通道状态 */
	public enum ChannelStatus {

		关闭(0, "关闭"), 开启(1, "开启"), 暂停(2, "暂停");

		private Integer value;
		private String desc;

		ChannelStatus(Integer value, String desc) {
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
			for (ChannelStatus s : ChannelStatus.values()) {
				if (value == s.getValue()) {
					result = s.getDesc();
					break;
				}
			}
			return result;
		}

		public static ChannelStatus getEnumByValue(Integer value) {
			if (value == null) {
				return null;
			}
			ChannelStatus result = null;
			for (ChannelStatus s : ChannelStatus.values()) {
				if (value == s.getValue()) {
					result = s;
					break;
				}
			}
			return result;
		}
	}

}
