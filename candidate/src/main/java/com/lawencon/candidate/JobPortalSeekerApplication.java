package com.lawencon.candidate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.lawencon"})
@EntityScan(basePackages = {
		"com.lawencon"
		})

public class JobPortalSeekerApplication {
    
    public static void main(String[] args) {
		SpringApplication.run(JobPortalSeekerApplication.class, args);
	}
}
