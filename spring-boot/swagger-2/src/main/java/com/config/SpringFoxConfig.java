package com.config;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.model.Book;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

	@SuppressWarnings("rawtypes")
	private static final Class[] HIDDEN_MODELS = {Book.class};

	@Bean
	public Docket filterAPI() {
		return new Docket(DocumentationType.SWAGGER_2) //
				.ignoredParameterTypes(HIDDEN_MODELS)
				.select() //
				.apis(RequestHandlerSelectors.basePackage("com.controller")) //
				.paths(PathSelectors.any()) //
				// .paths(PathSelectors.ant("/NHD/**")) //
				.build() //
				.apiInfo(apiInfo());
				// .globalOperationParameters(JWTAuthorization());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("Swagger API", //
			"Custom description of API", //
			"2021", //
			"Terms of service", //
			new Contact("ABC", "https://abc.com.vn", "info@abc.com.vn"), //
			"License of API", "API license URL", //
			Collections.emptyList()
		);
	}
	
	List<Parameter> JWTAuthorization() {
		return Collections.singletonList(new ParameterBuilder() //
                .name("Authorization") //
                .description("JWT Authorization token") //
                .modelRef(new ModelRef("string")) //
                .parameterType("header") //
                .required(false) //
                .build());
	}

}
