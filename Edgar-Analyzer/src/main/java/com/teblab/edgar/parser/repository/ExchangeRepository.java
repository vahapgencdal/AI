package com.teblab.edgar.parser.repository;

import com.teblab.edgar.parser.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExchangeRepository extends JpaRepository<Exchange, UUID> {
}
