package com.example.simpleinsurance.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;


@Entity
@Data
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private CoverageType type;

    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Provider provider;
}
