package com.ezrah.datafetcher.services.persistance;

import com.ezrah.datafetcher.persistence.entities.Committee;

import java.util.Optional;

public interface CommitteeService {
    Optional<Committee> findByKnsId(Integer knsId);

    Committee save(Committee committee);
}
