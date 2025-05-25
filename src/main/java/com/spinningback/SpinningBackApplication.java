package com.spinningback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpinningBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpinningBackApplication.class, args);
		
	}
	
}
