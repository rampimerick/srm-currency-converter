package br.com.srm.srmcurrencyconverter.api.controller;

import br.com.srm.srmcurrencyconverter.api.dto.request.ProductKingdomDto;
import br.com.srm.srmcurrencyconverter.api.model.ProductKingdom;
import br.com.srm.srmcurrencyconverter.api.service.ProductKingdomService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products-kingdoms")
@Tag(name = "Produtos Por Reino", description = "Gerencia os vínculos entre produtos e reinos")
public class ProductKingdomController {

    private final ProductKingdomService productKingdomService;

    @GetMapping("/{productKingdomId}")
    public ResponseEntity<ProductKingdom> getProductKingdomById(@PathVariable final Integer productKingdomId) {
        return ResponseEntity.ok(productKingdomService.getProductKingdomById(productKingdomId));
    }

    @PostMapping()
    public ResponseEntity<ProductKingdom> createProductKingdom(@RequestBody @Valid ProductKingdomDto productKingdomDto) {
        ProductKingdom productKingdom = productKingdomService.createProductKingdom(productKingdomDto);
        URI uri = UriComponentsBuilder.newInstance().path("/api/v1/product-kingdoms/{productKingdomId}").buildAndExpand(productKingdom.getProductKingdomId()).toUri();
        return ResponseEntity.created(uri).body(productKingdom);
    }
}
