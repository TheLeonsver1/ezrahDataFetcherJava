package com.ezrah.datafetcher.services.persistance;

import com.ezrah.datafetcher.persistence.entities.Ministry;

import java.util.Optional;

public interface MinistryService {
    Optional<Ministry> findByKnsId(Integer knsId);

    Ministry save(Ministry ministry);
}
