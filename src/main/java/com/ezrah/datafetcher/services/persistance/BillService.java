package com.ezrah.datafetcher.services.persistance;

import com.ezrah.datafetcher.objects.persistence.documents.Bill;

import java.util.List;

public interface BillService {
    List<Bill> getBillsByKnsBillIds(List<Integer> knsBillsIds);

    Bill save(Bill bill);
}
