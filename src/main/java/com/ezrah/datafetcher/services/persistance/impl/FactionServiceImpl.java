package com.ezrah.datafetcher.services.persistance.impl;

import com.ezrah.datafetcher.persistence.entities.Faction;
import com.ezrah.datafetcher.persistence.entities.FactionPerKnesset;
import com.ezrah.datafetcher.persistence.repositories.FactionPerKnessetRepo;
import com.ezrah.datafetcher.persistence.repositories.FactionRepo;
import com.ezrah.datafetcher.services.persistance.FactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FactionServiceImpl implements FactionService {
    private final FactionRepo factionRepo;

    private final FactionPerKnessetRepo factionPerKnessetRepo;

    @Override
    public Optional<FactionPerKnesset> getFactionPerKnessetByKnsId(Integer knsId) {
        return factionPerKnessetRepo.findByKnsId(knsId);
    }

    @Override
    @Transactional
    public FactionPerKnesset saveFactionPerKnesset(FactionPerKnesset factionPerKnesset) {
        if (factionPerKnesset.getFaction() == null) {
            Optional<Faction> factionByNameResult = factionRepo.findByName(factionPerKnesset.getName());

            if (factionByNameResult.isPresent()) {
                factionPerKnesset.setFaction(factionByNameResult.get());
            } else {
                Faction faction = new Faction();
                faction.setName(factionPerKnesset.getName());
                faction = factionRepo.save(faction);
                factionPerKnesset.setFaction(faction);
            }
        }
        return factionPerKnessetRepo.save(factionPerKnesset);
    }
}
