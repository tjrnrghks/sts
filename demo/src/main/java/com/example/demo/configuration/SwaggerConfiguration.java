package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//swagger를 적용시키기 위한 설정
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
	@Bean
	public Docket api() {//Docket 형태의 bean을 api라는 아이디로 만들어라
		return new Docket(DocumentationType.SWAGGER_2)
								//api 정보 설정 : API 제목 , 설명 , 버전 , ...
								.apiInfo(new ApiInfoBuilder().title("데모 API").version("0.0.1").description("설명").build())
								.select()
								//apis 에서는 감시 대상의 범위를 설정
								//.apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
								.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
								//paths 에서는 감시 대상 주소를 설정
								.paths(PathSelectors.any())
							.build();		
	}
	
}
