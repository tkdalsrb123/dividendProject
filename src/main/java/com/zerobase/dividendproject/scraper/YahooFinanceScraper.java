package com.zerobase.dividendproject.scraper;


import com.zerobase.dividendproject.model.Company;
import com.zerobase.dividendproject.model.Dividend;
import com.zerobase.dividendproject.model.ScrapedResult;
import com.zerobase.dividendproject.model.constants.Month;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class YahooFinanceScraper implements Scraper{

    private static final String TICKER_URL = "https://finance.yahoo.com/quote/%s?=%s";
    private static final String URL = "https://finance.yahoo.com/quote/%s/history/?frequency=1mo&period1=%d&period2=%d";
    @Override
    public Company scrapCompanyByTicker(String ticker) {
        String url = String.format(TICKER_URL, ticker, ticker);

        try {
            Document document = Jsoup.connect(url).get();

            String title = document.getElementsByTag("h1").get(1).text();

            return new Company(ticker, title);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ScrapedResult scrap(Company company) {
        var scrapResult = new ScrapedResult();
        scrapResult.setCompany(company);

        long now = System.currentTimeMillis() / 1000;
        String url = String.format(URL, company.getTicker(), now - 157070135, now);

        try {
            Connection connection = Jsoup.connect(url);
            Document document = connection.get();

            Elements elements = document.getElementsByAttributeValue("class", "table yf-j5d1ld noDl");
            Element element = elements.get(0);
            Element tbody = element.getElementsByTag("tbody").get(0);
            List<Dividend> dividends = new ArrayList<>();
            for (Element tr : tbody.getElementsByTag("tr")) {
                String txt = tr.text();
                if (!txt.endsWith("Dividend")) {
                    continue;
                }

                String[] split = txt.split(" ");
                int month = Month.strToNumber(split[0]);
                int day = Integer.parseInt(split[1].replace(",", ""));
                int year = Integer.parseInt(split[2]);
                String dividend = split[3];

                if (month < 0) {
                    throw new RuntimeException("Unexpected Month enum value: " + split[0]);
                }

                dividends.add(new Dividend(LocalDateTime.of(year, month, day, 0, 0), dividend));

            }

            scrapResult.setDividendList(dividends);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return scrapResult;
    }
}
