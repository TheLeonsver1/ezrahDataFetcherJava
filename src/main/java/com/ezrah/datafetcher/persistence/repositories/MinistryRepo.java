package com.ezrah.datafetcher.persistence.repositories;

import com.ezrah.datafetcher.persistence.entities.Ministry;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface MinistryRepo extends PagingAndSortingRepository<Ministry, Integer> {
    Optional<Ministry> findByKnsId(Integer knsId);
}
