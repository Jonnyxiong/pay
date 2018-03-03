package com.ucpaas.sms.common.enums;

/**
 * 运营商号段常量
 */
public class SegmentEnum {

	/** 号段类型 */
	public enum SegmentOperater {
		移动(1, "移动"), 联通(2, "联通"), 电信(3, "电信"), 国际(4, "国际");

		private Integer value;
		private String desc;

		SegmentOperater(Integer value, String desc) {
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
			for (SegmentOperater s : SegmentOperater.values()) {
				if (value == s.getValue()) {
					result = s.getDesc();
					break;
				}
			}
			return result;
		}

		public static SegmentOperater getEnumByValue(Integer value) {
			if (value == null) {
				return null;
			}
			SegmentOperater result = null;
			for (SegmentOperater s : SegmentOperater.values()) {
				if (value == s.getValue()) {
					result = s;
					break;
				}
			}
			return result;
		}
	}

}
