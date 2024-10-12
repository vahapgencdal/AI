package com.teblab.edgar.parser.service;

import com.teblab.edgar.parser.config.SecApiClient;
import com.teblab.edgar.parser.dto.TickerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.io.ClassPathResource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class TickerService {
    @Value("${nasdaq.com.stocks.api.cid.url}")
    private String TICKER_URL;

    private final RestTemplate tickerRestTemplate;
    private final SecApiClient secApiClient;


    @Autowired
    public TickerService(RestTemplate tickerRestTemplate, SecApiClient secApiClient) {
        this.tickerRestTemplate = tickerRestTemplate;
        this.secApiClient = secApiClient;
    }

    public List<TickerDTO> fetchTickers() {
        // Make GET request and get the response body as a String
        String responseBody = tickerRestTemplate.getForObject(TICKER_URL, String.class);

        // Split the response by line breaks (each line is a record)
        String[] lines = responseBody.split("\n");

        // Create a list to hold the ticker DTOs
        List<TickerDTO> tickerList = new ArrayList<>();

        // Process each line and split by tab (\t) to get the ticker symbol and number
        for (String line : lines) {
            String[] parts = line.split("\t");
            if (parts.length == 2) {
                // Add the ticker and number as a TickerDTO
                tickerList.add(new TickerDTO(parts[0], parts[1]));
            }
        }

        return tickerList;
    }

    public List<String[]> readFileFromResources() {
        List<String[]> lines = new ArrayList<>();

        try {
            // Load file from the resources/static directory
            ClassPathResource resource = new ClassPathResource("static/ticker.txt");

            // Read the file
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));

            String line;
            while ((line = reader.readLine()) != null) {
                // Split each line into parts (assuming tab-separated)
                String[] parts = line.split("\t");
                lines.add(parts);
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lines;
    }

    public String getSubmissionUrl(String number) {
        //always make cik number 10 digits if it less put 0 in front of it
        if (number.length() < 10) {
            number = "0".repeat(10 - number.length()) + number;
        }
        //this is gona return json file
        return "https://data.sec.gov/submissions/CIK" + number + ".json";
    }


    public String getSubmission001Url(String number) {
        //always make cik number 10 digits if it less put 0 in front of it
        if (number.length() < 10) {
            number = "0".repeat(10 - number.length()) + number;
        }
        //this is gona return json file
        return "https://data.sec.gov/submissions/CIK" + number + "-submissions-001.json";
    }

    public String getSubmission(String url) {
        return secApiClient.fetchData(url);
    }
}
