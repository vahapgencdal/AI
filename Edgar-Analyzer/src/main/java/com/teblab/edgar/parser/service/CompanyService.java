package com.teblab.edgar.parser.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teblab.edgar.parser.dto.FillingDTO;
import com.teblab.edgar.parser.dto.NasdaqApiEntityParser;
import com.teblab.edgar.parser.dto.TickerDTO;
import com.teblab.edgar.parser.entity.Company;
import com.teblab.edgar.parser.entity.CompanyFilling;
import com.teblab.edgar.parser.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class CompanyService {

    private final TickerService tickerService;
    private final CompanyRepository companyRepository;

    public CompanyService(TickerService tickerService, CompanyRepository companyRepository) {
        this.tickerService = tickerService;
        this.companyRepository = companyRepository;
    }

    public List<JSONArray> fillSubmissions() throws IOException {
        List<TickerDTO> tickers = new ArrayList<>(); //tickerService.readFileFromResources().stream().map(parts -> new TickerDTO(parts[0], parts[1])).toList();

        List<JSONArray> reportsTable= new ArrayList<>();

        TickerDTO tickerDTO = new TickerDTO();
        tickerDTO.setNumber("320193");
        tickers.add(tickerDTO);

        //tickers = tickers.stream().filter(tickerDTO -> tickerDTO.getNumber().equals("320193") || tickerDTO.getNumber().equals("1045810")).toList();

        for (TickerDTO ticker : tickers) {
            String submissionUrl = tickerService.getSubmissionUrl(ticker.getNumber());
            String submission001Url = tickerService.getSubmission001Url(ticker.getNumber());

            String submission = tickerService.getSubmission(submissionUrl);
            String submission001 = tickerService.getSubmission(submission001Url);

            //List<FillingDTO> fillingDTOList = parseJSONData(submission, ticker, false);
            //List<FillingDTO> oldFillingDTOList= parseJSONData(submission001, ticker, true);

            JSONArray jsonArray =  getReportsTable(submission,ticker.getNumber());

            reportsTable.add(jsonArray);

           // List<CompanyFilling> fillingList = new ArrayList<>();

           // List<CompanyFilling> newFillingList = fillingDTOList.stream().map(NasdaqApiEntityParser::parseFillingToCompanyFilling).toList();
          //  List<CompanyFilling> oldFillingList = oldFillingDTOList.stream().map(NasdaqApiEntityParser::parseFillingToCompanyFilling).toList();

          //  fillingList.addAll(newFillingList);
          //  fillingList.addAll(oldFillingList);

          //  Company company = new Company();
          //  company.setCidNumber(ticker.getNumber());
         //   company.setSymbol(ticker.getSymbol());
         //   company.setCompanyFillings(fillingList);
         //   company.setSubmissionBlobNewUrl(submissionUrl);
         //   company.setSubmissionBlobOldUrl(submission001Url);

           // companyRepository.save(company);

        }

        //we will make loop for all tickers and get data from https://data.sec.gov/submissions/CIK0000320193.json
        //and https://data.sec.gov/submissions/CIK0000320193-submissions-001.json
        // we will parse this json files and save reports endpoint in Company Reports table


        //https://data.sec.gov/submissions/CIK0000320193.json
        //https://data.sec.gov/submissions/CIK0000320193-submissions-001.json
        //https://data.sec.gov/submissions/CIK0001584509-submissions-001.json

        return reportsTable;
    }

    public List<FillingDTO> parseJSONData(String jsonData, TickerDTO tickerDTO, boolean isOld) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonData);
        JsonNode filings = root.path("filings").path("recent");

        List<FillingDTO> filingList = new ArrayList<>();

        for (int i = 0; i < filings.path("accessionNumber").size(); i++) {
            FillingDTO filing = new FillingDTO(
                    filings.path("accessionNumber").get(i).asText(),
                    null,
                    null,
                    filings.path("form").get(i).asText(),
                    filings.path("primaryDocument").get(i).asText(),
                    filings.path("primaryDocDescription").get(i).asText(),
                    tickerDTO.getSymbol(),
                    tickerDTO.getNumber(),
                    isOld
            );
            filingList.add(filing);
        }

        filingList.sort(Comparator.comparing(FillingDTO::getFilingDate).reversed());
        return filingList;
    }

    public JSONArray getReportsTable(String jsonContent, String cik) {

            if (cik.length() < 10) {
                cik = "0".repeat(10 - cik.length()) + cik;
            }

            JSONObject jsonObject = new JSONObject(jsonContent);

            JSONArray accessionNumbers = jsonObject.getJSONObject("filings").getJSONObject("recent").getJSONArray("accessionNumber");
            JSONArray filingDates = jsonObject.getJSONObject("filings").getJSONObject("recent").getJSONArray("filingDate");
            String ticker = jsonObject.getJSONArray("tickers").getString(0);  // e.g., "AAPL"
            String fiscalYearEnd = jsonObject.getString("fiscalYearEnd");  // e.g., "0928"

            // Create a JSONArray for the resulting table
            JSONArray jsonTable = new JSONArray();

            // Loop through filings and create JSON rows
            for (int i = 0; i < accessionNumbers.length(); i++) {
                String accessionNumber = accessionNumbers.getString(i);
                String filingDate = filingDates.getString(i);

                // Extract the year and create the document name
                String filingYear = filingDate.substring(0, 4);
                String documentName = ticker.toLowerCase() + "-" + filingYear + fiscalYearEnd + ".htm";

                // Construct the URL
                String secUrl = "https://www.sec.gov/ix?doc=/Archives/edgar/data/" + cik + "/"
                        + accessionNumber.replace("-", "") + "/" + documentName;

                // Create a JSON object for the row
                JSONObject jsonRow = new JSONObject();
                jsonRow.put("form_type", accessionNumber);
                jsonRow.put("form_description", secUrl);
                jsonRow.put("filing_date", filingDate);
                jsonRow.put("reporting_date", "N/A");  // Assuming reporting date is N/A for now

                // Add the row to the table
                jsonTable.put(jsonRow);
            }

            return jsonTable;

    }

}
