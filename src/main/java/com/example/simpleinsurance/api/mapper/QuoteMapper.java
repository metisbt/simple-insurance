package com.example.simpleinsurance.api.mapper;

import com.example.simpleinsurance.api.dto.CreationQuoteRequest;
import com.example.simpleinsurance.api.dto.QuoteResponse;
import com.example.simpleinsurance.api.dto.UpdateQuoteRequest;
import com.example.simpleinsurance.model.Quote;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {ProviderMapper.class})
public interface QuoteMapper {

    QuoteResponse toResponse(Quote quote);

    Quote toEntity(CreationQuoteRequest creationQuoteRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateQuoteFromDto(UpdateQuoteRequest dto, @MappingTarget Quote quote);
}
