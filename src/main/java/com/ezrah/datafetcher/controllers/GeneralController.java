//package com.ezrah.datafetcher.controllers;
//
//import com.ezrah.datafetcher.objects.knessetOdataApi.ObjectBatch;
//import com.ezrah.datafetcher.objects.persistence.documents.ItemType;
//import com.ezrah.datafetcher.services.knessetApi.APIBillService;
//import com.ezrah.datafetcher.services.knessetApi.APIStatusService;
//import com.ezrah.datafetcher.services.knessetApi.APITypeService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//@Slf4j
//public class GeneralController {
//
//    private final APIBillService apiBillService;
////    private final APIStatusService apiStatusService;
////    private final APITypeService apiTypeService;
//
//    @Autowired
//    GeneralController(APIBillService apiBillService) {
//        this.apiBillService = apiBillService;
////        this.apiStatusService = apiStatusService;
////        this.apiTypeService = apiTypeService;
//    }
//
//    @GetMapping("/recreate-db-data")
//    public void recreateDbData() {
//        getTypes();
////        getStatuses();
////        getBills();
//    }
//
//    private void getTypes() {
//        String nextLink = null;
//        do {
//            ObjectBatch<ItemType> batch = apiTypeService.getTypeBatch(nextLink);
//            if (!CollectionUtils.isEmpty(batch.getValue())) {
//                log.info("getTypes", batch.getValue());
//            }
//            nextLink = batch.getNextBatchUri();
//        }
//        while (StringUtils.hasText(nextLink));
//
//    }
//}
