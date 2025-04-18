package br.com.srm.srmcurrencyconverter.api.service;

import br.com.srm.srmcurrencyconverter.api.model.Currency;
import br.com.srm.srmcurrencyconverter.api.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;


    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }
}
