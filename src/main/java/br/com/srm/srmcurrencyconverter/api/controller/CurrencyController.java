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
@Tag(name = "Moedas", description = "Gerencia as moedas disponíveis")
public class CurrencyController {

    private final CurrencyService currencyService;
    private final CurrencyConversionService currencyConversionService;

    @Operation(summary = "Obter todas as moedas",
            description = "Este endpoint retorna uma lista com todas as moedas cadastradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Lista de moedas obtida com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Currency.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro interno no servidor")
    })
    @GetMapping()
    public ResponseEntity<List<Currency>> getProduct() {
        return ResponseEntity.ok(currencyService.getAllCurrencies());
    }


    @Operation(summary = "Obter taxas de conversão de moedas",
            description = "Este endpoint retorna uma lista com todas as taxas de conversão de moedas cadastradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Lista de taxas de conversão obtida com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CurrencyConversionRate.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro interno no servidor")
    })
    @GetMapping("/rates")
    public ResponseEntity<List<CurrencyConversionRate>> getCurrencyConversionRate() {
        return ResponseEntity.ok(currencyConversionService.getAllCurrencyConversionRate());
    }

    @Operation(summary = "Obter histórico de taxas de conversão por moeda de origem",
            description = "Este endpoint retorna o histórico de taxas de conversão de uma moeda de origem específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Histórico de taxas de conversão obtido com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CurrencyConversionRate.class))),
            @ApiResponse(responseCode = "400",
                    description = "ID da moeda de origem inválido"),
            @ApiResponse(responseCode = "500",
                    description = "Erro interno no servidor")
    })
    @GetMapping("{originCurrencyId}/rates/history")
    public ResponseEntity<List<CurrencyConversionRate>> getHistoryCurrencyRateByOriginCurrencyId(@PathVariable Integer originCurrencyId) {
        return ResponseEntity.ok(currencyConversionService.getAllCurrencyRateByOriginCurrencyId(originCurrencyId));
    }


    @Operation(summary = "Obter taxas de conversão de moedas para hoje",
            description = "Este endpoint retorna as taxas de conversão de moedas para a data de hoje.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Taxas de conversão de moedas para o dia de hoje obtidas com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CurrencyConversionRate.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro interno no servidor")
    })
    @GetMapping("/rates/today")
    public ResponseEntity<List<CurrencyConversionRate>> getCurrencyConversionRateToday() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.parse(LocalDate.now().format(formatter));
        return ResponseEntity.ok(currencyConversionService.getCurrencyByDate(today));
    }

    @Operation(summary = "Obter taxas de conversão de moedas para uma data específica",
            description = "Este endpoint retorna as taxas de conversão de moedas para uma data informada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Taxas de conversão de moedas para a data especificada obtidas com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CurrencyConversionRate.class))),
            @ApiResponse(responseCode = "400",
                    description = "Data inválida fornecida"),
            @ApiResponse(responseCode = "500",
                    description = "Erro interno no servidor")
    })
    @GetMapping("/rates/date")
    public ResponseEntity<List<CurrencyConversionRate>> getCurrencyConversionRateByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return ResponseEntity.ok(currencyConversionService.getCurrencyByDate(date));
    }

    @Operation(summary = "Criar uma nova taxa de conversão de moeda",
            description = "Este endpoint cria uma nova taxa de conversão de moeda com os dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Taxa de conversão de moeda criada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CurrencyConversionRate.class))),
            @ApiResponse(responseCode = "400",
                    description = "Requisição mal-formada (dados inválidos fornecidos)"),
            @ApiResponse(responseCode = "500",
                    description = "Erro interno no servidor")
    })
    @PostMapping("/rates")
    public ResponseEntity<List<CurrencyConversionRate>> createCurrencyConversionRate(@RequestBody @Valid CurrencyRateDto currencyConversionRate) {
        List<CurrencyConversionRate> currencyRate = currencyConversionService.createCurrencyRate(currencyConversionRate);
        URI uri = UriComponentsBuilder.newInstance().path("/api/v1/currencies/{originCurrencyId}/rates").buildAndExpand(currencyRate.get(0).getCurrencyConversionId()).toUri();
        return ResponseEntity.created(uri).body(currencyRate);
    }
}
