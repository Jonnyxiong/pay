package com.ucpaas.sms.common.validator.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by lpjLiu on 2017/6/26.
 * use @NotNullNumberRange(min = 0, max = Integer.MAX_VALUE, message = "是否直连不能为空且必须是数字，取值范围为1至2147483647")
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = NotNullNumRangeValidator.class)
@Documented
public @interface NotNullNumberRange {
	int min() default 0;

	int max() default Integer.MAX_VALUE;

	String message() default "{org.hibernate.validator.constraints.Length.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
