package com.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

	@Bean
	public Docket filterAPI() {
		return new Docket(DocumentationType.SWAGGER_2) //
				.select() //
				.apis(RequestHandlerSelectors.basePackage("com.controller")) //
				.paths(PathSelectors.ant("/book/*")) //
				.build() //
				.apiInfo(apiInfo());
//                .useDefaultResponseMessages(false)
//                .globalResponseMessage(RequestMethod.GET,
//                        Arrays.asList(new ResponseMessageBuilder()
//                                        .code(500)
//                                        .message("500 message custom")
//                                        .responseModel(new ModelRef("Error"))
//                                        .build(),
//                                new ResponseMessageBuilder()
//                                        .code(403)
//                                        .message("403 message custom!")
//                                        .build()));
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("Swagger API AAAAAAAAAAAAAAAAAAA", //
			"Some custom description of API BBBBBBBBBBBBBB.", //
			"@2021", //
			"Terms of serviceeeeeee", //
			new Contact("Deftttttt", "http://abc.net/", "abc@abc.com"), //
			"License of APIIIIIIII", "API license URL", //
			Collections.emptyList()
		);
	}

}
