package com.example.kafkachat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ExceptionHandler;

@SpringBootApplication
public class KafkaChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaChatApplication.class, args);
	}

}
