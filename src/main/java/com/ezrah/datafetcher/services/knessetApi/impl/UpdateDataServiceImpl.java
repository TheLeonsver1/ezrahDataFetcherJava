package com.ezrah.datafetcher.services.knessetApi.impl;

import com.ezrah.datafetcher.common.KNSOdataRestTemplateUtils;
import com.ezrah.datafetcher.common.KnessetOdataApiForEachBatchTemplate;
import com.ezrah.datafetcher.enums.KNSApiDataTypes;
import com.ezrah.datafetcher.objects.ObjectBatch;
import com.ezrah.datafetcher.persistence.entities.*;
import com.ezrah.datafetcher.services.knessetApi.UpdateDataService;
import com.ezrah.datafetcher.services.persistance.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Service
public class UpdateDataServiceImpl implements UpdateDataService {

    private final MinistryService ministryService;
    private final CommitteeService committeeService;

    private final BillService billService;

    private final ItemTypeService itemTypeService;

    private final StatusService statusService;

    private final PersonService personService;

    private final PositionService positionService;

    private final FactionService factionService;

    private final KNSOdataRestTemplateUtils knsOdataRestTemplateUtils;


    public void updateBills(String nextBatchUri) throws InterruptedException {
        new KnessetOdataApiForEachBatchTemplate<Bill>() {
            @Override
            public void forEachBatch(int batchIndex, ObjectBatch<Bill> billBatch) {
                billBatch.getValue().stream()
                        .filter(updatedBill -> {
                            var persistedBill = billService.findBillByKnsId(updatedBill.getKnsId());
                            return persistedBill.isEmpty()
                                    || Objects.isNull(persistedBill.get().getKnsLastUpdatedDate())
                                    || persistedBill.get().getKnsLastUpdatedDate().compareTo(updatedBill.getKnsLastUpdatedDate()) < 0;
                        })
                        .forEach(billService::upsertBill);
            }
        }.start(log, new ParameterizedTypeReference<>() {
        }, KNSApiDataTypes.BILL);
    }

    public void updateItemTypes(String nextBatchUri) throws InterruptedException {

        new KnessetOdataApiForEachBatchTemplate<ItemType>() {
            @Override
            public void forEachBatch(int batchIndex, ObjectBatch<ItemType> itemTypeBatch) {
                itemTypeBatch.getValue().stream()
                        .filter(updatedType -> {
                            var persistedItemType = itemTypeService.getByKnsId(updatedType.getKnsId());
                            return persistedItemType.isEmpty();
                        })
                        .forEach(itemTypeService::save);
            }
        }.start(log, new ParameterizedTypeReference<>() {
        }, KNSApiDataTypes.ITEM_TYPE);
    }

    @Override
    public void updateStatuses(String nextBatchUri) throws InterruptedException {
        new KnessetOdataApiForEachBatchTemplate<Status>() {
            @Override
            public void forEachBatch(int batchIndex, ObjectBatch<Status> statusBatch) {
                statusBatch.getValue().stream()
                        .filter(updatedStatus -> {
                            var persistedStatus = statusService.getByKnsId(updatedStatus.getKnsId());
                            return persistedStatus.isEmpty();
                        })
                        .forEach(status -> {
                            itemTypeService.getByKnsId(status.getKnsSubTypeId()).ifPresent(status::setStatusType);
                            statusService.save(status);
                        });
            }
        }.start(log, new ParameterizedTypeReference<>() {
        }, KNSApiDataTypes.STATUS);
    }

    @Override
    public void updatePersons(String nextBatchUri) throws InterruptedException {
        new KnessetOdataApiForEachBatchTemplate<Person>() {
            @Override
            public void forEachBatch(int batchIndex, ObjectBatch<Person> personBatch) {
                personBatch.getValue().stream()
                        .filter(updatedPerson -> {
                            var persistedPerson = personService.getPersonByKnsId(updatedPerson.getKnsId());
                            return persistedPerson.isEmpty();
                        })
                        .forEach(personService::savePerson);
            }
        }.start(log, new ParameterizedTypeReference<>() {
        }, KNSApiDataTypes.PERSON);
    }

    @Override
    public void updatePositions(String nextBatchUri) throws InterruptedException {
        new KnessetOdataApiForEachBatchTemplate<Position>() {
            @Override
            public void forEachBatch(int batchIndex, ObjectBatch<Position> positionnBatch) {
                positionnBatch.getValue().stream()
                        .filter(updatedPosition -> {
                            var persistedPosition = positionService.getByKnsId(updatedPosition.getKnsId());
                            return persistedPosition.isEmpty();
                        })
                        .forEach(positionService::save);
            }
        }.start(log, new ParameterizedTypeReference<>() {
        }, KNSApiDataTypes.POSITION);
    }

