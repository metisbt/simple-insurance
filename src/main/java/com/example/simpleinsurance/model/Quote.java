package com.example.simpleinsurance.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private CoverageType type;

    private Long price;

    @ManyToOne
    private Provider provider;
}
