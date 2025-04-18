package br.com.srm.srmcurrencyconverter.api.repository;

import br.com.srm.srmcurrencyconverter.api.model.Kingdom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KingdomRepository extends JpaRepository<Kingdom, Integer> {
}
