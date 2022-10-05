package com.ezrah.datafetcher.services.persistance.impl;

import com.ezrah.datafetcher.objects.persistence.entities.Bill;
import com.ezrah.datafetcher.objects.persistence.entities.ItemType;
import com.ezrah.datafetcher.objects.persistence.entities.Status;
import com.ezrah.datafetcher.repositories.BillRepo;
import com.ezrah.datafetcher.repositories.ItemTypeRepo;
import com.ezrah.datafetcher.repositories.StatusRepo;
import com.ezrah.datafetcher.services.persistance.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BillServiceImpl implements BillService {

    private final BillRepo billRepo;

    private final ItemTypeRepo itemTypeRepo;

    private final StatusRepo statusRepo;

    @Override
    public List<Bill> findAllByKnsBillIds(List<Integer> knsBillsIds) {
        return billRepo.findAllByKnsBillIdIn(knsBillsIds);
    }

    @Override
    public Optional<Bill> findByKnsBillId(Integer knsBillId) {
        return billRepo.findByKnsBillId(knsBillId);
    }

    @Override
    @Transactional
    public Bill upsertBill(Bill billToSave) {
        Optional<Bill> possiblePersistedBill = findByKnsBillId(billToSave.getKnsBillId());
        if (possiblePersistedBill.isPresent()) {
            Bill persistedBill = possiblePersistedBill.get();
            if (!persistedBill.getName().equals(billToSave.getName())) {
                persistedBill.setName(billToSave.getName());
            }
            if (!persistedBill.getKnessetNum().equals(billToSave.getKnessetNum())) {
                persistedBill.setKnessetNum(billToSave.getKnessetNum());
            }
            if (!persistedBill.getPublicationDate().equals(billToSave.getPublicationDate())) {
                persistedBill.setPublicationDate(billToSave.getPublicationDate());
            }
            if (!persistedBill.getOfficialLawSummary().equals(billToSave.getOfficialLawSummary())) {
                persistedBill.setOfficialLawSummary(billToSave.getOfficialLawSummary());
            }
            if (!persistedBill.getKnsLastUpdatedDate().equals(billToSave.getKnsLastUpdatedDate())) {
                persistedBill.setKnsLastUpdatedDate(billToSave.getKnsLastUpdatedDate());
            }
            if (persistedBill.getKnsSubTypeId().equals(billToSave.getKnsSubTypeId())) {
                persistedBill.setKnsSubTypeId(billToSave.getKnsSubTypeId());
                ItemType newSubType = itemTypeRepo.findByKnsTypeId(billToSave.getKnsSubTypeId());
                persistedBill.setSubType(newSubType);
            }
            if (persistedBill.getKnsStatusId().equals(billToSave.getKnsStatusId())) {
                persistedBill.setKnsStatusId(billToSave.getKnsStatusId());
                Status newStatus = statusRepo.findByKnsStatusId(billToSave.getKnsStatusId());
                persistedBill.setStatus(newStatus);
            }
            return billRepo.save(persistedBill);
        } else {
            ItemType subType = itemTypeRepo.findByKnsTypeId(billToSave.getKnsSubTypeId());
            billToSave.setSubType(subType);
            Status status = statusRepo.findByKnsStatusId(billToSave.getKnsStatusId());
            billToSave.setStatus(status);
            return billRepo.save(billToSave);
        }
    }

    @Override
    public List<Bill> saveAll(List<Bill> updatedBills) {

        List<Integer> knsBillIds = updatedBills.stream()
                .map(Bill::getKnsBillId).toList();

        List<Bill> persistedBills = findAllByKnsBillIds(knsBillIds);

        for (Bill updatedBill : updatedBills) {
        }
        return updatedBills;
    }


}
