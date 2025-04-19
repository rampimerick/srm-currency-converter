package br.com.srm.srmcurrencyconverter.api.repository;

import br.com.srm.srmcurrencyconverter.api.model.ProductByKingdom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductByKingdomRepository extends JpaRepository<ProductByKingdom, Integer> {

    List<ProductByKingdom> findAllByKingdomKingdomId(Integer kingdomId);
    List<ProductByKingdom> findAllByProductProductId(Integer productId);

}
