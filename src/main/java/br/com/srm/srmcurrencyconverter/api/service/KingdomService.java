package br.com.srm.srmcurrencyconverter.api.service;

import br.com.srm.srmcurrencyconverter.api.dto.request.KingdomDto;
import br.com.srm.srmcurrencyconverter.api.model.Kingdom;
import br.com.srm.srmcurrencyconverter.api.repository.KingdomRepository;
import br.com.srm.srmcurrencyconverter.config.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KingdomService {

    private final KingdomRepository kingdomRepository;

    public List<Kingdom> getAllKingdoms() {
        return kingdomRepository.findAll();
    }

    public Kingdom getKingdomById(Integer kingdomId) {
        return kingdomRepository.findById(kingdomId).orElseThrow(() -> new  DataNotFoundException("Kingdom not found", "kingdomId", kingdomId));
    }

    public Kingdom saveKingdom(KingdomDto kingdomDto) {
        return kingdomRepository.save(new Kingdom(kingdomDto));
    }
}
