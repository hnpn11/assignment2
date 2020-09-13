package com.fpt.assignment2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.fpt.assignment2")
@SpringBootApplication(scanBasePackages = "com.fpt.assignment2")
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
}
