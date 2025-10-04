package com.example.simpleinsurance.api.controller;

import com.example.simpleinsurance.api.dto.CreationQuoteRequest;
import com.example.simpleinsurance.api.dto.QuoteResponse;
import com.example.simpleinsurance.api.dto.UpdateQuoteRequest;
import com.example.simpleinsurance.model.Quote;
import com.example.simpleinsurance.service.QuoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quotes")
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping
    public ResponseEntity<List<QuoteResponse>> getAllQuotes() {
        return  ResponseEntity.ok(quoteService.getAllQuotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuoteResponse> getQuoteById(@PathVariable Long id) {
        return ResponseEntity.ok(quoteService.getQuoteById(id));
    }

    @PostMapping
    public ResponseEntity<QuoteResponse> createQuote(@RequestBody CreationQuoteRequest request){
        return ResponseEntity.ok(quoteService.createQuote(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuoteResponse> updateQuote(@PathVariable Long id,
                                                     @RequestBody UpdateQuoteRequest request){
        return ResponseEntity.ok(quoteService.updateQuote(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable Long id){
        quoteService.deleteQuote(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/best-price")
    public ResponseEntity<QuoteResponse> getBestQuote(){
        return ResponseEntity.ok(quoteService.getBestQuote());
    }
}
