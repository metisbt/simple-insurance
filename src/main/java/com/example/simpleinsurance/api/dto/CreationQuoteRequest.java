package com.example.simpleinsurance.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreationQuoteRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Coverage type is required")
    private CoverageTypeDto type;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private BigDecimal price;

    @Valid
    @NotNull(message = "Provider is required")
    private ProviderRequest provider;
}
