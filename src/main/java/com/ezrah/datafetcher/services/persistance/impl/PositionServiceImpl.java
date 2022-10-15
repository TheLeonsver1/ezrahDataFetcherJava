package com.ezrah.datafetcher.services.persistance.impl;

import com.ezrah.datafetcher.persistence.entities.Position;
import com.ezrah.datafetcher.persistence.repositories.PositionRepository;
import com.ezrah.datafetcher.services.persistance.PositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;

    @Override
    public Optional<Position> getByKnsId(Integer knsId) {
        return positionRepository.findByKnsId(knsId);
    }

    @Override
    public Position save(Position position) {
        return positionRepository.save(position);
    }

    @Override
    public long countPositions() {
        return positionRepository.count();
    }
}
