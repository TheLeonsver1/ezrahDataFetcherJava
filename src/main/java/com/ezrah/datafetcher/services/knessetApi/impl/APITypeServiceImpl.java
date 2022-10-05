package com.ezrah.datafetcher.services.knessetApi.impl;

import com.ezrah.datafetcher.common.KNSOdataRestTemplateUtils;
import com.ezrah.datafetcher.definitions.Definitions;
import com.ezrah.datafetcher.objects.knessetOdataApi.ObjectBatch;
import com.ezrah.datafetcher.objects.knessetOdataApi.OdataFeedObjectBatch;
import com.ezrah.datafetcher.objects.persistence.entities.ItemType;
import com.ezrah.datafetcher.services.knessetApi.APITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class APITypeServiceImpl implements APITypeService {
    private final KNSOdataRestTemplateUtils knsOdataRestTemplateUtils;

    private static final String GET_ITEM_TYPES_URI = Definitions.KNESSET_ODATA_API_URL + "KNS_ItemType";

    private static final ParameterizedTypeReference<OdataFeedObjectBatch<ItemType>> TYPE_REFERENCE = new ParameterizedTypeReference<>() {
    };

    @Autowired
    APITypeServiceImpl(KNSOdataRestTemplateUtils knsOdataRestTemplateUtils) {
        this.knsOdataRestTemplateUtils = knsOdataRestTemplateUtils;
    }

    @Override
    public Optional<ObjectBatch<ItemType>> getTypeBatch(String nextBatchUri) {
        return knsOdataRestTemplateUtils.getOdataFeed(TYPE_REFERENCE, GET_ITEM_TYPES_URI, nextBatchUri);
    }
}
