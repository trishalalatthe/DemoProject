package com.myretail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author trishala
 *
 */
@SpringBootApplication
public class MyRetailApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(MyRetailApplication.class, args);
	}
	
	/**
	 * @param builder
	 * @return RestTemplate
	 */
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
}
