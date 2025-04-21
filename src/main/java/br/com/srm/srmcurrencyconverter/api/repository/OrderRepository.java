package br.com.srm.srmcurrencyconverter.api.repository;

import br.com.srm.srmcurrencyconverter.api.dto.response.ProductOrderDto;
import br.com.srm.srmcurrencyconverter.api.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT new br.com.srm.srmcurrencyconverter.api.dto.response.ProductOrderDto(" +
            "pk.productKingdomId, " +
            "pk.kingdom, " +
            "pk.product, " +
            "pk.originCurrency, " +
            "pk.value, " +
            "po.amount) " +
            "FROM ProductOrder po " +
            "JOIN po.product pk " +
            "WHERE po.orderId = :orderId " +
            "ORDER BY pk.productKingdomId ")
    List<ProductOrderDto> findProductsDetails(@Param("orderId") Integer orderId);
}
