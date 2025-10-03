package com.example.simpleinsurance.api.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateQuoteRequest {

    private String name;

    private BigDecimal price;
}
