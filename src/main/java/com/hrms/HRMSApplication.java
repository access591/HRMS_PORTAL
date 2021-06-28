package com.hrms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class HRMSApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(HRMSApplication.class, args);
		
		System.out.println("hi");
	}

}
