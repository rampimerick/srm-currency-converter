package br.com.srm.srmcurrencyconverter.api.repository;

import br.com.srm.srmcurrencyconverter.api.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

}
