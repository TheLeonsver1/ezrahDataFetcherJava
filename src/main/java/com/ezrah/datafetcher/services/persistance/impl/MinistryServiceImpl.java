package com.ezrah.datafetcher.services.persistance.impl;

import com.ezrah.datafetcher.persistence.entities.Ministry;
import com.ezrah.datafetcher.persistence.repositories.MinistryRepo;
import com.ezrah.datafetcher.services.persistance.MinistryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MinistryServiceImpl implements MinistryService {
    private final MinistryRepo ministryRepo;

    @Override
    public Optional<Ministry> findByKnsId(Integer knsId) {
        return ministryRepo.findByKnsId(knsId);
    }

    @Override
    public Ministry save(Ministry ministry) {
        return ministryRepo.save(ministry);
    }
}
