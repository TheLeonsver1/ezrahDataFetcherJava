package com.ezrah.datafetcher.persistence.repositories;

import com.ezrah.datafetcher.persistence.entities.Bill;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepo extends PagingAndSortingRepository<Bill, Integer> {

    List<Bill> findAllByKnsIdIn(Collection<Integer> knsBillIDs);

    Optional<Bill> findByKnsId(Integer knsBillID);

}
