package com.example.simpleinsurance.api.dto;

import com.example.simpleinsurance.model.CoverageType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class QuoteResponse {

    private Long id;
    private String name;
    private CoverageType type;
    private ProviderResponse provider;
    private BigDecimal price;
}
