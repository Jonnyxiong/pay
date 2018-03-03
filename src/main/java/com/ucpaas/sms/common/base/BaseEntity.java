package com.ucpaas.sms.common.base;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity支持类
 */
public abstract class BaseEntity<T> implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	/** 时间段查询条件载体 */
	private Date startTime;

	/** 时间段查询条件载体 */
	private Date endTime;

	public BaseEntity() {

	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
