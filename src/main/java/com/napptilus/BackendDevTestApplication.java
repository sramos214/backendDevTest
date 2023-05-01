package com.napptilus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class BackendDevTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendDevTestApplication.class, args);
	}


}
