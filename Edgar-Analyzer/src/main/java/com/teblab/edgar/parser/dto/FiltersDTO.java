package com.teblab.edgar.parser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class FiltersDTO {

    @JsonProperty("region")
    private List<FilterItemDTO> region;

    @JsonProperty("country")
    private List<FilterItemDTO> country;

    @JsonProperty("exchange")
    private List<FilterItemDTO> exchange;

    @JsonProperty("sector")
    private List<FilterItemDTO> sector;

    @JsonProperty("recommendation")
    private List<FilterItemDTO> recommendation;

    @JsonProperty("marketcap")
    private List<FilterItemDTO> marketcap;

    @JsonProperty("exsubcategory")
    private List<FilterItemDTO> exsubcategory;
}