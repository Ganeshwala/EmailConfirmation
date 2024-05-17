package com.springboot.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EmailUserRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailUserRegistrationApplication.class, args);
	}

}
