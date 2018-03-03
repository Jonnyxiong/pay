package com.ucpaas.sms.config.aop;

import com.alibaba.fastjson.JSON;
import com.ucpaas.sms.common.util.AuthorityUtils;
import com.ucpaas.sms.common.util.CommonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
@Order(-5)
public class WebLogAspect {
	protected final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

	ThreadLocal<Long> startTime = new ThreadLocal<>();

	/**
	 * 定义一个切入点. 解释下：
	 *
	 * ~ 第一个 * 代表任意修饰符及任意返回值. ~ 第二个 * 任意包名 ~ 第三个 * 代表任意方法. ~ 第四个 * 定义在web包或者子包 ~
	 * 第五个 * 任意方法 ~ .. 匹配任意数量的参数.
	 */
	@Pointcut("execution(public * com.ucpaas.sms.api..*.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) {
		startTime.set(System.currentTimeMillis());

		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		Object obj = AuthorityUtils.getLoginUser(request);
		String operator = null;
		/*if (obj == null) {
			operator = "未登录";
		} else {
			if (obj instanceof User) {
				operator = ((User) obj).getMobile();
			} else {
				operator = ((Account) obj).getClientid();
			}
		}*/

		// 记录下请求内容
		logger.info("统一记录日志：loginUser: {} IP: {}  URL: {} HTTP_METHOD: {} CLASS_METHOD: {}.{} ...ARGS {} ...params: {}",
				operator, CommonUtil.getClientIP(request), request.getRequestURL().toString(), request.getMethod(),
				joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
				Arrays.toString(joinPoint.getArgs()), JSON.toJSONString(request.getParameterMap()));

	}

	@AfterReturning("webLog()")
	public void doAfterReturning(JoinPoint joinPoint) {
		// 处理完请求，返回内容
		logger.info("耗时（毫秒） : " + (System.currentTimeMillis() - startTime.get()));
	}

}
