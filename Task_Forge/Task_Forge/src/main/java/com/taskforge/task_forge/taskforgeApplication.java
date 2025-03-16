package com.taskforge.task_forge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages = "com.taskforge.task_forge.Repository")
@EntityScan(basePackages = "com.taskforge.task_forge.Model")
@SpringBootApplication
public class taskforgeApplication {
	public static void main(String[] args) {
		SpringApplication.run(taskforgeApplication.class, args);
	}
}
