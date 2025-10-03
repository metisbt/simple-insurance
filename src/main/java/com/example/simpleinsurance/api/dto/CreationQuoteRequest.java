package com.example.simpleinsurance.api.dto;

import com.example.simpleinsurance.model.CoverageType;
import lombok.Data;

@Data
public class CreationQuoteRequest {

    private String name;

    private CoverageType type;

    private Long price;

    private ProviderRequest provider;
}
