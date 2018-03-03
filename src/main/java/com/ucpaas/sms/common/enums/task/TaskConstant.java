package com.ucpaas.sms.common.enums.task;

/**
 * 任务常量
 *
 * @author xiejiaan
 */
public class TaskConstant {

	public enum TaskTypeNew {
		每日统计ACCESS昨日数据(1, "每日统计ACCESS昨日数据"), 每日统计ACCESS四天前数据(2, "每日统计ACCESS四天前数据");

		private Integer value;
		private String desc;

		TaskTypeNew(Integer value, String desc) {
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
			for (TaskTypeNew s : TaskTypeNew.values()) {
				if (value == s.getValue()) {
					result = s.getDesc();
					break;
				}
			}
			return result;
		}

		public static TaskTypeNew getEnumByValue(Integer value) {
			if (value == null) {
				return null;
			}
			TaskTypeNew result = null;
			for (TaskTypeNew s : TaskTypeNew.values()) {
				if (value == s.getValue()) {
					result = s;
					break;
				}
			}
			return result;
		}
	}

	/**
	 * 任务类型
	 *
	 * @author xiejiaan
	 */
	public enum TaskType {

		/**
		 * 客户运维运营报表数据统计(昨日的数据)
		 */
		access_stat_zuori,
		/**
		 * 客户运维运营报表数据统计(四天前的数据)
		 */
		access_stat_foudayago;

		public static TaskType getInstance(int value) {
			switch (value) {
			case 1:
				return access_stat_zuori;
			case 2:
				return access_stat_foudayago;
			default:
				return null;
			}
		}
	}

	/**
	 * 执行类型
	 */
	public enum ExecuteType {
		/**
		 * 执行类型：空
		 */
		empty(null),
		/**
		 * 执行类型：分
		 */
		minute("yyyyMMddHHmm"),
		/**
		 * 执行类型：时
		 */
		hour("yyyyMMddHH"),
		/**
		 * 执行类型：日
		 */
		day("yyyyMMdd"),
		/**
		 * 执行类型：周
		 */
		week("yyyyMMdd"),
		/**
		 * 执行类型：月
		 */
		month("yyyyMM"),
		/**
		 * 执行类型：季
		 */
		season("yyyyMM"),
		/**
		 * 执行类型：年
		 */
		year("yyyy");
		private String format;// 时间格式

		ExecuteType(String format) {
			this.format = format;
		}

		public static ExecuteType getInstance(int value) {
			switch (value) {
			case 0:
				return empty;
			case 1:
				return minute;
			case 2:
				return hour;
			case 3:
				return day;
			case 4:
				return week;
			case 5:
				return month;
			case 6:
				return season;
			case 7:
				return year;
			default:
				return null;
			}
		}

		public String getFormat() {
			return format;
		}
	}

	/**
	 * 扫描类型
	 */
	public enum ScanType {
		/**
		 * 扫描类型：分
		 */
		minute,
		/**
		 * 扫描类型：时
		 */
		hour,
		/**
		 * 扫描类型：日
		 */
		day,
		/**
		 * 扫描类型：周
		 */
		week,
		/**
		 * 扫描类型：月
		 */
		month,
		/**
		 * 扫描类型：季
		 */
		season,
		/**
		 * 扫描类型：年
		 */
		year;

		public static ScanType getInstance(int value) {
			switch (value) {
			case 1:
				return minute;
			case 2:
				return hour;
			case 3:
				return day;
			case 4:
				return week;
			case 5:
				return month;
			case 6:
				return season;
			case 7:
				return year;
			default:
				return null;
			}
		}
	}

	/**
	 * 任务状态
	 *
	 * @author xiejiaan
	 */
	public enum TaskStatus {
		close(0), enabled(1), running(2), delete(3);
		private int value;

		TaskStatus(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public static TaskStatus getInstance(int value) {
			switch (value) {
			case 0:
				return close;
			case 1:
				return enabled;
			case 2:
				return running;
			case 3:
				return delete;
			default:
				return null;
			}
		}
	}

}
