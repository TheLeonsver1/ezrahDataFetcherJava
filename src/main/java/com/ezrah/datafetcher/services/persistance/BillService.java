package com.ezrah.datafetcher.services.persistance;

import com.ezrah.datafetcher.persistence.entities.Bill;
import com.ezrah.datafetcher.persistence.entities.BillHistoryInitiator;
import com.ezrah.datafetcher.persistence.entities.BillInitiator;

import java.util.List;
import java.util.Optional;

public interface BillService {
    List<Bill> findAllByKnsIds(List<Integer> knsBillsIds);

    Optional<Bill> findBillByKnsId(Integer knsBillId);

    Bill upsertBill(Bill bill);

    List<Bill> saveAll(List<Bill> bills);

    Optional<BillInitiator> findBillInitiatorByKnsId(Integer knsId);

    BillInitiator saveBillInitiator(BillInitiator billInitiator);

    Optional<BillHistoryInitiator> findBillHistoryInitiatorByKnsId(Integer knsId);

    BillHistoryInitiator saveBillHistoryInitiator(BillHistoryInitiator billInitiator);
}
