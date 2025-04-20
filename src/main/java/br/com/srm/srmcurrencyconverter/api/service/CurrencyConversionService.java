package br.com.srm.srmcurrencyconverter.api.service;


import br.com.srm.srmcurrencyconverter.api.dto.request.CurrencyRateDto;
import br.com.srm.srmcurrencyconverter.api.model.Currency;
import br.com.srm.srmcurrencyconverter.api.model.CurrencyConversionRate;
import br.com.srm.srmcurrencyconverter.api.repository.CurrencyConversionRateRepository;
import br.com.srm.srmcurrencyconverter.api.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Arrays;
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
    public List<CurrencyConversionRate> createCurrencyRate(@Valid @NotNull final CurrencyRateDto currencyRateDto) {
        Currency originCurrency = currencyRepository.findById(currencyRateDto.getOriginCurrencyId()).orElseThrow(() -> new RuntimeException("Currency not found"));
        Currency destinyCurrency = currencyRepository.findById(currencyRateDto.getDestinyCurrencyId()).orElseThrow(() -> new RuntimeException("Currency not found"));
        currencyRepository.findById(currencyRateDto.getOriginCurrencyId()).orElseThrow();
        CurrencyConversionRate currencyConversionRate = new CurrencyConversionRate(currencyRateDto, originCurrency, destinyCurrency);
        List<CurrencyConversionRate> currenciesRates = Arrays.asList(currencyConversionRate, currencyConversionRate.reverseCurrencyRate());
        return currencyConversionRateRepository.saveAll(currenciesRates);
    }

    public List<CurrencyConversionRate> getAllCurrencyRateByOriginCurrencyId(final Integer originCurrencyId) {
        return currencyConversionRateRepository.findAllByOriginCurrencyCurrencyId(originCurrencyId);
    }
}
