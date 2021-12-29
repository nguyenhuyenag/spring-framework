package com.config;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.model.Book;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
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
	private static final Class[] HIDDEN_MODELS = { Book.class };

	@Bean
	public Docket filterAPI() {
		return new Docket(DocumentationType.SWAGGER_2) //
				.ignoredParameterTypes(HIDDEN_MODELS).select() //
				.apis(RequestHandlerSelectors.basePackage("com.controller")) //
				.paths(PathSelectors.any()) // .ant("/book/**")
				.build() //
				.apiInfo(apiInfo());
		// .globalOperationParameters(JWTAuthorization());

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

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder() //
				.title("This is Title") //
				.description("This is a sample server Petstore server. You can find out more about Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/). For this sample, you can use the api key `special-key` to test the authorization filters.")
				// .description("[![cdcb](https://i.stack.imgur.com/1XGl3.png)](https://www.uscdcb.com/)")
				.version("1.0") //
				.termsOfServiceUrl("termsOfServiceUrl") //
				.contact(new Contact("Contact name", "https://www.abc.vn", "info@abc.vn")) //
				.license("This is License") //
				.licenseUrl("This license url") //
				.extensions(Collections.emptyList()) //
				.build();
	}
	
}
