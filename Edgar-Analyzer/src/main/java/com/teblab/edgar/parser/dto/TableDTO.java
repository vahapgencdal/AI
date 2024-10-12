package com.teblab.edgar.parser.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class TableDTO {
    @JsonProperty("asOf")
    private String asOf;
    @JsonProperty("headers")
    private HeadersDTO headers;
    @JsonProperty("rows")
    private List<RowDTO> rows;

}