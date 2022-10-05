package com.ezrah.datafetcher.services.knessetApi;

import com.ezrah.datafetcher.objects.knessetOdataApi.ObjectBatch;
import com.ezrah.datafetcher.objects.persistence.entities.Bill;

import java.util.Optional;

public interface APIBillService {
    Optional<ObjectBatch<Bill>> getBillBatch(String nextBatchUri);
}
