package com.ucpaas.sms;

import com.ucpaas.sms.util.ConfigUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ConditionalOnEnabledResourceChain;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.Serializable;

/**
 * Created by dylan on 2017/7/8.
 */
@SpringBootApplication
//basePackages = { "com.apress.prospringmvc.bookstore.web" }
//@ComponentScan(basePackages = {"com.jsmsframework","com.ucpaas.sms"})

//@ComponentScan(basePackages = {"com.jsmsframework.**/*Service","com.ucpaas.sms"})
//@MapperScan({"com.jsmsframework.*.mapper","com/ucpaas/sms/mapper"})
//@ComponentScan({"com.jsmsframework.*","com.ucpaas.sms.mapper"})

/*@SpringBootApplication(scanBasePackages = {"com.ucpaas.sms.*","com.jsmsframework.*.service","com.jsmsframework.*.mapper"},exclude = DataSourceAutoConfiguration.class)
@ConditionalOnEnabledResourceChain
@PropertySource(value = "classpath:/application.properties", ignoreResourceNotFound = true,encoding = "UTF-8")
@EnableScheduling
@ComponentScan(basePackages={"com.ucpaas.sms.*","com.jsmsframework.*.service"})
@MapperScan({"com.jsmsframework.*.mapper","com.ucpaas.sms.mapper"})*/

@ComponentScan(basePackages={"com.ucpaas.sms.*","com.jsmsframework.**.service"})
public class Application implements Serializable{

    public static void main(String[] args) {


        SpringApplication.run(Application.class, args);

    }

    /*@Bean
    public FilterRegistrationBean smsFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AuthorityFilter());
        registration.addUrlPatterns("*//*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("authorityFilter");
        registration.setOrder(1);
        return registration;
    }*/
}
