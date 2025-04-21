package br.com.srm.srmcurrencyconverter.api.controller;


import br.com.srm.srmcurrencyconverter.api.dto.request.ProductDto;
import br.com.srm.srmcurrencyconverter.api.dto.request.ProductKingdomDto;
import br.com.srm.srmcurrencyconverter.api.dto.response.ConvertedProductDto;
import br.com.srm.srmcurrencyconverter.api.model.Product;
import br.com.srm.srmcurrencyconverter.api.model.ProductKingdom;
import br.com.srm.srmcurrencyconverter.api.service.ProductKingdomService;
import br.com.srm.srmcurrencyconverter.api.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Tag(name = "Products", description = "Manage all available products.")
public class ProductController {

    private final ProductService productService;
    private final ProductKingdomService productKingdomService;

    @Operation(summary = "Get product history", description = "Returns a list of all previously registered products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product list returned successfully"),
            @ApiResponse(responseCode = "404", description = "No products found")
    })
    @GetMapping()
    public ResponseEntity<List<Product>> getProduct() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Operation(summary = "Get all kingdom products by product ID", description = "Returns the kingdom products found for the specified product ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product list returned successfully"),
            @ApiResponse(responseCode = "404", description = "No products found")
    })
    @GetMapping("/{productId}")
    public ResponseEntity<List<ProductKingdom>> getProductByProductId(@PathVariable final Integer productId) {
        return ResponseEntity.ok(productKingdomService.getAllProductsByProductId(productId));
    }

    @Operation(summary = "Create a new product",
            description = "This endpoint creates a new product with the provided information and returns the created product with a 201 Created status.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Product created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "400",
                    description = "Bad request (invalid data)"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductDto product) {
        Product productSave = productService.saveProduct(product);
        URI uri = UriComponentsBuilder.newInstance().path("/api/v1/currencies/{productId}").buildAndExpand(productSave.getProductId()).toUri();
        return ResponseEntity.created(uri).body(productSave);
    }


    @Operation(summary = "Convert product value to a specific currency",
            description = "This endpoint converts the value of a product to the specified destination currency.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Product value converted successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConvertedProductDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Bad request (invalid data)"),
            @ApiResponse(responseCode = "404",
                    description = "Kingdom product or destination currency not found"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    @GetMapping("/{productKingdomId}/convert")
    public ResponseEntity<ConvertedProductDto> convertProduct(@PathVariable final Integer productKingdomId,
                                                              @RequestParam final Integer destinyCurrencyId) {
        return ResponseEntity.ok(productService.convertProductValue(productKingdomId, destinyCurrencyId));
    }

}


