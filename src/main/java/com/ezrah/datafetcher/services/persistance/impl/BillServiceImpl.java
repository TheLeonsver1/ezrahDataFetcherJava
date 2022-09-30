package com.ezrah.datafetcher.services.persistance.impl;

import com.ezrah.datafetcher.objects.persistence.documents.Bill;
import com.ezrah.datafetcher.repositories.BillRepo;
import com.ezrah.datafetcher.services.persistance.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    private final BillRepo billRepo;

    @Autowired
    BillServiceImpl(BillRepo billRepo) {
        this.billRepo = billRepo;
    }

    @Override
    public List<Bill> getBillsByKnsBillIds(List<Integer> knsBillsIds) {
        return billRepo.getAllByKnsBillID(knsBillsIds);
    }

    @Override
    public Bill save(Bill bill) {
        return billRepo.save(bill);
    }


}
