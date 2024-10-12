package com.teblab.edgar.parser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class FilterItemDTO {
    @JsonProperty("name")
    private String name;
    @JsonProperty("value")
    private String value;


}