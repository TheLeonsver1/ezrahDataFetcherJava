package com.ezrah.datafetcher.persistence.repositories;

import com.ezrah.datafetcher.persistence.entities.PersonPosition;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PersonToPositionRepo extends PagingAndSortingRepository<PersonPosition, Integer> {
    Optional<PersonPosition> findByKnsId(Integer knsId);
}
