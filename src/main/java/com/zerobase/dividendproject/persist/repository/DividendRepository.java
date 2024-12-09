package com.zerobase.dividendproject.persist.repository;

import com.zerobase.dividendproject.model.Dividend;
import com.zerobase.dividendproject.persist.entity.DividendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DividendRepository extends JpaRepository<DividendEntity, Integer> {

    @Transactional
    void deleteAllByCompanyId(Long id);

    List<DividendEntity> findAllByCompanyId(Long companyId);
    }
