package br.com.srm.srmcurrencyconverter.api.controller;


import br.com.srm.srmcurrencyconverter.api.model.Product;
import br.com.srm.srmcurrencyconverter.api.model.ProductByKingdom;
import br.com.srm.srmcurrencyconverter.api.service.ProductByKingdomService;
import br.com.srm.srmcurrencyconverter.api.service.ProductService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/api/v1/products")
@Tag(name = "Produtos", description = "Gerencia os produtos disponíveis")
public class ProductController {

    private final ProductService productService;
    private final ProductByKingdomService productByKingdomService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado")
    })
    @GetMapping()
    public ResponseEntity<List<Product>> getProduct() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<ProductByKingdom>> getProductByProductId(@PathVariable  final Integer productId) {
        return ResponseEntity.ok(productByKingdomService.getAllProductsByProductId(productId));
    }
}
