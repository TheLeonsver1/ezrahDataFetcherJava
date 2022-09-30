package com.ezrah.datafetcher.services.knessetApi;

import com.ezrah.datafetcher.objects.knessetOdataApi.ObjectBatchWrapper;

public interface APIStatusService {
    ObjectBatchWrapper getStatusBatch(String nextBatchUri);
}
