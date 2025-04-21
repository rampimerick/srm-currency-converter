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
@Tag(name = "Produtos", description = "Gerencia os produtos disponíveis")
public class ProductController {

    private final ProductService productService;
    private final ProductKingdomService productKingdomService;

    @Operation(summary = "Obter histórico de produtos cadastrados no sistema", description = "Retorna uma lista de todas os produtos cadastrados anterioremente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado")
    })
    @GetMapping()
    public ResponseEntity<List<Product>> getProduct() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Operation(summary = "Obter todos os produtos por reino atráves do id do produto ", description = "Retorna os produtos por reino encontrados para o ID de produto informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado")
    })
    @GetMapping("/{productId}")
    public ResponseEntity<List<ProductKingdom>> getProductByProductId(@PathVariable final Integer productId) {
        return ResponseEntity.ok(productKingdomService.getAllProductsByProductId(productId));
    }

    @Operation(summary = "Criar um novo produto",
            description = "Este endpoint cria um novo produto com as informações fornecidas e retorna o produto criado com status 201 Created.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Produto criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "400",
                    description = "Requisição mal-formada (dados inválidos)"),
            @ApiResponse(responseCode = "500",
                    description = "Erro interno no servidor")
    })
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductDto product) {
        Product productSave = productService.saveProduct(product);
        URI uri = UriComponentsBuilder.newInstance().path("/api/v1/currencies/{productId}").buildAndExpand(productSave.getProductId()).toUri();
        return ResponseEntity.created(uri).body(productSave);
    }


    @Operation(summary = "Converter valor do produto para uma moeda específica",
            description = "Este endpoint converte o valor de um produto para a moeda de destino especificada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Valor do produto convertido com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConvertedProductDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Requisição mal-formada (dados inválidos)"),
            @ApiResponse(responseCode = "404",
                    description = "Produto de reino ou moeda de destino não encontrados"),
            @ApiResponse(responseCode = "500",
                    description = "Erro interno no servidor")
    })
    @GetMapping("/{productKingdomId}/convert")
    public ResponseEntity<ConvertedProductDto> convertProduct(@PathVariable final Integer productKingdomId,
                                                              @RequestParam final Integer destinyCurrencyId) {
        return ResponseEntity.ok(productService.convertProductValue(productKingdomId, destinyCurrencyId));
    }

}


