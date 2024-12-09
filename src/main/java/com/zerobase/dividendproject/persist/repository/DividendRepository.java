package com.zerobase.dividendproject.persist.repository;

import com.zerobase.dividendproject.model.Dividend;
import com.zerobase.dividendproject.persist.entity.DividendEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DividendRepository extends JpaRepository<DividendEntity, Integer> {
}
