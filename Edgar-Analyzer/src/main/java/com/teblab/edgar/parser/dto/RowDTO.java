package com.teblab.edgar.parser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RowDTO {
    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("name")
    private String name;

    @JsonProperty("lastsale")
    private String lastsale;

    @JsonProperty("netchange")
    private String netchange;

    @JsonProperty("pctchange")
    private String pctchange;

    @JsonProperty("marketCap")
    private String marketCap;

    @JsonProperty("url")
    private String url;
}