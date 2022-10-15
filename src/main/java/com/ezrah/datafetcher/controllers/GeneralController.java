package com.ezrah.datafetcher.controllers;

import com.ezrah.datafetcher.services.knessetApi.UpdateDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GeneralController {

    private final UpdateDataService updateDataService;

    @GetMapping("/recreate-db-data")
    public String recreateDbData() {
        new Thread() {
            @Override
            public void run() {
                try {
//                    updateDataService.updateItemTypes(null);
//                    updateDataService.updateStatuses(null);
//                    updateDataService.updatePersons(null);
//                    updateDataService.updatePositions(null);
//                    updateDataService.updateFactions(null);
//                    updateDataService.updateMinistries(null);
//                    updateDataService.updateCommittees(null);
//                    updateDataService.updatePersonToPosition(null);
//                    updateDataService.updateBills(null);
                    updateDataService.updateBillInitiators(null);
                    updateDataService.updateBillHistoryInitiators(null);
                } catch (Exception e) {
                    log.error("Recreate db exception encountered", e);
                }
            }
        }.start();
        return "will start recreating";
//        getBills();
    }
}
