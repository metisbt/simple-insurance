package com.example.simpleinsurance.service;

import com.example.simpleinsurance.api.dto.CreationQuoteRequest;
import com.example.simpleinsurance.api.dto.QuoteResponse;
import com.example.simpleinsurance.api.dto.UpdateQuoteRequest;
import com.example.simpleinsurance.api.mapper.ProviderMapper;
import com.example.simpleinsurance.api.mapper.QuoteMapper;
import com.example.simpleinsurance.exception.ResourceNotFoundException;
import com.example.simpleinsurance.model.Provider;
import com.example.simpleinsurance.model.ProviderDao;
import com.example.simpleinsurance.model.Quote;
import com.example.simpleinsurance.model.QuoteDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;


@Service
@Slf4j
public class QuoteServiceImpl implements QuoteService {

    private final QuoteDao quoteDao;
    private final ProviderDao providerDao;
    private final QuoteMapper quoteMapper;

    private final ProviderMapper providerMapper;

    public QuoteServiceImpl(QuoteDao quoteDao, ProviderDao providerDao, QuoteMapper quoteMapper, ProviderMapper providerMapper) {
        this.quoteDao = quoteDao;
        this.providerDao = providerDao;
        this.quoteMapper = quoteMapper;
        this.providerMapper = providerMapper;
        log.info("QuoteService initialized.");
    }

    @Override
    public List<QuoteResponse> getAllQuotes() {
        log.info("Fetching all quotes.");
        return quoteDao.findAll().stream().map(quoteMapper::toResponse).toList();
    }

    @Override
    public QuoteResponse getQuoteById(Long id) {
        log.info("Fetching quote by ID: {}", id);
        Quote quote = quoteDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Quote not found with id : " + id));
        return quoteMapper.toResponse(quote);
    }

    @Transactional
    @Override
    public QuoteResponse createQuote(CreationQuoteRequest request) {
        log.info("Attempting to create a new quote for coverage type: {}", request.getType());
        Provider providerExists = providerDao.findByName(request.getProvider().getName());

        if (providerExists == null) {
            log.info("Provider with name {} not found, creating new provider.",  request.getProvider().getName());
            providerExists = providerMapper.toEntity(request.getProvider());
            providerExists = providerDao.save(providerExists);
        }
        Quote quote = quoteMapper.toEntity(request);
        quote.setProvider(providerExists);
        log.info("Quote created successfully with ID: {}", providerExists.getId());

        return quoteMapper.toResponse(quoteDao.saveAndFlush(quote));
    }

    @Transactional
    @Override
    public QuoteResponse updateQuote(Long id, UpdateQuoteRequest request){
        log.info("Attempting to update quote with ID: {}", id);
        Quote quote = quoteDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Quote not found with id: " + id));
        quoteMapper.updateQuoteFromDto(request, quote);
        log.info("Quote with ID {} updated successfully.", id);
        return quoteMapper.toResponse(quoteDao.save(quote));
    }

    @Override
    public void deleteQuote(Long id){
        log.info("Attempting to delete quote with ID: {}", id);
        Quote quote = quoteDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Quote not found with id: " + id));
        quoteDao.delete(quote);
        log.info("Quote deleted successfully with ID: {}", id);
    }

    @Override
    public QuoteResponse getBestQuote() {
        log.info("Calculating best quote from database for aggregation.");
        Quote quote = quoteDao.findAll().stream().min(Comparator.comparing(Quote::getPrice))
                .orElseThrow(() -> new ResourceNotFoundException("Quotes not found!"));
        return quoteMapper.toResponse(quote);
    }
}
