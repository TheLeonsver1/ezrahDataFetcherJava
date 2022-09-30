package com.ezrah.datafetcher.repositories;

import com.arangodb.springframework.annotation.Query;
import com.ezrah.datafetcher.objects.persistence.documents.Bill;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepo extends PagingAndSortingRepository<Bill, Integer> {
    @Query("FOR bill in bills FILTER CONTAINS_ARRAY(@knsBillIDs, bill.knsBillID) RETURN bill")
    List<Bill> findAllByKnsBillID(@Param("knsBillIDs") Iterable<Integer> knsBillIDs);

}
