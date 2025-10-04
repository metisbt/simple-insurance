package com.example.simpleinsurance.api.mapper;

import com.example.simpleinsurance.api.dto.CreationQuoteRequest;
import com.example.simpleinsurance.api.dto.QuoteResponse;
import com.example.simpleinsurance.api.dto.UpdateQuoteRequest;
import com.example.simpleinsurance.model.Quote;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {ProviderMapper.class})
public interface QuoteMapper {

    @Mapping(source = "type", target = "type")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "provider", target = "provider")
    QuoteResponse toResponse(Quote quote);

    @Mapping(source = "type", target = "type")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "provider", target = "provider")
    Quote toEntity(CreationQuoteRequest creationQuoteRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateQuoteFromDto(UpdateQuoteRequest dto, @MappingTarget Quote quote);
}
