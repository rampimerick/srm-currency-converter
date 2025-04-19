package br.com.srm.srmcurrencyconverter.api.service;

import br.com.srm.srmcurrencyconverter.api.model.ProductByKingdom;
import br.com.srm.srmcurrencyconverter.api.repository.ProductByKingdomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class ProductByKingdomService {

    private final ProductByKingdomRepository productByKingdomRepository;

    public List<ProductByKingdom> getAllProductsByKingdomId(final Integer kingdomId) {
        return productByKingdomRepository.findAllByKingdomKingdomId(kingdomId);
    }

    public List<ProductByKingdom> getAllProductsByProductId(final Integer productId) {
        return productByKingdomRepository.findAllByProductProductId(productId);
    }

    public ProductByKingdom getProductByProductKingdomId(final Integer productKingdomId) {
        return productByKingdomRepository.findById(productKingdomId).orElseThrow();
    }
}
