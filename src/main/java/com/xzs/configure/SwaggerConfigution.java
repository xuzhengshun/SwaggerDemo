package com.xzs.configure;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfigution extends WebMvcConfigurerAdapter {
	@Autowired
	ServletContext servletContext;

	@Bean
	public Docket createApi() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).useDefaultResponseMessages(true)
				.select().apis(RequestHandlerSelectors.basePackage("com.xzs"))
				.paths(PathSelectors.any()).build();
		return docket;
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("xzs-swaggerDemo").description("xzs-swaggerDemo").contact("ydyy").version("1.0").build();
	}
}
