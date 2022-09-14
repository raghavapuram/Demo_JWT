package com.demo.doman.car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
public class CarApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(CarApplication.class, args);
	}

}
