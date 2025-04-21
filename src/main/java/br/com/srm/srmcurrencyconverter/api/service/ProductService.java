package br.com.srm.srmcurrencyconverter.api.service;

import br.com.srm.srmcurrencyconverter.api.dto.request.ProductDto;
import br.com.srm.srmcurrencyconverter.api.dto.response.ConvertedProductDto;
import br.com.srm.srmcurrencyconverter.api.model.CurrencyConversionRate;
import br.com.srm.srmcurrencyconverter.api.model.Product;
import br.com.srm.srmcurrencyconverter.api.model.ProductKingdom;
import br.com.srm.srmcurrencyconverter.api.repository.*;
import br.com.srm.srmcurrencyconverter.config.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductKingdomRepository productKingdomRepository;
    private final CurrencyConversionRateRepository currencyConversionRateRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(ProductDto product) {
        return productRepository.save(new Product(product));
    }

    public Product getProductById(Integer productId) {
        return productRepository.findById(productId).orElseThrow(() -> new DataNotFoundException("Product not found", "productId", productId));
    }

    public ConvertedProductDto convertProductValue(final Integer productId, final Integer destinyCurrencyId) {
        ProductKingdom product = productKingdomRepository.findById(productId).orElseThrow(() -> new DataNotFoundException("Product not found", "productId", productId));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.parse(LocalDate.now().format(formatter));
        BigDecimal convertedValue = BigDecimal.ZERO;

        CurrencyConversionRate currencyConversionRate = currencyConversionRateRepository.findByOriginCurrencyCurrencyIdAndDate(product.getOriginCurrency().getCurrencyId(), today)
                .orElseThrow(() -> new DataNotFoundException("Currency conversion rate not found on this date: " + today, "originCurrencyId", product.getOriginCurrency().getCurrencyId()));
        if(!Objects.equals(product.getOriginCurrency().getCurrencyId(), destinyCurrencyId)) {
            if (BigDecimal.ZERO.compareTo(product.getProductConversionRate()) != 0) {
                convertedValue = product.getValue().multiply(product.getProductConversionRate());
            } else {
                convertedValue = product.getValue().multiply(currencyConversionRate.getConversionRate());
            }
        } else {
            convertedValue = product.getValue();
        }

        return new ConvertedProductDto(product, convertedValue);
    }
}
