package com.teblab.edgar.parser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MessageDTO {

    @JsonProperty("rCode")
    private int rCode;

    @JsonProperty("bCodeMessage")
    private String bCodeMessage;

    @JsonProperty("developerMessage")
    private String developerMessage;

    public MessageDTO() {
    }

}
