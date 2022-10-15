package com.ezrah.datafetcher.services.knessetApi;

public interface UpdateDataService {
    void updateBills(String nextBatchUri) throws InterruptedException;

    void updateItemTypes(String nextBatchUri) throws InterruptedException;

    void updateStatuses(String nextBatchUri) throws InterruptedException;

    void updatePersons(String nextBatchUri) throws InterruptedException;

    void updatePositions(String nextBatchUri) throws InterruptedException;

    void updateFactions(String nextBatchUri) throws InterruptedException;

    void updateMinistries(String nextBatchUri) throws InterruptedException;

    void updateCommittees(String nextBatchUri) throws InterruptedException;

    void updatePersonToPosition(String nextBatchUri) throws InterruptedException;

    void updateBillInitiators(String nextBatchUri) throws InterruptedException;

    void updateBillHistoryInitiators(String nextBatchUri) throws InterruptedException;

}
