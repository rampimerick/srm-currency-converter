package br.com.srm.srmcurrencyconverter.api.controller;

import br.com.srm.srmcurrencyconverter.api.dto.request.CurrencyRateDto;
import br.com.srm.srmcurrencyconverter.api.model.Currency;
import br.com.srm.srmcurrencyconverter.api.model.CurrencyConversionRate;
import br.com.srm.srmcurrencyconverter.api.service.CurrencyConversionService;
import br.com.srm.srmcurrencyconverter.api.service.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import java.time.format.DateTimeFormatter;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/currencies")
@Tag(name = "Currencies", description = "Manage all available currencies")
public class CurrencyController {

    private final CurrencyService currencyService;
    private final CurrencyConversionService currencyConversionService;

    @Operation(summary = "Get all currencies",
            description = "This endpoint returns a list of all registered currencies.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List of currencies retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Currency.class))),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    @GetMapping()
    public ResponseEntity<List<Currency>> getProduct() {
        return ResponseEntity.ok(currencyService.getAllCurrencies());
    }


    @Operation(summary = "Get all currency conversion rates",
            description = "This endpoint returns a list of all registered currency conversion rates.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List of conversion rates retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CurrencyConversionRate.class))),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    @GetMapping("/rates")
    public ResponseEntity<List<CurrencyConversionRate>> getCurrencyConversionRate() {
        return ResponseEntity.ok(currencyConversionService.getAllCurrencyConversionRate());
    }

    @Operation(summary = "Get conversion rate history by origin currency",
            description = "This endpoint returns the conversion rate history for a specific origin currency.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Conversion rate history retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CurrencyConversionRate.class))),
            @ApiResponse(responseCode = "400",
                    description = "Invalid origin currency ID"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    @GetMapping("{originCurrencyId}/rates/history")
    public ResponseEntity<List<CurrencyConversionRate>> getHistoryCurrencyRateByOriginCurrencyId(@PathVariable Integer originCurrencyId) {
        return ResponseEntity.ok(currencyConversionService.getAllCurrencyRateByOriginCurrencyId(originCurrencyId));
    }

    @Operation(summary = "Get today's currency conversion rates",
            description = "This endpoint returns today's currency conversion rates.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Today's conversion rates retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CurrencyConversionRate.class))),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    @GetMapping("/rates/today")
    public ResponseEntity<List<CurrencyConversionRate>> getCurrencyConversionRateToday() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.parse(LocalDate.now().format(formatter));
        return ResponseEntity.ok(currencyConversionService.getCurrencyByDate(today));
    }

    @Operation(summary = "Get currency conversion rates by date",
            description = "This endpoint returns the currency conversion rates for a given date.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Conversion rates for the specified date retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CurrencyConversionRate.class))),
            @ApiResponse(responseCode = "400",
                    description = "Invalid date provided"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    @GetMapping("/rates/date")
    public ResponseEntity<List<CurrencyConversionRate>> getCurrencyConversionRateByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return ResponseEntity.ok(currencyConversionService.getCurrencyByDate(date));
    }

    @Operation(summary = "Create a new currency conversion rate",
            description = "This endpoint creates a new currency conversion rate with the provided data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Currency conversion rate created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CurrencyConversionRate.class))),
            @ApiResponse(responseCode = "400",
                    description = "Bad request (invalid data provided)"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    @PostMapping("/rates")
    public ResponseEntity<List<CurrencyConversionRate>> createCurrencyConversionRate(@RequestBody @Valid CurrencyRateDto currencyConversionRate) {
        List<CurrencyConversionRate> currencyRate = currencyConversionService.createCurrencyRate(currencyConversionRate);
        URI uri = UriComponentsBuilder.newInstance().path("/api/v1/currencies/{originCurrencyId}/rates").buildAndExpand(currencyRate.get(0).getCurrencyConversionId()).toUri();
        return ResponseEntity.created(uri).body(currencyRate);
    }
}
