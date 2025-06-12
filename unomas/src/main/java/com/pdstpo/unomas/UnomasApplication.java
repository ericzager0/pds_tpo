package com.pdstpo.unomas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UnomasApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnomasApplication.class, args);
	}

}
