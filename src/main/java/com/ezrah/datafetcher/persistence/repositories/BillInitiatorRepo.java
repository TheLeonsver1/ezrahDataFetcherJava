package com.ezrah.datafetcher.persistence.repositories;

import com.ezrah.datafetcher.persistence.entities.BillInitiator;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface BillInitiatorRepo extends PagingAndSortingRepository<BillInitiator, Integer> {
    Optional<BillInitiator> findByKnsId(Integer knsId);
}
