package br.com.srm.srmcurrencyconverter.api.repository;

import br.com.srm.srmcurrencyconverter.api.model.CurrencyConversionRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyConversionRateRepository extends JpaRepository<CurrencyConversionRate, Integer> {

    List<CurrencyConversionRate> findAllByOriginCurrencyCurrencyIdAndDateBetween(Integer originCurrencyId, LocalDate startDate, LocalDate endDate);
    Optional<CurrencyConversionRate> findByOriginCurrencyCurrencyIdAndDate(Integer originCurrencyCurrencyId, LocalDate date);
    List<CurrencyConversionRate> findAllByOriginCurrencyCurrencyId(Integer originCurrencyId);

    List<CurrencyConversionRate> findAllByDate(LocalDate date);
}
