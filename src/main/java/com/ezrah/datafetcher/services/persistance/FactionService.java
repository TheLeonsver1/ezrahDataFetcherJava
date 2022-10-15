package com.ezrah.datafetcher.services.persistance;

import com.ezrah.datafetcher.persistence.entities.FactionPerKnesset;

import java.util.Optional;

public interface FactionService {

    Optional<FactionPerKnesset> getFactionPerKnessetByKnsId(Integer KnsId);

    FactionPerKnesset saveFactionPerKnesset(FactionPerKnesset factionPerKnesset);
}
