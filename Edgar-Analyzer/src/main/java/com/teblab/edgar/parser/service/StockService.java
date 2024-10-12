package com.teblab.edgar.parser.service;

import com.teblab.edgar.parser.dto.*;
import com.teblab.edgar.parser.entity.*;
import com.teblab.edgar.parser.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@Service
public class StockService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final AssetRepository assetRepository;
    private final CountryRepository countryRepository;
    private final ExchangeRepository exchangeRepository;
    private final ExSubCategoryRepository exSubCategoryRepository;
    private final MarketCapCategoryRepository marketCapCategoryRepository;
    private final  RecommendationRepository recommendationRepository;
    private final  RegionRepository regionRepository;
    private final  SectorRepository sectorRepository;
    private final  TickerService tickerService;


    private static final int LIMIT = 100;

    @Value("${nasdaq.com.stocks.api.url}")
    private String apiUrl;

    public StockService(RestTemplate restTemplate, ObjectMapper objectMapper, AssetRepository assetRepository,
                        CountryRepository countryRepository, ExchangeRepository exchangeRepository, ExSubCategoryRepository exSubCategoryRepository,
                        MarketCapCategoryRepository marketCapCategoryRepository, RecommendationRepository recommendationRepository,
                        RegionRepository regionRepository, SectorRepository sectorRepository, TickerService tickerService) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.assetRepository = assetRepository;
        this.countryRepository = countryRepository;
        this.exchangeRepository = exchangeRepository;
        this.exSubCategoryRepository = exSubCategoryRepository;
        this.marketCapCategoryRepository = marketCapCategoryRepository;
        this.recommendationRepository = recommendationRepository;
        this.regionRepository = regionRepository;
        this.sectorRepository = sectorRepository;
        this.tickerService = tickerService;
    }

    public List<Asset> getStockData() throws IOException {

        // Make the API request
        String response = restTemplate.getForObject(apiUrl+"?tableonly=false&limit="+LIMIT+"&offset=0", String.class);

        // Parse JSON response into StockDataDTO
        JsonNode root = objectMapper.readTree(response);
        JsonNode dataNode = root.path("data");

        // Map the JSON data to StockDataDTO
        StockDataDTO stockDataDTO = objectMapper.treeToValue(dataNode, StockDataDTO.class);

        FiltersDTO filters = stockDataDTO.getFilters();

        List<Region> regions = filters.getRegion().stream().map(NasdaqApiEntityParser::parseFilterToRegion).toList();
        List<Country> countries = filters.getCountry().stream().map(NasdaqApiEntityParser::parseFilterToCountry).toList();
        List<Exchange> exchanges = filters.getExchange().stream().map(NasdaqApiEntityParser::parseFilterToExchange).toList();
        List<Sector> sectors = filters.getSector().stream().map(NasdaqApiEntityParser::parseFilterToSector).toList();
        List<Recommendation> recommendations = filters.getRecommendation().stream().map(NasdaqApiEntityParser::parseFilterToRecommendation).toList();
        List<MarketCapCategory> marketCapCategories = filters.getMarketcap().stream().map(NasdaqApiEntityParser::parseFilterToMarketCapCategory).toList();
        List<ExSubCategory> exSubCategories = filters.getExsubcategory().stream().map(NasdaqApiEntityParser::parseFilterToExSubCategory).toList();

        List<Asset> assets = stockDataDTO.getTable().getRows().stream().map(NasdaqApiEntityParser::parseStockAsset).toList();

        regionRepository.saveAll(regions);
        countryRepository.saveAll(countries);
        exchangeRepository.saveAll(exchanges);
        sectorRepository.saveAll(sectors);
        recommendationRepository.saveAll(recommendations);
        marketCapCategoryRepository.saveAll(marketCapCategories);
        exSubCategoryRepository.saveAll(exSubCategories);

        assetRepository.saveAll(assets);

        int totalRecords = stockDataDTO.getTotalrecords();

        int numberOfPages = totalRecords/LIMIT+1;

        for (int i = 0; i < numberOfPages; i++) {
            String url = apiUrl+"?tableonly=false&limit="+LIMIT+"&offset="+LIMIT*(i+1);
            System.out.println(url);
            response = restTemplate.getForObject(url, String.class);
            root = objectMapper.readTree(response);
            dataNode = root.path("data");
            stockDataDTO = objectMapper.treeToValue(dataNode, StockDataDTO.class);
            if (stockDataDTO.getTable().getRows() != null){
                assets = stockDataDTO.getTable().getRows().stream().map(NasdaqApiEntityParser::parseStockAsset).toList();
                assetRepository.saveAll(assets);
            }
        }


        return assetRepository.findAll();
    }

    public List<Asset> fetchAll() {
        return assetRepository.findAll();
    }

    public boolean matchCidNumbers() {
        List<TickerDTO> tickers = tickerService.readFileFromResources().stream().map(parts -> new TickerDTO(parts[0], parts[1])).toList();
        List<Asset> assets = assetRepository.findAll();
        for (Asset asset : assets) {
            for (TickerDTO ticker : tickers) {
                if (asset.getSymbol().equalsIgnoreCase(ticker.getSymbol())) {
                    asset.setCidNumber(ticker.getNumber());
                }
            }
        }
        assetRepository.saveAll(assets);
        return true;
    }
}
