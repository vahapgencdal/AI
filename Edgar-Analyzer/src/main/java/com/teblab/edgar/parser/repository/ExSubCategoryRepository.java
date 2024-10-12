package com.teblab.edgar.parser.repository;

import com.teblab.edgar.parser.entity.ExSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExSubCategoryRepository extends JpaRepository<ExSubCategory, UUID> {
}