    @Override
    public void updateFactions(String nextBatchUri) throws InterruptedException {
        new KnessetOdataApiForEachBatchTemplate<FactionPerKnesset>() {
            @Override
            public void forEachBatch(int batchIndex, ObjectBatch<FactionPerKnesset> factionPerKnessetBatch) {
                factionPerKnessetBatch.getValue().stream()
                        .filter(updatedFactionPerKnesset -> {
                            var persistedFactionPerKnesset = factionService.getFactionPerKnessetByKnsId(updatedFactionPerKnesset.getKnsId());
                            return persistedFactionPerKnesset.isEmpty();
                        })
                        .forEach(factionService::saveFactionPerKnesset);
            }
        }.start(log, new ParameterizedTypeReference<>() {
        }, KNSApiDataTypes.FACTION_PER_KNESSET);
    }

    @Override
    public void updateMinistries(String nextBatchUri) throws InterruptedException {
        new KnessetOdataApiForEachBatchTemplate<Ministry>() {
            @Override
            public void forEachBatch(int batchIndex, ObjectBatch<Ministry> ministriesBatch) {
                ministriesBatch.getValue().stream()
                        .filter(updatedMinistry -> {
                            var persistedMinistry = ministryService.findByKnsId(updatedMinistry.getKnsId());
                            return persistedMinistry.isEmpty();
                        })
                        .forEach(ministryService::save);
            }
        }.start(log, new ParameterizedTypeReference<>() {
        }, KNSApiDataTypes.MINISTRY);
    }

    @Override
    public void updateCommittees(String nextBatchUri) throws InterruptedException {
        updateCommitteesGeneric(KNSApiDataTypes.PARENT_COMMITTEE);
        updateCommitteesGeneric(KNSApiDataTypes.CHILD_COMMITTEE);
    }

    private void updateCommitteesGeneric(KNSApiDataTypes knsApiDataType) throws InterruptedException {
        new KnessetOdataApiForEachBatchTemplate<Committee>() {
            @Override
            public void forEachBatch(int batchIndex, ObjectBatch<Committee> committeesBatch) {
                committeesBatch.getValue().stream()
                        .filter(updatedCommittee -> {
                            var persistedMinistry = committeeService.findByKnsId(updatedCommittee.getKnsId());
                            return persistedMinistry.isEmpty();
                        })
                        .forEach(committeeService::save);
            }
        }.start(log, new ParameterizedTypeReference<>() {
        }, knsApiDataType);
    }

    @Override
    public void updatePersonToPosition(String nextBatchUri) throws InterruptedException {
        new KnessetOdataApiForEachBatchTemplate<PersonPosition>() {
            @Override
            public void forEachBatch(int batchIndex, ObjectBatch<PersonPosition> committeesBatch) {
                committeesBatch.getValue().stream()
                        .filter(updatedPersonPosition -> {
                            var persistedPersonPosition = personService.getPersonPositionByKnsId(updatedPersonPosition.getKnsId());
                            return persistedPersonPosition.isEmpty();
                        })
                        .forEach(personService::savePersonPosition);
            }
        }.start(log, new ParameterizedTypeReference<>() {
        }, KNSApiDataTypes.PERSON_TO_POSITION);
    }

    @Override
    public void updateBillInitiators(String nextBatchUri) throws InterruptedException {
        new KnessetOdataApiForEachBatchTemplate<BillInitiator>() {
            @Override
            public void forEachBatch(int batchIndex, ObjectBatch<BillInitiator> billInitiatorBatch) {
                billInitiatorBatch.getValue().stream()
                        .filter(updatedBillInitiator -> {
                            var persistedBillInitiator = billService.findBillInitiatorByKnsId(updatedBillInitiator.getKnsId());
                            return persistedBillInitiator.isEmpty();
                        })
                        .forEach(billService::saveBillInitiator);
            }
        }.start(log, new ParameterizedTypeReference<>() {
        }, KNSApiDataTypes.BILL_INITIATOR);
    }

    @Override
    public void updateBillHistoryInitiators(String nextBatchUri) throws InterruptedException {
        new KnessetOdataApiForEachBatchTemplate<BillHistoryInitiator>() {
            @Override
            public void forEachBatch(int batchIndex, ObjectBatch<BillHistoryInitiator> billInitiatorBatch) {
                billInitiatorBatch.getValue().stream()
                        .filter(updatedBillInitiator -> {
                            var persistedBillHistoryInitiator = billService.findBillHistoryInitiatorByKnsId(updatedBillInitiator.getKnsId());
                            return persistedBillHistoryInitiator.isEmpty();
                        })
                        .forEach(billService::saveBillHistoryInitiator);
            }
        }.start(log, new ParameterizedTypeReference<>() {
        }, KNSApiDataTypes.BILL_HISTORY_INITIATOR);
    }

}
