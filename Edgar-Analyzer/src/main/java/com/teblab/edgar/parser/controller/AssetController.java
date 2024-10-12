package com.teblab.edgar.parser.controller;

import com.teblab.edgar.parser.dto.NasdaqApiResponseDTO;
import com.teblab.edgar.parser.entity.Asset;
import com.teblab.edgar.parser.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/assets")
public class AssetController {

    private final StockService stockService;

    @Autowired
    public AssetController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/stocks/fetchFromWebsite")
    public List<Asset> fetchFromWebsite() {
        try {
            return stockService.getStockData();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch stock data", e);
        }
    }

    @GetMapping("/stocks/fetchFromDb")
    public List<Asset> fetchFromDb() {
        try {
            return stockService.fetchAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch stock data", e);
        }
    }

    @GetMapping("/stocks/matchCidNumber")
    public boolean fetchFromNasdaq() {
        try {
            return stockService.matchCidNumbers();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch stock data", e);
        }
    }

    @GetMapping("/companies/matchCidNumber")
    public boolean companiesFromNasdaq() {
        try {
            return stockService.matchCidNumbers();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch stock data", e);
        }
    }
}
