package com.tanmay.simple3;



import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;




@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Simple3Application {

	public static void main(String[] args) {
		SpringApplication.run(Simple3Application.class, args);
	}

	
}
