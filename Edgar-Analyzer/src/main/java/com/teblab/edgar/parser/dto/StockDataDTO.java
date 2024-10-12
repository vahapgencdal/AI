package com.teblab.edgar.parser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class StockDataDTO {
    @JsonProperty("filters")
    private FiltersDTO filters;

    @JsonProperty("table")
    private TableDTO table;

    @JsonProperty("totalrecords")
    private int totalrecords;

    @JsonProperty("asof")
    private String asof;
}
