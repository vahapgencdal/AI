package com.teblab.edgar.parser.dto;

import com.teblab.edgar.parser.entity.*;

public class NasdaqApiEntityParser {

    public static  Country parseFilterToCountry(FilterItemDTO filter){
        Country country = new Country();
        country.setName(filter.getName());
        country.setUniqueName(filter.getValue());
        return country;
    }

    public static FilterItemDTO parseCountryToFilter(Country country){
        FilterItemDTO filter = new FilterItemDTO();
        filter.setName(country.getName());
        filter.setValue(country.getUniqueName());
        return filter;
    }

    public static  Exchange parseFilterToExchange(FilterItemDTO filter){
        Exchange exchange = new Exchange();
        exchange.setName(filter.getName());
        exchange.setUniqueName(filter.getValue());
        return exchange;
    }

    public static FilterItemDTO parseExchangeToFilter(Exchange exchange){
        FilterItemDTO filter = new FilterItemDTO();
        filter.setName(exchange.getName());
        filter.setValue(exchange.getUniqueName());
        return filter;
    }

    public static ExSubCategory parseFilterToExSubCategory(FilterItemDTO filter){
        ExSubCategory exSubCategory = new ExSubCategory();
        exSubCategory.setName(filter.getName());
        exSubCategory.setUniqueName(filter.getValue());
        return exSubCategory;
    }

    public static MarketCapCategory parseFilterToMarketCapCategory(FilterItemDTO filter){
        MarketCapCategory marketCapCategory = new MarketCapCategory();
        marketCapCategory.setName(filter.getName());
        marketCapCategory.setUniqueName(filter.getValue());
        return marketCapCategory;
    }

    public static Recommendation parseFilterToRecommendation(FilterItemDTO filter){
        Recommendation recommendation = new Recommendation();
        recommendation.setName(filter.getName());
        recommendation.setUniqueName(filter.getValue());
        return recommendation;
    }

    public static Region parseFilterToRegion(FilterItemDTO filter){
        Region region = new Region();
        region.setName(filter.getName());
        region.setUniqueName(filter.getValue());
        return region;
    }

    public static Sector parseFilterToSector(FilterItemDTO filter){
        Sector sector = new Sector();
        sector.setName(filter.getName());
        sector.setUniqueName(filter.getValue());
        return sector;
    }

    public static Asset parseStockAsset(RowDTO rowDTO) {
        Asset asset = new Asset();
        asset.setSymbol(rowDTO.getSymbol());
        asset.setName(rowDTO.getName());
        asset.setLastSale(rowDTO.getLastsale());
        asset.setNetChange(rowDTO.getNetchange());
        asset.setPctChange(rowDTO.getPctchange());
        asset.setMarketCap(rowDTO.getMarketCap());
        asset.setUrl(rowDTO.getUrl());
        asset.setType(AssetType.STOCKS);
        asset.setInfoUrl("https://api.nasdaq.com/api/quote/"+rowDTO.getSymbol()+"/info?assetclass=stocks");
        return asset;
    }

    //parse com.teblab.edgar.parser.dto.FillingDto to CompanyFilling
    public static CompanyFilling parseFillingToCompanyFilling(FillingDTO fillingDTO){
        CompanyFilling companyFilling = new CompanyFilling();
        companyFilling.setAccessionNumber(fillingDTO.getAccessionNumber());
        companyFilling.setFilingDate(fillingDTO.getFilingDate());
        companyFilling.setReportDate(fillingDTO.getReportDate());
        companyFilling.setForm(fillingDTO.getForm());
        companyFilling.setPrimaryDocument(fillingDTO.getPrimaryDocument());
        companyFilling.setPrimaryDocDescription(fillingDTO.getPrimaryDocDescription());
        companyFilling.setSymbol(fillingDTO.getSymbol());
        companyFilling.setCikNumber(fillingDTO.getCikNumber());
        companyFilling.setOld(fillingDTO.isOld());
        return companyFilling;
    }

}
