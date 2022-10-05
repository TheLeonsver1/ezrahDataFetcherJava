package com.ezrah.datafetcher.services.knessetApi;

import com.ezrah.datafetcher.objects.knessetOdataApi.ObjectBatch;
import com.ezrah.datafetcher.objects.persistence.entities.Status;

import java.util.Optional;

public interface APIStatusService {
    Optional<ObjectBatch<Status>> getStatusBatch(String nextBatchUri);
}
