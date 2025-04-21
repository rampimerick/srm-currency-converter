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
@Tag(name = "Produtos de Reinos", description = "Gerencia os vínculos entre produtos e reinos")
public class ProductKingdomController {

    private final ProductKingdomService productKingdomService;

    @Operation(summary = "Obter todos os produtos de reinos",
            description = "Este endpoint retorna uma lista com todos os produtos de reinos cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Lista de produtos de reinos obtida com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductKingdom.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro interno no servidor")
    })
    @GetMapping()
    public ResponseEntity<List<ProductKingdom>> getAllProductKingdoms() {
        return ResponseEntity.ok(productKingdomService.getAll());
    }

    @Operation(summary = "Obter produto de reino por ID",
            description = "Este endpoint retorna um produto de reino específico com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Produto de reino encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductKingdom.class))),
            @ApiResponse(responseCode = "404",
                    description = "Produto de reino não encontrado"),
            @ApiResponse(responseCode = "500",
                    description = "Erro interno no servidor")
    })
    @GetMapping("/{productKingdomId}")
    public ResponseEntity<ProductKingdom> getProductKingdomById(@PathVariable final Integer productKingdomId) {
        return ResponseEntity.ok(productKingdomService.getProductKingdomById(productKingdomId));
    }

    @Operation(summary = "Criar um novo produto de reino",
            description = "Este endpoint cria um novo produto de reino com as informações fornecidas e retorna o produto criado com status 201 Created.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Produto de reino criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductKingdom.class))),
            @ApiResponse(responseCode = "400",
                    description = "Requisição mal-formada (dados inválidos fornecidos)"),
            @ApiResponse(responseCode = "500",
                    description = "Erro interno no servidor")
    })
    @PostMapping()
    public ResponseEntity<ProductKingdom> createProductKingdom(@RequestBody @Valid ProductKingdomDto productKingdomDto) {
        ProductKingdom productKingdom = productKingdomService.createProductKingdom(productKingdomDto);
        URI uri = UriComponentsBuilder.newInstance().path("/api/v1/product-kingdoms/{productKingdomId}").buildAndExpand(productKingdom.getProductKingdomId()).toUri();
        return ResponseEntity.created(uri).body(productKingdom);
    }
}
