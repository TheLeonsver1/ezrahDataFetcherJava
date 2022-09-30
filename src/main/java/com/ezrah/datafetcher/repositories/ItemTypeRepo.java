package com.ezrah.datafetcher.repositories;

import com.ezrah.datafetcher.objects.persistence.documents.ItemType;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ItemTypeRepo extends PagingAndSortingRepository<ItemType, Integer> {

    List<ItemType> saveAll(List<ItemType> itemTypes);
}
