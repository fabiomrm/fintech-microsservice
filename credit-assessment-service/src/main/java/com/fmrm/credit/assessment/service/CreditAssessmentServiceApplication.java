package com.fmrm.credit.assessment.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CreditAssessmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditAssessmentServiceApplication.class, args);
	}

}
