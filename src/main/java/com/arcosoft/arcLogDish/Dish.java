package com.arcosoft.arcLogDish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@EnableScheduling
public class Dish {

	public static void main(String[] args) {
		SpringApplication.run(Dish.class, args);
	}


}
