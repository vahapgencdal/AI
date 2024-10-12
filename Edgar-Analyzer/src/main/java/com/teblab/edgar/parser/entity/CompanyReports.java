package com.teblab.edgar.parser.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "companies")
@Data
public class CompanyReports {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "asset_type")
    private AssetType type;

    @Column(name = "cid_number")
    private String cidNumber;

    @Column(name = "info_url")
    private String infoUrl;
}