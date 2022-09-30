package com.ezrah.datafetcher.services.knessetApi.impl;

import com.ezrah.datafetcher.common.KNSOdataRestTemplateUtils;
import com.ezrah.datafetcher.definitions.Definitions;
import com.ezrah.datafetcher.objects.knessetOdataApi.ObjectBatchWrapper;
import com.ezrah.datafetcher.services.knessetApi.APIBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class APIBillServiceImpl implements APIBillService {
    private final KNSOdataRestTemplateUtils knsOdataRestTemplateUtils;

    private static final String GET_BILLS_URI = Definitions.KNESSET_ODATA_API_URL + "KNS_Bill";

    @Autowired
    APIBillServiceImpl(KNSOdataRestTemplateUtils knsOdataRestTemplateUtils) {
        this.knsOdataRestTemplateUtils = knsOdataRestTemplateUtils;
    }

    @Override
    public ObjectBatchWrapper getBillBatch(String nextBatchUri) {
        return knsOdataRestTemplateUtils.getOdataFeed(GET_BILLS_URI, nextBatchUri);
    }
}
