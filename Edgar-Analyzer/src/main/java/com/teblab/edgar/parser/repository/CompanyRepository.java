package com.teblab.edgar.parser.repository;

import com.teblab.edgar.parser.entity.Asset;
import com.teblab.edgar.parser.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
}
