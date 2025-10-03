package com.example.simpleinsurance.api.mapper;

import com.example.simpleinsurance.api.dto.ProviderRequest;
import com.example.simpleinsurance.api.dto.ProviderResponse;
import com.example.simpleinsurance.model.Provider;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProviderMapper {

    ProviderResponse toResponse(Provider provider);

    Provider toEntity(ProviderRequest providerRequest);
}