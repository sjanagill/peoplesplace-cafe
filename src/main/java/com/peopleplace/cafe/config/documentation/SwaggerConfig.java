package com.peopleplace.cafe.config.documentation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("public-api")
        		.apiInfo(getApiInfo())
                .select()
                //.apis(RequestHandlerSelectors.basePackage("com.peoplesplace.cafe"))
                .paths(PathSelectors.any())
                .build();
    }

	
	private ApiInfo getApiInfo() {
        Contact contact = new Contact("peoplesplace-cafe", "http://peoplesplace.com", "contact.peoplesplace@gmail.com");
        return new ApiInfoBuilder()
                .title("PeoplesPlace Cafe API")
                .description("Documentation PeoplesPlace-Cafe")
                .version("1.0.0")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .contact(contact)
                .build();
    }
}