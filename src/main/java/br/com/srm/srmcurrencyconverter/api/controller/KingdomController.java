package br.com.srm.srmcurrencyconverter.api.controller;

import br.com.srm.srmcurrencyconverter.api.dto.request.KingdomDto;
import br.com.srm.srmcurrencyconverter.api.model.Kingdom;
import br.com.srm.srmcurrencyconverter.api.model.ProductKingdom;
import br.com.srm.srmcurrencyconverter.api.service.KingdomService;
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
@RequestMapping("/api/v1/kingdoms")
@Tag(name = "Reinos", description = "Gerencia os reinos disponíveis")
public class KingdomController {

    private final ProductKingdomService productKingdomService;
    private final KingdomService kingdomService;


    @Operation(summary = "Obter todos os reinos",
            description = "Este endpoint retorna uma lista com todos os reinos cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Lista de reinos obtida com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Kingdom.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro interno no servidor")
    })
    @GetMapping
    public ResponseEntity<List<Kingdom>> getAllKingdoms() {
        return ResponseEntity.ok(kingdomService.getAllKingdoms());
    }

    @Operation(summary = "Obter reino por ID",
            description = "Este endpoint retorna um reino específico com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Reino encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Kingdom.class))),
            @ApiResponse(responseCode = "404",
                    description = "Reino não encontrado"),
            @ApiResponse(responseCode = "500",
                    description = "Erro interno no servidor")
    })
    @GetMapping("/{kingdomId}")
    public ResponseEntity<Kingdom> getKingdomById(@PathVariable final Integer kingdomId) {
        return ResponseEntity.ok(kingdomService.getKingdomById(kingdomId));
    }


    @Operation(summary = "Obter produtos por reino",
            description = "Este endpoint retorna uma lista de produtos associados a um reino específico, com base no ID do reino.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Lista de produtos do reino obtida com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductKingdom.class))),
            @ApiResponse(responseCode = "404",
                    description = "Reino não encontrado"),
            @ApiResponse(responseCode = "500",
                    description = "Erro interno no servidor")
    })
    @GetMapping("/{kingdomId}/products")
    public ResponseEntity<List<ProductKingdom>> getProductByKingdomId(@PathVariable final Integer kingdomId) {
        return ResponseEntity.ok(productKingdomService.getAllProductsByKingdomId(kingdomId));
    }

    @Operation(summary = "Criar um novo reino",
            description = "Este endpoint cria um novo reino com as informações fornecidas e retorna o reino criado com status 201 Created.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Reino criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Kingdom.class))),
            @ApiResponse(responseCode = "400",
                    description = "Requisição mal-formada (dados inválidos)"),
            @ApiResponse(responseCode = "500",
                    description = "Erro interno no servidor")
    })
    @PostMapping()
    public ResponseEntity<Kingdom> createKingdom(@RequestBody @Valid KingdomDto kingdomDto) {
        Kingdom kingdomSave = kingdomService.saveKingdom(kingdomDto);
        URI uri = UriComponentsBuilder.newInstance().path("/api/v1/kingdoms/{kingdomId}").buildAndExpand(kingdomSave.getKingdomId()).toUri();
        return ResponseEntity.created(uri).body(kingdomSave);
    }

}
