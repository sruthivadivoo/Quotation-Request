package com.cts.quotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class RequestQuotation {

	public static void main(String[] args) {
		SpringApplication.run(RequestQuotation.class, args);
	}

}
