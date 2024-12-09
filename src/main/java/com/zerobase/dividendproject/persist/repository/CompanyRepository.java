package com.zerobase.dividendproject.persist.repository;

import com.zerobase.dividendproject.persist.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {

    boolean existsByTicker(String ticker);
}
