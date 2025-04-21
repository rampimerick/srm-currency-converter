package br.com.srm.srmcurrencyconverter.api.service;


import br.com.srm.srmcurrencyconverter.api.dto.request.CurrencyRateDto;
import br.com.srm.srmcurrencyconverter.api.model.Currency;
import br.com.srm.srmcurrencyconverter.api.model.CurrencyConversionRate;
import br.com.srm.srmcurrencyconverter.api.repository.CurrencyConversionRateRepository;
import br.com.srm.srmcurrencyconverter.api.repository.CurrencyRepository;
import br.com.srm.srmcurrencyconverter.config.exception.DataNotFoundException;
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

    public List<CurrencyConversionRate> getCurrencyByDate(final LocalDate date ) {
        List<CurrencyConversionRate> allByDate = currencyConversionRateRepository.findAllByDate(date);
        if(allByDate.isEmpty()) {
            throw new DataNotFoundException("Currency Rate not found for the date: " + date , "date", 1);
        }
        return allByDate;
    }

    @Transactional
    public List<CurrencyConversionRate> createCurrencyRate(@Valid @NotNull final CurrencyRateDto currencyRateDto) {
        Currency originCurrency = currencyRepository.findById(currencyRateDto.getOriginCurrencyId()).orElseThrow(() -> new DataNotFoundException("Currency not found", "originCurrencyId", currencyRateDto.getOriginCurrencyId()));
        Currency destinyCurrency = currencyRepository.findById(currencyRateDto.getDestinyCurrencyId()).orElseThrow(() -> new DataNotFoundException("Currency not found", "destinyCurrencyId", currencyRateDto.getDestinyCurrencyId()));
        currencyRepository.findById(currencyRateDto.getOriginCurrencyId()).orElseThrow();
        CurrencyConversionRate currencyConversionRate = new CurrencyConversionRate(currencyRateDto, originCurrency, destinyCurrency);
        List<CurrencyConversionRate> currenciesRates = Arrays.asList(currencyConversionRate, currencyConversionRate.reverseCurrencyRate());
        return currencyConversionRateRepository.saveAll(currenciesRates);
    }

    public List<CurrencyConversionRate> getAllCurrencyRateByOriginCurrencyId(final Integer originCurrencyId) {
        List<CurrencyConversionRate> allByOriginCurrencyCurrencyId = currencyConversionRateRepository.findAllByOriginCurrencyCurrencyId(originCurrencyId);
        if (allByOriginCurrencyCurrencyId.isEmpty()) {
            throw new DataNotFoundException("Currency Rate not found", "originCurrencyId", originCurrencyId);
        }
        return allByOriginCurrencyCurrencyId;
    }
}
