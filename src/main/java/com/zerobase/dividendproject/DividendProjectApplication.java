package com.zerobase.dividendproject;

import com.zerobase.dividendproject.model.Company;
import com.zerobase.dividendproject.model.ScrapedResult;
import com.zerobase.dividendproject.scraper.YahooFinanceScraper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DividendProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DividendProjectApplication.class, args);
    }

}
