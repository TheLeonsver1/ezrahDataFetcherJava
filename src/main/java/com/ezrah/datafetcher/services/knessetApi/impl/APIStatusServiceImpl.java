package com.ezrah.datafetcher.services.knessetApi.impl;

import com.ezrah.datafetcher.common.KNSOdataRestTemplateUtils;
import com.ezrah.datafetcher.definitions.Definitions;
import com.ezrah.datafetcher.objects.knessetOdataApi.ObjectBatch;
import com.ezrah.datafetcher.objects.knessetOdataApi.OdataFeedObjectBatch;
import com.ezrah.datafetcher.objects.persistence.entities.Status;
import com.ezrah.datafetcher.services.knessetApi.APIStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class APIStatusServiceImpl implements APIStatusService {
    private final KNSOdataRestTemplateUtils knsOdataRestTemplateUtils;

    private static final String GET_STATUSES_URI = Definitions.KNESSET_ODATA_API_URL + "KNS_Status";

    private static final ParameterizedTypeReference<OdataFeedObjectBatch<Status>> TYPE_REFERENCE = new ParameterizedTypeReference<>() {
    };

    @Autowired
    APIStatusServiceImpl(KNSOdataRestTemplateUtils knsOdataRestTemplateUtils) {
        this.knsOdataRestTemplateUtils = knsOdataRestTemplateUtils;
    }

    @Override
    public Optional<ObjectBatch<Status>> getStatusBatch(String nextBatchUri) {
        return knsOdataRestTemplateUtils.getOdataFeed(TYPE_REFERENCE, GET_STATUSES_URI, nextBatchUri);
    }
}
