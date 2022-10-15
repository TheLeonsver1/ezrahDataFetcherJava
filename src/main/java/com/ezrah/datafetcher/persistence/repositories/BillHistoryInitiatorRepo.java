package com.ezrah.datafetcher.persistence.repositories;

import com.ezrah.datafetcher.persistence.entities.BillHistoryInitiator;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface BillHistoryInitiatorRepo extends PagingAndSortingRepository<BillHistoryInitiator, Integer> {
    Optional<BillHistoryInitiator> findByKnsId(Integer knsId);
}
