package com.ezrah.datafetcher.repositories;

import com.ezrah.datafetcher.objects.persistence.documents.Bill;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepo extends PagingAndSortingRepository<Bill, Integer> {
    List<Bill> getAllByKnsBillID(List<Integer> knsBillIDs);
}
