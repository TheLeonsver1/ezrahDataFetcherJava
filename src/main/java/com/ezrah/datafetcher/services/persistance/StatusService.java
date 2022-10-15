package com.ezrah.datafetcher.services.persistance;

import com.ezrah.datafetcher.persistence.entities.Status;

import java.util.Optional;

public interface StatusService {
    Optional<Status> getByKnsId(Integer knsId);

    Status save(Status status);

    long countStatuses();

}
