package com.ucpaas.sms.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;

/**
 * Created by dylan on 2017/7/10.
 */
@Configuration
public class CustomMVCConfiguration extends WebMvcConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(CustomMVCConfiguration.class);

    @Autowired
    private Environment env;

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }

    @Override
    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    @Bean
    public JavaMailSenderImpl javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        System.out.println(env.getProperty("spring.mail.host"));
        javaMailSender.setHost(env.getProperty("spring.mail.host"));
        javaMailSender.setPort(465);
        javaMailSender.setUsername(env.getProperty("spring.mail.username"));
        javaMailSender.setPassword(env.getProperty("spring.mail.password"));

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", env.getProperty("spring.mail.properties.mail.smtp.auth"));
        properties.setProperty("mail.smtp.timeout", env.getProperty("spring.mail.properties.mail.smtp.timeout"));
        properties.setProperty("mail.smtp.socketFactory.class", env.getProperty("spring.mail.properties.mail.smtp.socketFactory.class"));
        javaMailSender.setJavaMailProperties(properties);
        return javaMailSender;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.debug("=======================================================");
        logger.debug("         "+env.getProperty("welcome.message"));
        logger.debug("=======================================================");
       // registry.addInterceptor(new AuthorizationInterceptor()).addPathPatterns("/**");
    }
}
