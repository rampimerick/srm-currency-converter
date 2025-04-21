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
@Tag(name = "Kingdoms", description = "Manage all available kingdoms")
public class KingdomController {

    private final ProductKingdomService productKingdomService;
    private final KingdomService kingdomService;


    @Operation(summary = "Get all kingdoms",
            description = "This endpoint returns a list of all registered kingdoms.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List of kingdoms retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Kingdom.class))),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<Kingdom>> getAllKingdoms() {
        return ResponseEntity.ok(kingdomService.getAllKingdoms());
    }

    @Operation(summary = "Get kingdom by ID",
            description = "This endpoint returns a specific kingdom based on the provided ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Kingdom found successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Kingdom.class))),
            @ApiResponse(responseCode = "404",
                    description = "Kingdom not found"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    @GetMapping("/{kingdomId}")
    public ResponseEntity<Kingdom> getKingdomById(@PathVariable final Integer kingdomId) {
        return ResponseEntity.ok(kingdomService.getKingdomById(kingdomId));
    }


    @Operation(summary = "Get products by kingdom",
            description = "This endpoint returns a list of products associated with a specific kingdom, based on the kingdom ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List of products by kingdom retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductKingdom.class))),
            @ApiResponse(responseCode = "404",
                    description = "Kingdom not found"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    @GetMapping("/{kingdomId}/products")
    public ResponseEntity<List<ProductKingdom>> getProductByKingdomId(@PathVariable final Integer kingdomId) {
        return ResponseEntity.ok(productKingdomService.getAllProductsByKingdomId(kingdomId));
    }

    @Operation(summary = "Create a new kingdom",
            description = "This endpoint creates a new kingdom with the provided information and returns the created kingdom with status 201 Created.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Kingdom created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Kingdom.class))),
            @ApiResponse(responseCode = "400",
                    description = "Bad request (invalid data)"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    @PostMapping()
    public ResponseEntity<Kingdom> createKingdom(@RequestBody @Valid KingdomDto kingdomDto) {
        Kingdom kingdomSave = kingdomService.saveKingdom(kingdomDto);
        URI uri = UriComponentsBuilder.newInstance().path("/api/v1/kingdoms/{kingdomId}").buildAndExpand(kingdomSave.getKingdomId()).toUri();
        return ResponseEntity.created(uri).body(kingdomSave);
    }

}
