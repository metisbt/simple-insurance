package com.example.simpleinsurance.api.controller;

import com.example.simpleinsurance.api.dto.ApiResponse;
import com.example.simpleinsurance.api.dto.CreationQuoteRequest;
import com.example.simpleinsurance.api.dto.QuoteResponse;
import com.example.simpleinsurance.api.dto.UpdateQuoteRequest;
import com.example.simpleinsurance.service.QuoteService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quotes")
@Slf4j
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
        log.info("QuoteController initialized.");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<QuoteResponse>>> getAllQuotes() {
        log.debug("Received request to get all quotes.");
        List<QuoteResponse> quotes = quoteService.getAllQuotes();
        if (quotes.isEmpty()) {
            log.warn("No quotes found.");
            return new ResponseEntity<>(ApiResponse.notFound("Quotes not found"), HttpStatus.NOT_FOUND);
        }
        else {
            log.info("Retrieved {} quotes.", quotes.size());
            return new ResponseEntity<>(ApiResponse.success(
                    quotes, "All quotes retrieved successfully."
            ), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<QuoteResponse>> getQuoteById(@PathVariable Long id) {
        log.debug("Received request to get quote by ID: {}", id);
        QuoteResponse quote = quoteService.getQuoteById(id);
        if (quote != null) {
            log.info("Found quote with ID: {}", id);
//            return ResponseEntity.ok(quote);
            return new ResponseEntity<>(ApiResponse.success(quote, "Quote retrieved successfully."), HttpStatus.OK);
        }
        else {
            log.warn("Quote with ID {} not found.", id);
//            return  ResponseEntity.notFound().build();
            return new ResponseEntity<>(ApiResponse.notFound("Quote not found with ID " + id), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<QuoteResponse>> createQuote(@Valid @RequestBody CreationQuoteRequest request){
        log.debug("Received request to create quote: {}",  request);
        QuoteResponse createdQuote = quoteService.createQuote(request);
        log.info("Quote created successfully, ID: {}", createdQuote.getId());
//        return ResponseEntity.ok(createdQuote);
        return new ResponseEntity<>(ApiResponse.success(createdQuote, "Quote created successfully"), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<QuoteResponse>> updateQuote(@PathVariable Long id,
                                                     @RequestBody UpdateQuoteRequest request){
        log.debug("Received request to update quote ID {}: {}",  id, request);
        QuoteResponse updatedQuote = quoteService.updateQuote(id, request);
        log.info("Quote with ID {} updated successfully.", id);
//        return ResponseEntity.ok(updatedQuote);
        return new ResponseEntity<>(ApiResponse.success(updatedQuote, "Quote updated successfully"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable Long id){
        log.debug("Received request to delete quote with ID: {}", id);
        quoteService.deleteQuote(id);
        log.info("Quote with ID {} deleted successfully.", id);
//        return ResponseEntity.noContent().build();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/best-price")
    public ResponseEntity<ApiResponse<QuoteResponse>> getBestQuote(){
        log.debug("Received request to get quote best price.");
        QuoteResponse quote = quoteService.getBestQuote();
        if (quote != null) {
            log.info("Found best quote with ID: {}", quote.getId());
//            return ResponseEntity.ok(quote);
            return new ResponseEntity<>(ApiResponse.success(quote, "Best quote retrieved successfully"), HttpStatus.OK);
        }
        else {
            log.warn("Best quote not found.");
//            return  ResponseEntity.notFound().build();
            return new ResponseEntity<>(ApiResponse.notFound("Best quote not found"), HttpStatus.NOT_FOUND);
        }
    }
}
