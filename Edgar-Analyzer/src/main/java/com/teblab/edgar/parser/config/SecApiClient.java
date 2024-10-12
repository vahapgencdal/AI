package com.teblab.edgar.parser.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.Duration;

@Service
@EnableRetry
public class SecApiClient {

    private final RestTemplate restTemplate;

    public SecApiClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(5))
                .setReadTimeout(Duration.ofSeconds(5))
                .additionalInterceptors(new SecApiInterceptor())
                .build();
    }

    @Retryable(value = {IOException.class}, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public String fetchData(String url) {
        return restTemplate.getForObject(url, String.class);
    }

    private static class SecApiInterceptor implements ClientHttpRequestInterceptor {
        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            HttpHeaders headers = request.getHeaders();
            headers.set(HttpHeaders.HOST, "data.sec.gov");
            headers.set(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
            headers.set(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br, zstd");
            headers.set(HttpHeaders.ACCEPT_LANGUAGE, "en-US,en;q=0.9");
            headers.set(HttpHeaders.CACHE_CONTROL, "no-cache");
            headers.set(HttpHeaders.PRAGMA, "no-cache");
            headers.set("sec-ch-ua", "\"Google Chrome\";v=\"129\", \"Not=A?Brand\";v=\"8\", \"Chromium\";v=\"129\"");
            headers.set("sec-ch-ua-mobile", "?0");
            headers.set("sec-ch-ua-platform", "\"Windows\"");
            headers.set("sec-fetch-dest", "document");
            headers.set("sec-fetch-mode", "navigate");
            headers.set("sec-fetch-site", "none");
            headers.set("sec-fetch-user", "?1");
            headers.set("upgrade-insecure-requests", "1");
            headers.set(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36");

            return execution.execute(request, body);
        }
    }
}