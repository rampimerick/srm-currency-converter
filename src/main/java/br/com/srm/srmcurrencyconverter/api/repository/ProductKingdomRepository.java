package br.com.srm.srmcurrencyconverter.api.repository;

import br.com.srm.srmcurrencyconverter.api.model.ProductKingdom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductKingdomRepository extends JpaRepository<ProductKingdom, Integer> {

    List<ProductKingdom> findAllByKingdomKingdomId(Integer kingdomId);
    List<ProductKingdom> findAllByProductProductId(Integer productId);

}
