package com.fmrm.credit.assessor.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CreditAssessorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditAssessorServiceApplication.class, args);
	}

}
