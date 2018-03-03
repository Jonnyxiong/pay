package com.ucpaas.sms.common.validator;

import com.ucpaas.sms.common.util.StringUtils;
import com.ucpaas.sms.common.exception.BusinessException;

/**
 * 数据校验
 */
public abstract class Assert {

	public static void isBlank(String str, String message) {
		if (StringUtils.isBlank(str)) {
			throw new BusinessException(message);
		}
	}

	public static void isNull(Object object, String message) {
		if (object == null) {
			throw new BusinessException(message);
		}
	}
}
