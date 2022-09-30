package com.ezrah.datafetcher.services.knessetApi.impl;

import com.ezrah.datafetcher.common.KNSOdataRestTemplateUtils;
import com.ezrah.datafetcher.definitions.Definitions;
import com.ezrah.datafetcher.objects.knessetOdataApi.ObjectBatch;
import com.ezrah.datafetcher.objects.knessetOdataApi.OdataFeedObjectBatch;
import com.ezrah.datafetcher.objects.persistence.documents.Bill;
import com.ezrah.datafetcher.services.knessetApi.APIBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class APIBillServiceImpl implements APIBillService {
    private final KNSOdataRestTemplateUtils knsOdataRestTemplateUtils;

    private static final String GET_BILLS_URI = Definitions.KNESSET_ODATA_API_URL + "KNS_Bill";

    private static final ParameterizedTypeReference<OdataFeedObjectBatch<Bill>> TYPE_REFERENCE = new ParameterizedTypeReference<>() {
    };

    @Autowired
    APIBillServiceImpl(KNSOdataRestTemplateUtils knsOdataRestTemplateUtils) {
        this.knsOdataRestTemplateUtils = knsOdataRestTemplateUtils;
    }

    @Override
    public Optional<ObjectBatch<Bill>> getBillBatch(String nextBatchUri) {
        return knsOdataRestTemplateUtils.getOdataFeed(TYPE_REFERENCE, GET_BILLS_URI, nextBatchUri);
    }
}
