package com.devjck.springboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableJpaAuditing
@EnableWebMvc
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class SpringboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringboardApplication.class, args);
	}

}
