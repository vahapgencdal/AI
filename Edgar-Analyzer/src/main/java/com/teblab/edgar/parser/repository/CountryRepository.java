package com.teblab.edgar.parser.repository;

import com.teblab.edgar.parser.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CountryRepository extends JpaRepository<Country, UUID> {
}
