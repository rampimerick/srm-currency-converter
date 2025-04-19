package br.com.srm.srmcurrencyconverter.api.controller;

import br.com.srm.srmcurrencyconverter.api.model.ProductByKingdom;
import br.com.srm.srmcurrencyconverter.api.service.ProductByKingdomService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kingdoms")
@Tag(name = "Produtos", description = "Gerencia os produtos disponíveis")
public class KingdomController {

    private final ProductByKingdomService productByKingdomService;

    @GetMapping("/{kingdomId}/products")
    public ResponseEntity<List<ProductByKingdom>> getProductByKingdomId(@PathVariable final Integer kingdomId) {
        return ResponseEntity.ok(productByKingdomService.getAllProductsByKingdomId(kingdomId));
    }
}
