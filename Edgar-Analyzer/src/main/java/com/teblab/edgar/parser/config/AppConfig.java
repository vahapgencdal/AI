package com.teblab.edgar.parser.config;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.time.Duration;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(Duration.ofSeconds(30));  // Read timeout
        factory.setConnectTimeout(Duration.ofSeconds(20)); // Connection timeout

        return new RestTemplate(factory);
    }

    // Create an ObjectMapper bean for parsing JSON
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
