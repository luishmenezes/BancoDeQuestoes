package com.example.BancoDeDados;

import com.example.BancoDeDados.Services.serviceChatGPT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BancoDeDadosApplication {
	@Value("${openai.key}")
	private String openaiApiKey;

	public static void main(String[] args) {
		SpringApplication.run(BancoDeDadosApplication.class, args);

	}

}
