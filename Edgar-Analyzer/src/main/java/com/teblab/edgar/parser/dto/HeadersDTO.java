package com.teblab.edgar.parser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class HeadersDTO {
    private String symbol;
    private String name;
    private String lastsale;
    private String netchange;
    private String pctchange;
    private String marketCap;
}