package com.ucpaas.sms.config;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by lpjLiu on 2017/7/22.
 */
@Configuration
@EnableSwagger2
@Profile({"dev", "devtest", "prod"})
public class SwaggerConfig {
	@Bean
	public Docket apiAll() {
		return new Docket(DocumentationType.SWAGGER_2).produces(Sets.newHashSet("application/json")).apiInfo(apiInfo())
				.consumes(Sets.newHashSet("application/json"))
				.protocols(Sets.newHashSet("http"/* , "https" */)).forCodeGeneration(true).select()
				.apis(RequestHandlerSelectors.basePackage("com.ucpaas.smse")).paths(PathSelectors.any()).build();
	}

	private Predicate<String> petstorePaths() {
		return or(regex("/api.*"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("短信企业平台API文档").description("短信企业平台 REST风格的可视化文档")
				.termsOfServiceUrl("http://sms.ucpaas.com/v2/api-docs")
				.contact(new Contact("jackLiu", "http://sms.ucpaas.com", "liulipengju@ucpaas.com")).version("1.0.0.0")
				.build();
	}
}
