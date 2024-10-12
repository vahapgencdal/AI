package com.teblab.edgar.parser.dto;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

@lombok.Data
@lombok.NoArgsConstructor
public class FillingDTO {
    private String accessionNumber;
    private LocalDate filingDate;
    private LocalDate reportDate;
    private String form;
    private String primaryDocument;
    private String primaryDocDescription;
    private String symbol;
    private String cikNumber;
    private boolean isOld;

    public FillingDTO(String accessionNumber, LocalDate filingDate, LocalDate reportDate, String form, String primaryDocument, String primaryDocDescription, String symbol, String cikNumber, boolean isOld) {
        this.accessionNumber = accessionNumber;
        this.filingDate = filingDate;
        this.reportDate = reportDate;
        this.form = form;
        this.primaryDocument = primaryDocument;
        this.primaryDocDescription = primaryDocDescription;
        this.symbol = symbol;
        this.cikNumber = cikNumber;
        this.isOld = isOld;
    }
}
