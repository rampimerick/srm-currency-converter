package br.com.srm.srmcurrencyconverter.api.service;


import br.com.srm.srmcurrencyconverter.api.dto.request.CurrencyRate;
import br.com.srm.srmcurrencyconverter.api.model.Currency;
import br.com.srm.srmcurrencyconverter.api.model.CurrencyConversionRate;
import br.com.srm.srmcurrencyconverter.api.repository.CurrencyConversionRateRepository;
import br.com.srm.srmcurrencyconverter.api.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class CurrencyConversionService {

    private final CurrencyConversionRateRepository currencyConversionRateRepository;
    private final CurrencyRepository currencyRepository;

    public List<CurrencyConversionRate> getAllCurrencyConversionRate() {
        return currencyConversionRateRepository.findAll();
    }

    public List<CurrencyConversionRate> getCurrencyRateByOriginCurrencyIdAndPeriod(final Integer originCurrencyId, final LocalDate startDate, final LocalDate endDate ) {
        return currencyConversionRateRepository.findAllByOriginCurrencyCurrencyIdAndDateBetween(originCurrencyId, startDate, endDate);
    }

    @Transactional
    public CurrencyConversionRate createCurrencyRate(final CurrencyRate currencyRate) {
        Currency originCurrency = currencyRepository.findById(currencyRate.getOriginCurrencyId()).orElseThrow();
        Currency destinyCurrency = currencyRepository.findById(currencyRate.getDestinyCurrencyId()).orElseThrow();
        currencyRepository.findById(currencyRate.getOriginCurrencyId()).orElseThrow();
        return currencyConversionRateRepository.save( new CurrencyConversionRate(currencyRate, originCurrency, destinyCurrency));
    }
}
