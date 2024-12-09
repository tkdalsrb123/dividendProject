package com.zerobase.dividendproject.scraper;

import com.zerobase.dividendproject.model.Company;
import com.zerobase.dividendproject.model.ScrapedResult;

public interface Scraper {
    Company scrapCompanyByTicker(String ticker);
    ScrapedResult scrap(Company company);
}
