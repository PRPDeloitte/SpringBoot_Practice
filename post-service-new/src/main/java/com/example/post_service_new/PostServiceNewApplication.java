package com.example.post_service_new;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PostServiceNewApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostServiceNewApplication.class, args);
	}

}
