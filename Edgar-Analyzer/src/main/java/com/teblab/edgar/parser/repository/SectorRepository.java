package com.teblab.edgar.parser.repository;

import com.teblab.edgar.parser.entity.Exchange;
import com.teblab.edgar.parser.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SectorRepository extends JpaRepository<Sector, UUID> {
}