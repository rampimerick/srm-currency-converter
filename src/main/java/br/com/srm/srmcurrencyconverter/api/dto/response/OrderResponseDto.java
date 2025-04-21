package br.com.srm.srmcurrencyconverter.api.dto.response;

import br.com.srm.srmcurrencyconverter.api.model.Currency;
import br.com.srm.srmcurrencyconverter.api.model.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderResponseDto {

    private Integer orderId;
    private Currency destinyCurrency;
    private String orderDate;
    private String orderType;
    private List<ProductOrderDto> products;
    private Double totalValue;


    public OrderResponseDto(Order order, List<ProductOrderDto> products) {
        this.orderId = order.getOrderId();
        this.destinyCurrency = order.getDestinyCurrency();
        this.orderDate = order.getOrderDate().toString();
        this.orderType = order.getOrderType().toString();
        this.products = products;
        this.totalValue = order.getTotalValue().doubleValue();
    }
}
