package com.ezrah.datafetcher.services.persistance;

import com.ezrah.datafetcher.objects.persistence.entities.Bill;

import java.util.List;
import java.util.Optional;

public interface BillService {
    List<Bill> findAllByKnsBillIds(List<Integer> knsBillsIds);

    Optional<Bill> findByKnsBillId(Integer knsBillId);

    Bill upsertBill(Bill bill);

    List<Bill> saveAll(List<Bill> bills);
}
