package com.ezrah.datafetcher.persistence.repositories;

import com.ezrah.datafetcher.persistence.entities.ItemType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemTypeRepo extends PagingAndSortingRepository<ItemType, Integer> {

    Optional<ItemType> findByKnsId(Integer knsTypeId);
}
