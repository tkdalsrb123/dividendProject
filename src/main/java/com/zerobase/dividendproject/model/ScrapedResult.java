package com.zerobase.dividendproject.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ScrapedResult {

    private Company company;

    private List<Dividend> dividendList;

    public ScrapedResult() {
        this.dividendList = new ArrayList<>();
    }
}
