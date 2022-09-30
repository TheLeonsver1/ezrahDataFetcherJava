package com.ezrah.datafetcher.services.persistance;

import com.ezrah.datafetcher.objects.persistence.documents.ItemType;

public interface ItemTypeService {
    ItemType save(ItemType itemType);

//    List<ItemType> saveAll(List<ItemType> itemTypes);
}
