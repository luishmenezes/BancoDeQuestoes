package com.example.BancoDeDados;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BancoDeDadosApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoDeDadosApplication.class, args);

	}

}
