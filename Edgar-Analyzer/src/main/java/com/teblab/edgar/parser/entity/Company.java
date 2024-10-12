package com.teblab.edgar.parser.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "companies")
@Data
public class Company {
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

    @Column(name = "submission_blob_new_url")
    private String submissionBlobNewUrl;

    @Column(name = "submission_blob_old_url")
    private String submissionBlobOldUrl;

    @Column(name = "reports_new", length = 10000000)
    private String reportsNew;

    @Column(name = "reports_old" , length = 10000000)
    private String reportsOld;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<CompanyFilling> companyFillings;

}