package com.ezrah.datafetcher.repositories;

import com.ezrah.datafetcher.objects.persistence.entities.Bill;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepo extends PagingAndSortingRepository<Bill, Integer> {

    List<Bill> findAllByKnsBillIdIn(Collection<Integer> knsBillIDs);

    Optional<Bill> findByKnsBillId(Integer knsBillID);

}
