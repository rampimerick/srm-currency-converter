package br.com.srm.srmcurrencyconverter.api.repository;

import br.com.srm.srmcurrencyconverter.api.model.CurrencyConversionRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CurrencyConversionRateRepository extends JpaRepository<CurrencyConversionRate, Integer> {

    List<CurrencyConversionRate> findAllByOriginCurrencyCurrencyIdAndDateBetween(Integer originCurrencyId, LocalDate dateAfter, LocalDate dateBefore);
}
