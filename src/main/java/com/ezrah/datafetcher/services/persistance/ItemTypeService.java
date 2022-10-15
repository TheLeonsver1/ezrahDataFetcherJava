package com.ezrah.datafetcher.services.persistance;

import com.ezrah.datafetcher.persistence.entities.ItemType;

import java.util.Optional;

public interface ItemTypeService {
    Optional<ItemType> getByKnsId(Integer knsId);

    ItemType save(ItemType itemType);

    long countItemTypes();

//    List<ItemType> saveAll(List<ItemType> itemTypes);
}
