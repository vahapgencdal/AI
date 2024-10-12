package com.teblab.edgar.parser.repository;

import com.teblab.edgar.parser.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AssetRepository extends JpaRepository<Asset, UUID> {
}
