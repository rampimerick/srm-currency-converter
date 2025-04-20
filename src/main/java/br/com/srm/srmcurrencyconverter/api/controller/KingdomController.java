package br.com.srm.srmcurrencyconverter.api.controller;

import br.com.srm.srmcurrencyconverter.api.dto.request.CurrencyRateDto;
import br.com.srm.srmcurrencyconverter.api.dto.request.KingdomDto;
import br.com.srm.srmcurrencyconverter.api.model.CurrencyConversionRate;
import br.com.srm.srmcurrencyconverter.api.model.Kingdom;
import br.com.srm.srmcurrencyconverter.api.model.ProductKingdom;
import br.com.srm.srmcurrencyconverter.api.service.KingdomService;
import br.com.srm.srmcurrencyconverter.api.service.ProductKingdomService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kingdoms")
@Tag(name = "Reinos", description = "Gerencia os reinos disponíveis")
public class KingdomController {

    private final ProductKingdomService productKingdomService;
    private final KingdomService kingdomService;

    @GetMapping
    public ResponseEntity<List<Kingdom>> getAllKingdoms() {
        return ResponseEntity.ok(kingdomService.getAllKingdoms());
    }

    @GetMapping("/{kingdomId}")
    public ResponseEntity<Kingdom> getKingdomById(@PathVariable final Integer kingdomId) {
        return ResponseEntity.ok(kingdomService.getKingdomById(kingdomId));
    }

    @GetMapping("/{kingdomId}/products")
    public ResponseEntity<List<ProductKingdom>> getProductByKingdomId(@PathVariable final Integer kingdomId) {
        return ResponseEntity.ok(productKingdomService.getAllProductsByKingdomId(kingdomId));
    }

    @PostMapping()
    public ResponseEntity<Kingdom> createKingdom(@RequestBody @Valid KingdomDto kingdomDto) {
        Kingdom kingdomSave = kingdomService.saveKingdom(kingdomDto);
        URI uri = UriComponentsBuilder.newInstance().path("/api/v1/kingdoms/{kingdomId}").buildAndExpand(kingdomSave.getKingdomId()).toUri();
        return ResponseEntity.created(uri).body(kingdomSave);
    }

}
