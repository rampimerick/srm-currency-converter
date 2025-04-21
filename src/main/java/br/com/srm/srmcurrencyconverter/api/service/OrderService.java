package br.com.srm.srmcurrencyconverter.api.service;

import br.com.srm.srmcurrencyconverter.api.dto.request.NewOrderDto;
import br.com.srm.srmcurrencyconverter.api.dto.response.OrderResponseDto;
import br.com.srm.srmcurrencyconverter.api.dto.response.ProductOrderDto;
import br.com.srm.srmcurrencyconverter.api.model.*;
import br.com.srm.srmcurrencyconverter.api.model.Currency;
import br.com.srm.srmcurrencyconverter.api.model.enums.OrderType;
import br.com.srm.srmcurrencyconverter.api.repository.*;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CurrencyRepository currencyRepository;
    private final ProductKingdomRepository productKingdomRepository;
    private final CurrencyConversionRateRepository currencyConversionRateRepository;
    private final ProductOrderRepository productOrderRepository;

    public Order getOrderById(Integer orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderResponseDto getOrderAndProducts(final Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        List<ProductOrderDto> productsDetails = orderRepository.findProductsDetails(orderId);

        return new OrderResponseDto(order, productsDetails);
    }


    @Transactional
    public Order createOrder(final NewOrderDto newOrderDto) {

        Currency destinationCurrency = currencyRepository.findById(newOrderDto.getDestinyCurrencyId()).orElseThrow(() -> new RuntimeException("Currency not found"));
        Order orderModel = new Order(newOrderDto, destinationCurrency);
        Map<ProductKingdom, Integer> orderProducts = new HashMap<>();

        newOrderDto.getProducts().forEach(product -> {
            ProductKingdom productKingdom = productKingdomRepository.findById(product.getProductKingdomId()).orElseThrow(() -> new RuntimeException("Product for this kingdom not found"));
            orderProducts.put(productKingdom, product.getQuantity());
        });

        BigDecimal totalValue = orderProducts.keySet().stream().map(product -> {
            if (!Objects.equals(product.getOriginCurrency().getCurrencyId(), orderModel.getDestinyCurrency().getCurrencyId())) {
                if (product.getProductConversionRate().compareTo(BigDecimal.ZERO) != 0) {
                    return product.getValue().multiply(product.getProductConversionRate()).multiply(BigDecimal.valueOf(orderProducts.get(product)));
                } else {
                    CurrencyConversionRate currencyRate = currencyConversionRateRepository.findByOriginCurrencyCurrencyIdAndDate(product.getOriginCurrency().getCurrencyId(), orderModel.getOrderDate()).orElseThrow(() -> new RuntimeException("Currency conversion rate not found on this date"));
                    return product.getValue().multiply(currencyRate.getConversionRate()).multiply(BigDecimal.valueOf(orderProducts.get(product)));
                }
            }
            return product.getValue().multiply(BigDecimal.valueOf(orderProducts.get(product)));
        }).reduce(BigDecimal.ZERO, BigDecimal::add);

        orderModel.setTotalValue(totalValue);

        Order savedOrder = orderRepository.save(orderModel);

        orderProducts.keySet().forEach(productKingdom -> {
            productOrderRepository.save(new ProductOrder(savedOrder.getOrderId(), productKingdom, orderProducts.get(productKingdom)));
        });

        return savedOrder;
    }
}
