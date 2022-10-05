package com.ezrah.datafetcher.repositories;

import com.ezrah.datafetcher.objects.persistence.entities.Status;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepo extends PagingAndSortingRepository<Status, Integer> {

    Status findByKnsStatusId(Integer knsTypeId);
}
