package com.teblab.edgar.parser.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "company_fillings")
@Data
public class CompanyFilling {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    private String symbol;
    private String cikNumber;
    private boolean isOld;
    private String accessionNumber;
    private LocalDate filingDate;
    private LocalDate reportDate;
    private String form;
    private String primaryDocument;
    private String primaryDocDescription;

}