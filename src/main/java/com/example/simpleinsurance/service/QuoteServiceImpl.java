package com.example.simpleinsurance.service;

import com.example.simpleinsurance.api.dto.CreationQuoteRequest;
import com.example.simpleinsurance.api.dto.QuoteResponse;
import com.example.simpleinsurance.api.dto.UpdateQuoteRequest;
import com.example.simpleinsurance.api.mapper.ProviderMapper;
import com.example.simpleinsurance.api.mapper.QuoteMapper;
import com.example.simpleinsurance.model.Provider;
import com.example.simpleinsurance.model.ProviderDao;
import com.example.simpleinsurance.model.Quote;
import com.example.simpleinsurance.model.QuoteDao;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
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

    }

    @Override
    public List<QuoteResponse> getAllQuotes() {
        return quoteDao.findAll().stream().map(quoteMapper::toResponse).toList();
    }

    @Override
    public QuoteResponse getQuoteById(Long id) {
        Quote quote = quoteDao.findById(id).orElseThrow(() -> new RuntimeException("Quote not found!"));
        return quoteMapper.toResponse(quote);
    }

    @Override
    public QuoteResponse createQuote(CreationQuoteRequest request) {

        Provider providerExists = providerDao.findByName(request.getProvider().getName());

        if (providerExists == null) {
            providerExists = providerMapper.toEntity(request.getProvider());
            providerExists = providerDao.save(providerExists);
        }
        Quote quote = quoteMapper.toEntity(request);
        quote.setProvider(providerExists);

        return quoteMapper.toResponse(quoteDao.save(quote));
    }

    @Override
    public QuoteResponse updateQuote(Long id, UpdateQuoteRequest request){
        Quote quote = quoteDao.findById(id).orElseThrow(() -> new RuntimeException("Quote not found!"));
        quoteMapper.updateQuoteFromDto(request, quote);
        return quoteMapper.toResponse(quoteDao.save(quote));
    }

    @Override
    public void deleteQuote(Long id){
        Quote quote = quoteDao.findById(id).orElseThrow(() -> new RuntimeException("Quote not found!"));
        quoteDao.delete(quote);
    }

    @Override
    public QuoteResponse getBestQuote() {
        Quote quote = quoteDao.findAll().stream().min(Comparator.comparing(Quote::getPrice))
                .orElseThrow(() -> new RuntimeException("Quotes not found!"));
        return quoteMapper.toResponse(quote);
    }
}
