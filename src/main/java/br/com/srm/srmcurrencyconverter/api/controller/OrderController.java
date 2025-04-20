package br.com.srm.srmcurrencyconverter.api.controller;

import br.com.srm.srmcurrencyconverter.api.dto.request.NewOrderDto;
import br.com.srm.srmcurrencyconverter.api.model.Order;
import br.com.srm.srmcurrencyconverter.api.service.OrderService;
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


    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable final Integer orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @PostMapping
    public ResponseEntity<Order> createNewOrder(@RequestBody @Valid final NewOrderDto newOrderDto){
        Order saveOrder = orderService.createOrder(newOrderDto);
        URI uri = UriComponentsBuilder.newInstance().path("/api/v1/orders/{orderId}").buildAndExpand(saveOrder.getOrderId()).toUri();
        return ResponseEntity.created(uri).body(saveOrder);
    }



}
