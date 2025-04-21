package br.com.srm.srmcurrencyconverter.api.controller;

import br.com.srm.srmcurrencyconverter.api.dto.request.ProductKingdomDto;
import br.com.srm.srmcurrencyconverter.api.model.ProductKingdom;
import br.com.srm.srmcurrencyconverter.api.service.ProductKingdomService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/v1/products-kingdoms")
@Tag(name = "ProductsKingdoms", description = "Manages the relationships between products and kingdoms.")
public class ProductKingdomController {

    private final ProductKingdomService productKingdomService;

    @Operation(summary = "Get all kingdom products",
            description = "This endpoint returns a list of all registered kingdom products.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Kingdom products list retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductKingdom.class))),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    @GetMapping()
    public ResponseEntity<List<ProductKingdom>> getAllProductKingdoms() {
        return ResponseEntity.ok(productKingdomService.getAll());
    }

    @Operation(summary = "Get kingdom product by ID",
            description = "This endpoint returns a specific kingdom product based on the provided ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Kingdom product found successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductKingdom.class))),
            @ApiResponse(responseCode = "404",
                    description = "Kingdom product not found"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    @GetMapping("/{productKingdomId}")
    public ResponseEntity<ProductKingdom> getProductKingdomById(@PathVariable final Integer productKingdomId) {
        return ResponseEntity.ok(productKingdomService.getProductKingdomById(productKingdomId));
    }

    @Operation(summary = "Create a new kingdom product",
            description = "This endpoint creates a new kingdom product with the provided information and returns the created product with a 201 Created status.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Kingdom product created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductKingdom.class))),
            @ApiResponse(responseCode = "400",
                    description = "Bad request (invalid data provided)"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    @PostMapping()
    public ResponseEntity<ProductKingdom> createProductKingdom(@RequestBody @Valid ProductKingdomDto productKingdomDto) {
        ProductKingdom productKingdom = productKingdomService.createProductKingdom(productKingdomDto);
        URI uri = UriComponentsBuilder.newInstance().path("/api/v1/product-kingdoms/{productKingdomId}").buildAndExpand(productKingdom.getProductKingdomId()).toUri();
        return ResponseEntity.created(uri).body(productKingdom);
    }
}
