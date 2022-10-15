package com.ezrah.datafetcher.services.persistance;

import com.ezrah.datafetcher.persistence.entities.Position;

import java.util.Optional;

public interface PositionService {
    Optional<Position> getByKnsId(Integer KnsId);

    Position save(Position position);

    long countPositions();

}
