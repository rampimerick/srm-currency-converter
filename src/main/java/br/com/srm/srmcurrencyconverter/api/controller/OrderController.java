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
@Tag(name = "Orders", description = "Manage all orders")
public class OrderController {

    private final OrderService orderService;


    @Operation(summary = "Get order by ID",
            description = "This endpoint returns a specific order based on the provided ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Order found successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))),
            @ApiResponse(responseCode = "404",
                    description = "Order not found"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable final Integer orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @Operation(summary = "Get order details and products",
            description = "This endpoint returns order details, including the products associated with that order, based on the order ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Order details and products retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderResponseDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Order not found"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    @GetMapping("/{orderId}/detail")
    public ResponseEntity<OrderResponseDto> getOrderProductsDetailById(@PathVariable final Integer orderId) {
        return ResponseEntity.ok(orderService.getOrderAndProducts(orderId));
    }

    @Operation(summary = "Create a new order",
            description = "This endpoint creates a new order with the provided information and returns the created order with status 201 Created.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Order created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))),
            @ApiResponse(responseCode = "400",
                    description = "Bad request (invalid data provided)"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<Order> createNewOrder(@RequestBody @Valid final NewOrderDto newOrderDto){
        Order saveOrder = orderService.createOrder(newOrderDto);
        URI uri = UriComponentsBuilder.newInstance().path("/api/v1/orders/{orderId}").buildAndExpand(saveOrder.getOrderId()).toUri();
        return ResponseEntity.created(uri).body(saveOrder);
    }

}
