package com.multi.contactsapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            	registry.addMapping("/**")
        			.allowedOrigins("http://jcornor.com:8000", "http://localhost:8000")
        			.allowCredentials(true)
        			.allowedMethods("GET", "POST", "PUT","DELETE", "HEAD", "OPTIONS")
        			.allowedHeaders("Origin", "Content-Type", "X-Requested-With", "Accept", 
        					"Access-Control-Request-Method", "Access-Control-Request-Headers", "Authorization")
        			.exposedHeaders("Origin", "Access-Control-Request-Method", "Access-Control-Allow-Origin", 
        					"Access-Control-Allow-Credentials")
        			.maxAge(3600);
            }
        };
    }
}
