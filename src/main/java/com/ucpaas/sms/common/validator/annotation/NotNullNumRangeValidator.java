package com.ucpaas.sms.common.validator.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by lpjLiu on 2017/6/26.
 */
public class NotNullNumRangeValidator implements ConstraintValidator<NotNullNumberRange, Integer> {
	private int min;
	private int max;

	@Override
	public void initialize(NotNullNumberRange parameters) {
		min = parameters.min();
		max = parameters.max();
	}

	public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
		if (value == null) {
			return false;
		}
		return value >= min && value <= max;
	}

}
