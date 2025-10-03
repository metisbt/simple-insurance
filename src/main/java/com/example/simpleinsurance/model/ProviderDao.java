package com.example.simpleinsurance.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderDao extends JpaRepository<Provider, Long> {
    Provider findByName(String name);
}
