package com.ezrah.datafetcher.persistence.repositories;

import com.ezrah.datafetcher.persistence.entities.Faction;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface FactionRepo extends PagingAndSortingRepository<Faction, Integer> {
    Optional<Faction> findByName(String name);
}
