package com.teblab.edgar.parser.controller;

import com.teblab.edgar.parser.service.CompanyService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/fillSubmissions")
    public List<JSONArray> companiesFromNasdaq() {
        try {
            return companyService.fillSubmissions();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch stock data", e);
        }
    }
}
