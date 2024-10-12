package com.teblab.edgar.parser.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Table(name = "assets")
@Data
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "name")
    private String name;

    @Column(name = "last_sale")
    private String lastSale;

    @Column(name = "net_change")
    private String netChange;

    @Column(name = "pct_change")
    private String pctChange;

    @Column(name = "market_cap")
    private String marketCap;

    @Column(name = "url")
    private String url;

    @Column(name = "asset_type")
    private AssetType type;

    @Column(name = "cid_number")
    private String cidNumber;

    @Column(name = "info_url")
    private String infoUrl;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "exchange_id")
    private Exchange exchange;

    @ManyToOne
    @JoinColumn(name = "ex_sub_category_id")
    private ExSubCategory exSubCategory;

    @ManyToOne
    @JoinColumn(name = "market_cap_category_id")
    private MarketCapCategory marketCapCategory;

    @ManyToOne
    @JoinColumn(name = "recommendation_id")
    private Recommendation recommendation;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "sector_id")
    private Sector sector;

}