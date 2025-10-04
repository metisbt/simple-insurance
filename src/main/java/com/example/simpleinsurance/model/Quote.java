package com.example.simpleinsurance.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.math.BigDecimal;
import java.time.LocalDateTime;


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

    @CreatedDate
    private LocalDateTime creationDate;

    @LastModifiedBy
    private LocalDateTime lastModificationDate;
}
