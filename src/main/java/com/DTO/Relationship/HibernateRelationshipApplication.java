package com.DTO.Relationship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HibernateRelationshipApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateRelationshipApplication.class, args);
	}

}
