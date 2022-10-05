package com.ezrah.datafetcher.services.knessetApi;

import com.ezrah.datafetcher.objects.knessetOdataApi.ObjectBatch;
import com.ezrah.datafetcher.objects.persistence.entities.ItemType;

import java.util.Optional;

public interface APITypeService {
    Optional<ObjectBatch<ItemType>> getTypeBatch(String nextBatchUri);
}
