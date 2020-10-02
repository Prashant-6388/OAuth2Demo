package com.pc.OAuthDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class OAuthDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OAuthDemoApplication.class, args);
	}

}
