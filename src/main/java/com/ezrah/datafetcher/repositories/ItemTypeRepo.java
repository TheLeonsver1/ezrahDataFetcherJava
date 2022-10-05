package com.ezrah.datafetcher.repositories;

import com.ezrah.datafetcher.objects.persistence.entities.ItemType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemTypeRepo extends PagingAndSortingRepository<ItemType, Integer> {

    ItemType findByKnsTypeId(Integer knsTypeId);
}
