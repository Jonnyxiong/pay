package com.ucpaas.sms.common.enums.task;

/**
 * 任务日志常量
 */
public class TaskLogEnum {

	/** 任务日志状态 */
	public enum TaskLogStatus {
		未执行完(1, "未执行完"), 成功(2, "成功"), 失败(3, "失败");

		private Integer value;
		private String desc;

		TaskLogStatus(Integer value, String desc) {
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
			for (TaskLogStatus s : TaskLogStatus.values()) {
				if (value == s.getValue()) {
					result = s.getDesc();
					break;
				}
			}
			return result;
		}
	}

}
