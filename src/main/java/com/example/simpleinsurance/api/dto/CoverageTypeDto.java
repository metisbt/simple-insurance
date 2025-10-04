package com.example.simpleinsurance.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum CoverageTypeDto {
    HEALTH,
    CAR,
    PET;

    @JsonCreator
    public static CoverageTypeDto fromString(String value) {
        return Arrays.stream(CoverageTypeDto.values()).filter(t -> t.name().equalsIgnoreCase(value)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid coverage type: " + value));
    }
}
