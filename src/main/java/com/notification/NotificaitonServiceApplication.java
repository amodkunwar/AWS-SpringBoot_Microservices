package com.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class NotificaitonServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificaitonServiceApplication.class, args);
		log.info("Spring boot sns application started successfully!!");
	}

}
//https://examples.javacodegeeks.com/spring-boot-and-sns-email-example/
