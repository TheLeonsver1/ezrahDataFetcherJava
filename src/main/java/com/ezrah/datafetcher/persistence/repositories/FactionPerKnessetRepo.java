package com.ezrah.datafetcher.persistence.repositories;

import com.ezrah.datafetcher.persistence.entities.FactionPerKnesset;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface FactionPerKnessetRepo extends PagingAndSortingRepository<FactionPerKnesset, Integer> {
    Optional<FactionPerKnesset> findByKnsId(Integer knsId);
}
