package com.ezrah.datafetcher.services.persistance;

import com.ezrah.datafetcher.objects.persistence.documents.ItemType;

import java.util.List;

public interface ItemTypeService {
    ItemType save(ItemType itemType);

    List<ItemType> saveAll(List<ItemType> itemTypes);
}
