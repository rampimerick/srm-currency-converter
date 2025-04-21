package br.com.srm.srmcurrencyconverter.api.controller;

import br.com.srm.srmcurrencyconverter.api.dto.request.NewOrderDto;
import br.com.srm.srmcurrencyconverter.api.dto.response.OrderResponseDto;
import br.com.srm.srmcurrencyconverter.api.model.Order;
import br.com.srm.srmcurrencyconverter.api.service.OrderService;
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

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@Tag(name = "Transações", description = "Gerencia as transações")
public class OrderController {

    private final OrderService orderService;


    @Operation(summary = "Obter pedido por ID",
            description = "Este endpoint retorna um pedido específico com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Pedido encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))),
            @ApiResponse(responseCode = "404",
                    description = "Pedido não encontrado"),
            @ApiResponse(responseCode = "500",
                    description = "Erro interno no servidor")
    })
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable final Integer orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @Operation(summary = "Obter detalhes do pedido e produtos",
            description = "Este endpoint retorna os detalhes do pedido, incluindo os produtos associados a esse pedido, com base no ID do pedido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Detalhes do pedido e produtos obtidos com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderResponseDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Pedido não encontrado"),
            @ApiResponse(responseCode = "500",
                    description = "Erro interno no servidor")
    })
    @GetMapping("/{orderId}/detail")
    public ResponseEntity<OrderResponseDto> getOrderProductsDetailById(@PathVariable final Integer orderId) {
        return ResponseEntity.ok(orderService.getOrderAndProducts(orderId));
    }

    @Operation(summary = "Criar um novo pedido",
            description = "Este endpoint cria um novo pedido com as informações fornecidas e retorna o pedido criado com status 201 Created.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Pedido criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))),
            @ApiResponse(responseCode = "400",
                    description = "Requisição mal-formada (dados inválidos fornecidos)"),
            @ApiResponse(responseCode = "500",
                    description = "Erro interno no servidor")
    })
    @PostMapping
    public ResponseEntity<Order> createNewOrder(@RequestBody @Valid final NewOrderDto newOrderDto){
        Order saveOrder = orderService.createOrder(newOrderDto);
        URI uri = UriComponentsBuilder.newInstance().path("/api/v1/orders/{orderId}").buildAndExpand(saveOrder.getOrderId()).toUri();
        return ResponseEntity.created(uri).body(saveOrder);
    }

}
