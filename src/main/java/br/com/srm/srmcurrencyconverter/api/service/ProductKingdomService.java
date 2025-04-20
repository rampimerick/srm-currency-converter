package br.com.srm.srmcurrencyconverter.api.service;

import br.com.srm.srmcurrencyconverter.api.dto.request.ProductKingdomDto;
import br.com.srm.srmcurrencyconverter.api.model.Currency;
import br.com.srm.srmcurrencyconverter.api.model.Kingdom;
import br.com.srm.srmcurrencyconverter.api.model.Product;
import br.com.srm.srmcurrencyconverter.api.model.ProductKingdom;
import br.com.srm.srmcurrencyconverter.api.repository.CurrencyRepository;
import br.com.srm.srmcurrencyconverter.api.repository.KingdomRepository;
import br.com.srm.srmcurrencyconverter.api.repository.ProductKingdomRepository;
import br.com.srm.srmcurrencyconverter.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class ProductKingdomService {

    private final ProductKingdomRepository productByKingdomRepository;
    private final ProductRepository productRepository;
    private final CurrencyRepository currencyRepository;
    private final KingdomRepository kingdomRepository;


    public ProductKingdom getProductKingdomById(final Integer productKingdomId) {
        return productByKingdomRepository.findById(productKingdomId).orElseThrow(() -> new RuntimeException("Product Kingdom not found"));
    }

    public List<ProductKingdom> getAllProductsByKingdomId(final Integer kingdomId) {
        return productByKingdomRepository.findAllByKingdomKingdomId(kingdomId);
    }

    public List<ProductKingdom> getAllProductsByProductId(final Integer productId) {
        return productByKingdomRepository.findAllByProductProductId(productId);
    }

    @Transactional
    public ProductKingdom createProductKingdom(ProductKingdomDto productKingdomDto) {

        Product product = productRepository.findById(productKingdomDto.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
        Kingdom kingdom = kingdomRepository.findById(productKingdomDto.getKingdomId()).orElseThrow(() -> new RuntimeException("Kingdom not found"));
        Currency currency = currencyRepository.findById(productKingdomDto.getOriginCurrencyId()).orElseThrow(() -> new RuntimeException("Currency not found"));

        return productByKingdomRepository.save(new ProductKingdom(productKingdomDto, product, kingdom, currency));
    }

    public List<ProductKingdom> getAll() {
        return productByKingdomRepository.findAll();
    }
}
