package com.example.simpleinsurance.service;

import com.example.simpleinsurance.api.dto.CreationQuoteRequest;
import com.example.simpleinsurance.api.dto.QuoteResponse;
import com.example.simpleinsurance.api.dto.UpdateQuoteRequest;

import java.util.List;

public interface QuoteService {

    List<QuoteResponse> getAllQuotes();

    QuoteResponse getQuoteById(Long id);

    QuoteResponse createQuote(CreationQuoteRequest request);

    QuoteResponse updateQuote(Long id, UpdateQuoteRequest request);

    void deleteQuote(Long id);

    QuoteResponse getBestQuote();
}