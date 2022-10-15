package com.ezrah.datafetcher.persistence.repositories;

import com.ezrah.datafetcher.persistence.entities.Position;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PositionRepository extends PagingAndSortingRepository<Position, Integer> {
    Optional<Position> findByKnsId(Integer knsPositionId);

}
