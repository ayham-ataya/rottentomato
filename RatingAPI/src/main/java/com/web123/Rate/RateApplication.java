package com.web123.Rate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RateApplication {

	public static void main(String[] args) {
		SpringApplication.run(RateApplication.class, args);
	}

}
