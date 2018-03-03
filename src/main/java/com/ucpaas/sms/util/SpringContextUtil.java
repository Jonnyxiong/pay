package com.ucpaas.sms.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring容器，方法注入使用
 *
 * @author huangwenjie
 * @create 2016-12-31-15:39
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }


    public static <T> T getBean(Class<T> c) {
        return context.getBean(c);
    }

}
