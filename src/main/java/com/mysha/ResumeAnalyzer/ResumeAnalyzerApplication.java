package com.mysha.ResumeAnalyzer;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ResumeAnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumeAnalyzerApplication.class, args);
	}

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

