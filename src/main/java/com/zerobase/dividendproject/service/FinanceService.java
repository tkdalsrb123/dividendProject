package com.zerobase.dividendproject.service;

import com.zerobase.dividendproject.model.Company;
import com.zerobase.dividendproject.model.Dividend;
import com.zerobase.dividendproject.model.ScrapedResult;
import com.zerobase.dividendproject.persist.entity.CompanyEntity;
import com.zerobase.dividendproject.persist.entity.DividendEntity;
import com.zerobase.dividendproject.persist.repository.CompanyRepository;
import com.zerobase.dividendproject.persist.repository.DividendRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class FinanceService {

    private final CompanyRepository companyRepository;

    private final DividendRepository dividendRepository;

    public ScrapedResult getDividendByGetCompanyName(String companyName) {
        CompanyEntity company = this.companyRepository.findByName(companyName)
                .orElseThrow(() -> new RuntimeException("Company not found, companyName = " + companyName));

        List<DividendEntity> dividendEntities = this.dividendRepository.findAllByCompanyId(company.getId());

        List<Dividend> dividends = new ArrayList<>();
        for (var entity : dividendEntities) {
            dividends.add(new Dividend(entity.getDate(), entity.getDividend()));
        }

        return new ScrapedResult(new Company(company.getTicker(), company.getName()), dividends);

    }
}
