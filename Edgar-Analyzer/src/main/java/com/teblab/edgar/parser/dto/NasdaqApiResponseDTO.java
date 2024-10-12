package com.teblab.edgar.parser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class NasdaqApiResponseDTO {
    @JsonProperty("data")
    private StockDataDTO data;
    @JsonProperty("message")
    private String message;
    @JsonProperty("status")
    private MessageDTO status;
}
