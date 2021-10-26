package com.tanmay.simple3;



import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import com.tanmay.simple3.domain.Role;
import com.tanmay.simple3.repositories.RoleRepository;



@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Simple3Application {

	public static void main(String[] args) {
		SpringApplication.run(Simple3Application.class, args);
	}

	 @Bean
	    CommandLineRunner init(RoleRepository roleRepository) {

	        return args -> {

	            Role adminRole = roleRepository.findByRole("ADMIN");
	            if (adminRole == null) {
	                Role newAdminRole = new Role();
	                newAdminRole.setRole("ADMIN");
	                roleRepository.save(newAdminRole);
	            }

	            Role userRole = roleRepository.findByRole("USER");
	            if (userRole == null) {
	                Role newUserRole = new Role();
	                newUserRole.setRole("USER");
	                roleRepository.save(newUserRole);
	            }
	        };

	    }
}
