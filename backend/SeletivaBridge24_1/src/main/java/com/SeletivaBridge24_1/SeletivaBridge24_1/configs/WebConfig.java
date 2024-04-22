package com.SeletivaBridge24_1.SeletivaBridge24_1.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Permitir a conexão com o frontend

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	
	public void addCorsMapping(CorsRegistry registry) {
		registry.addMapping("/**"); // Poderiam ser adicionadas restrições
	}
	
}
