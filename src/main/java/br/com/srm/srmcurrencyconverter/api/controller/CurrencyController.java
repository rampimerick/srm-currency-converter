package br.com.srm.srmcurrencyconverter.api.controller;

import br.com.srm.srmcurrencyconverter.api.model.Currency;
import br.com.srm.srmcurrencyconverter.api.repository.CurrencyRepository;
import br.com.srm.srmcurrencyconverter.api.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/currencies")
    public ResponseEntity<List<Currency>> getProduct() {
        return ResponseEntity.ok(currencyService.getAllCurrencies());
    }
}
