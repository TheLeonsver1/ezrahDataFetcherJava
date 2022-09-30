package com.ezrah.datafetcher.controllers;

import com.ezrah.datafetcher.objects.knessetOdataApi.ObjectBatchWrapper;
import com.ezrah.datafetcher.objects.persistence.documents.ItemType;
import com.ezrah.datafetcher.services.knessetApi.APIBillService;
import com.ezrah.datafetcher.services.knessetApi.APIStatusService;
import com.ezrah.datafetcher.services.knessetApi.APITypeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GeneralController {

    private final APIBillService apiBillService;
    private final APIStatusService apiStatusService;
    private final APITypeService apiTypeService;

    @Autowired
    GeneralController(APIBillService apiBillService, APIStatusService apiStatusService, APITypeService apiTypeService) {
        this.apiBillService = apiBillService;
        this.apiStatusService = apiStatusService;
        this.apiTypeService = apiTypeService;
    }

    @GetMapping("/recreate-db-data")
    public void recreateDbData() {
        getTypes();
        getStatuses();
        getBills();
    }

    private void getTypes() {
        String nextLink = null;
        do {
            ObjectBatchWrapper batch = apiTypeService.getTypeBatch(nextLink);
            if (!CollectionUtils.isEmpty(batch.getValue())) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                List<ItemType> itemTypes = objectMapper.convertValue(batch.getValue(), new TypeReference<>() {
                });

                nextLink = batch.getNextBatchUri();


            }
        }
        while (StringUtils.hasText(nextLink));

    }
}
