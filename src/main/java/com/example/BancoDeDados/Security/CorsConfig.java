package com.example.BancoDeDados.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("https://site-questoes-theta.vercel.app","http://localhost:3000","http://bancodequestoes-production.up.railway.app","http://localhost:8080").allowedMethods("*");
    }
}

