package com.ezrah.datafetcher.services.knessetApi.impl;

import com.ezrah.datafetcher.common.KNSOdataRestTemplateUtils;
import com.ezrah.datafetcher.definitions.Definitions;
import com.ezrah.datafetcher.objects.knessetOdataApi.ObjectBatchWrapper;
import com.ezrah.datafetcher.services.knessetApi.APITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class APITypeServiceImpl implements APITypeService {
    private final KNSOdataRestTemplateUtils knsOdataRestTemplateUtils;

    private static final String GET_ITEM_TYPES_URI = Definitions.KNESSET_ODATA_API_URL + "KNS_ItemType";

    @Autowired
    APITypeServiceImpl(KNSOdataRestTemplateUtils knsOdataRestTemplateUtils) {
        this.knsOdataRestTemplateUtils = knsOdataRestTemplateUtils;
    }

    @Override
    public ObjectBatchWrapper getTypeBatch(String nextBatchUri) {
        return knsOdataRestTemplateUtils.getOdataFeed(GET_ITEM_TYPES_URI, nextBatchUri);
    }
}
