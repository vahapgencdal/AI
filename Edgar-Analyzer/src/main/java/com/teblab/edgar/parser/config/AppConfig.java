package com.teblab.edgar.parser.config;

import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        // Configuring the request factory to handle gzip encoding
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(
                HttpClients.custom()
                        .build()
        );

        RestTemplate restTemplate = new RestTemplate(factory);
        return restTemplate;
    }

    // Create an ObjectMapper bean for parsing JSON
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
