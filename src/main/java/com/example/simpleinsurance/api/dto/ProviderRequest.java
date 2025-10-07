package com.example.simpleinsurance.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProviderRequest {

    @NotBlank(message = "Provider name is required")
    private String name;
}
