package com.recipeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableMongoRepositories
@ComponentScan({"com.recipeservice.*"})
public class RecipeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeServiceApplication.class, args);
	}

}
