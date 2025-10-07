package com.example.simpleinsurance.api.dto;

import com.example.simpleinsurance.model.CoverageType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class QuoteResponse {

    private Long id;
    private String name;
    private CoverageTypeDto type;
    private ProviderResponse provider;
    private BigDecimal price;
    private LocalDateTime creationDate;
    private LocalDateTime lastModificationDate;
}
