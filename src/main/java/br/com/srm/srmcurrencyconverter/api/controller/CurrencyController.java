package br.com.srm.srmcurrencyconverter.api.controller;

import br.com.srm.srmcurrencyconverter.api.dto.request.CurrencyRateDto;
import br.com.srm.srmcurrencyconverter.api.model.Currency;
import br.com.srm.srmcurrencyconverter.api.model.CurrencyConversionRate;
import br.com.srm.srmcurrencyconverter.api.service.CurrencyConversionService;
import br.com.srm.srmcurrencyconverter.api.service.CurrencyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/currencies")
@Tag(name = "Moedas", description = "Gerencia as moedas disponíveis")
public class CurrencyController {

    private final CurrencyService currencyService;
    private final CurrencyConversionService currencyConversionService;

    @GetMapping()
    public ResponseEntity<List<Currency>> getProduct() {
        return ResponseEntity.ok(currencyService.getAllCurrencies());
    }

    @GetMapping("/rates")
    public ResponseEntity<List<CurrencyConversionRate>> getCurrencyConversionRate() {
        return ResponseEntity.ok(currencyConversionService.getAllCurrencyConversionRate());
    }

    @GetMapping("{originCurrencyId}/rates/history")
    public ResponseEntity<List<CurrencyConversionRate>> getHistoryCurrencyRateByOriginCurrencyId(@PathVariable Integer originCurrencyId) {
        return ResponseEntity.ok(currencyConversionService.getAllCurrencyRateByOriginCurrencyId(originCurrencyId));
    }

    @GetMapping("{originCurrencyId}/rates")
    public ResponseEntity<List<CurrencyConversionRate>> getCurrencyConversionRate(@PathVariable Integer originCurrencyId,
                                                                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return ResponseEntity.ok(currencyConversionService.getCurrencyRateByOriginCurrencyIdAndPeriod(originCurrencyId, startDate, endDate));
    }

    @PostMapping("/rates")
    public ResponseEntity<List<CurrencyConversionRate>> createCurrencyConversionRate(@RequestBody @Valid CurrencyRateDto currencyConversionRate) {
        List<CurrencyConversionRate> currencyRate = currencyConversionService.createCurrencyRate(currencyConversionRate);
        URI uri = UriComponentsBuilder.newInstance().path("/api/v1/currencies/{originCurrencyId}/rates").buildAndExpand(currencyRate.get(0).getCurrencyConversionId()).toUri();
        return ResponseEntity.created(uri).body(currencyRate);
    }
}
