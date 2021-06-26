package com.db.tradestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@ComponentScan({"com.db.*"})
@EnableSwagger2
@SpringBootApplication
@EnableScheduling
public class TradestoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradestoreApplication.class, args);
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(regex("/trades.*"))
				.build()
				//.pathMapping("/")
				.apiInfo(metaData());
	}

	@Bean
	public ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo(
				"REST API for Storage of Trades",
				"Spring Boot REST API for Storage of Trades",
				"1.0",
				"Terms of service",
				new Contact("Shyam Jandhyala", "", "jvpshyam@gmail.com"),
				"",
				"");
		return apiInfo;
	}

}
