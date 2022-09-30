package com.ezrah.datafetcher.services.knessetApi.impl;

import com.ezrah.datafetcher.common.KNSOdataRestTemplateUtils;
import com.ezrah.datafetcher.definitions.Definitions;
import com.ezrah.datafetcher.objects.knessetOdataApi.ObjectBatchWrapper;
import com.ezrah.datafetcher.services.knessetApi.APIStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class APIStatusServiceImpl implements APIStatusService {
    private final KNSOdataRestTemplateUtils knsOdataRestTemplateUtils;

    private static final String GET_STATUSES_URI = Definitions.KNESSET_ODATA_API_URL + "KNS_Status";

    @Autowired
    APIStatusServiceImpl(KNSOdataRestTemplateUtils knsOdataRestTemplateUtils) {
        this.knsOdataRestTemplateUtils = knsOdataRestTemplateUtils;
    }

    @Override
    public ObjectBatchWrapper getStatusBatch(String nextBatchUri) {
        return knsOdataRestTemplateUtils.getOdataFeed(GET_STATUSES_URI, nextBatchUri);
    }
}
