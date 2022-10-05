package com.ezrah.datafetcher.services.persistance;

import com.ezrah.datafetcher.objects.persistence.entities.Status;

public interface StatusService {
    Status getByKnsStatusID(Integer knsStatusID);

    Status save(Status status);

}
