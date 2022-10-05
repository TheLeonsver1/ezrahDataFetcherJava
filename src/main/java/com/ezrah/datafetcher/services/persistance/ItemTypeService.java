package com.ezrah.datafetcher.services.persistance;

import com.ezrah.datafetcher.objects.persistence.entities.ItemType;

public interface ItemTypeService {
    ItemType getByKnsTypeID(Integer knsTypeID);

    ItemType save(ItemType itemType);

//    List<ItemType> saveAll(List<ItemType> itemTypes);
}
