package com.example.simpleinsurance.api.dto;

import com.example.simpleinsurance.model.CoverageType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreationQuoteRequest {

    private String name;

    private CoverageTypeDto type;

    private BigDecimal price;

    private ProviderRequest provider;
}
