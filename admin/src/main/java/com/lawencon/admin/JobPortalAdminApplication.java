package com.lawencon.admin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.lawencon"})
@EntityScan(basePackages = {
		"com.lawencon"
		})

public class JobPortalAdminApplication {
    
    public static void main(String[] args) {
		SpringApplication.run(JobPortalAdminApplication.class, args);
	}
}
