package com.zerobase.dividendproject.model;


import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    private String ticker;
    private String name;
}
