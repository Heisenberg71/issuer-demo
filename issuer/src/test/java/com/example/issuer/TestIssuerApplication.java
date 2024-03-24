package com.example.issuer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestIssuerApplication {

	public static void main(String[] args) {
		SpringApplication.from(IssuerApplication::main).with(TestIssuerApplication.class).run(args);
	}

}
