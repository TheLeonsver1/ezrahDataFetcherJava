package com.ezrah.datafetcher.persistence.repositories;

import com.ezrah.datafetcher.persistence.entities.Committee;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CommitteeRepo extends PagingAndSortingRepository<Committee, Integer> {
    Optional<Committee> findByKnsId(Integer knsId);
}
