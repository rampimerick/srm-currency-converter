package br.com.srm.srmcurrencyconverter.api.controller;


import br.com.srm.srmcurrencyconverter.api.dto.request.ProductDto;
import br.com.srm.srmcurrencyconverter.api.dto.request.ProductKingdomDto;
import br.com.srm.srmcurrencyconverter.api.model.Product;
import br.com.srm.srmcurrencyconverter.api.model.ProductKingdom;
import br.com.srm.srmcurrencyconverter.api.service.ProductKingdomService;
import br.com.srm.srmcurrencyconverter.api.service.ProductService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/api/v1/products")
@Tag(name = "Produtos", description = "Gerencia os produtos disponíveis")
public class ProductController {

    private final ProductService productService;
    private final ProductKingdomService productKingdomService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado")
    })
    @GetMapping()
    public ResponseEntity<List<Product>> getProduct() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<ProductKingdom>> getProductByProductId(@PathVariable final Integer productId) {
        return ResponseEntity.ok(productKingdomService.getAllProductsByProductId(productId));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductDto product) {
        Product productSave = productService.saveProduct(product);
        URI uri = UriComponentsBuilder.newInstance().path("/api/v1/currencies/{productId}").buildAndExpand(productSave.getProductId()).toUri();
        return ResponseEntity.created(uri).body(productSave);
    }
}


