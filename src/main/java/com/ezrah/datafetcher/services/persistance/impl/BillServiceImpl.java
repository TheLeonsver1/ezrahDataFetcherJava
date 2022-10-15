package com.ezrah.datafetcher.services.persistance.impl;

import com.ezrah.datafetcher.persistence.entities.Bill;
import com.ezrah.datafetcher.persistence.entities.BillHistoryInitiator;
import com.ezrah.datafetcher.persistence.entities.BillInitiator;
import com.ezrah.datafetcher.persistence.repositories.*;
import com.ezrah.datafetcher.services.persistance.BillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BillServiceImpl implements BillService {

    private final BillRepo billRepo;

    private final BillInitiatorRepo billInitiatorRepo;
    private final BillHistoryInitiatorRepo billHistoryInitiatorRepo;
    private final PersonRepository personRepository;
    private final StatusRepo statusRepo;

    @Override
    public List<Bill> findAllByKnsIds(List<Integer> knsBillsIds) {
        return billRepo.findAllByKnsIdIn(knsBillsIds);
    }

    @Override
    public Optional<Bill> findBillByKnsId(Integer knsBillId) {
        return billRepo.findByKnsId(knsBillId);
    }

    @Override
    @Transactional
    public Bill upsertBill(Bill billToSave) {
        Optional<Bill> possiblePersistedBill = findBillByKnsId(billToSave.getKnsId());
        if (possiblePersistedBill.isPresent()) {
            Bill persistedBill = possiblePersistedBill.get();
            persistedBill.setName(billToSave.getName());
            persistedBill.setKnessetNum(billToSave.getKnessetNum());
            persistedBill.setPublicationDate(billToSave.getPublicationDate());
            persistedBill.setOfficialLawSummary(billToSave.getOfficialLawSummary());
            persistedBill.setKnsLastUpdatedDate(billToSave.getKnsLastUpdatedDate());
            persistedBill.setPrivateNumber(billToSave.getPrivateNumber());
            persistedBill.setGovernmentalNumber(billToSave.getGovernmentalNumber());
            persistedBill.setKnsSubTypeId(billToSave.getKnsSubTypeId());
            if (!persistedBill.getKnsStatusId().equals(billToSave.getKnsStatusId())) {
                persistedBill.setKnsStatusId(billToSave.getKnsStatusId());
                statusRepo.findByKnsId(billToSave.getKnsStatusId())
                        .ifPresent(persistedBill::setStatus);
            }
            return persistedBill;
        } else {
            statusRepo.findByKnsId(billToSave.getKnsStatusId())
                    .ifPresent(billToSave::setStatus);
            return billRepo.save(billToSave);
        }
    }

    @Override
    public List<Bill> saveAll(List<Bill> updatedBills) {

        List<Integer> knsBillIds = updatedBills.stream()
                .map(Bill::getKnsId).toList();

        List<Bill> persistedBills = findAllByKnsIds(knsBillIds);

        for (Bill updatedBill : updatedBills) {
        }
        return updatedBills;
    }

    @Override
    public Optional<BillInitiator> findBillInitiatorByKnsId(Integer knsId) {
        return billInitiatorRepo.findByKnsId(knsId);
    }

    @Override
    @Transactional
    public BillInitiator saveBillInitiator(BillInitiator billInitiator) {

        var findBillResult = billRepo.findByKnsId(billInitiator.getKnsBillId());
        if (findBillResult.isPresent()) {
            billInitiator.setBill(findBillResult.get());
        } else {
            log.error("Failed matching bill to bill initiator {}", billInitiator);
        }

        var findPersonResult = personRepository.findByKnsId(billInitiator.getKnsPersonId());
        if (findPersonResult.isPresent()) {
            billInitiator.setPerson(findPersonResult.get());
        } else {
            log.error("Failed matching person to bill initiator {}", billInitiator);
        }
        return billInitiatorRepo.save(billInitiator);
    }

    @Override
    public Optional<BillHistoryInitiator> findBillHistoryInitiatorByKnsId(Integer knsId) {
        return billHistoryInitiatorRepo.findByKnsId(knsId);
    }

    @Override
    public BillHistoryInitiator saveBillHistoryInitiator(BillHistoryInitiator billHistoryInitiator) {

        var findBillResult = billRepo.findByKnsId(billHistoryInitiator.getKnsBillId());
        if (findBillResult.isPresent()) {
            billHistoryInitiator.setBill(findBillResult.get());
        } else {
            log.error("Failed matching bill to bill history initiator {}", billHistoryInitiator);
        }

        var findPersonResult = personRepository.findByKnsId(billHistoryInitiator.getKnsPersonId());
        if (findPersonResult.isPresent()) {
            billHistoryInitiator.setPerson(findPersonResult.get());
        } else {
            log.error("Failed matching person to bill history initiator {}", billHistoryInitiator);
        }
        return billHistoryInitiatorRepo.save(billHistoryInitiator);
    }

}
