package com.capgemini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//http://localhost:9090/student-api/v2/api-docs - all the end points information
//http://localhost:9090/student-api/swagger-ui/
public class Client {
	public static void main(String[] args) {
		SpringApplication.run(Client.class, args);
		
	}

}
