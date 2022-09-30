package com.ezrah.datafetcher.services.persistance.impl;

import com.ezrah.datafetcher.objects.persistence.documents.ItemType;
import com.ezrah.datafetcher.repositories.ItemTypeRepo;
import com.ezrah.datafetcher.services.persistance.ItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemTypeServiceImpl implements ItemTypeService {
    private final ItemTypeRepo itemTypeRepo;

    @Autowired
    ItemTypeServiceImpl(ItemTypeRepo itemTypeRepo) {
        this.itemTypeRepo = itemTypeRepo;
    }

    @Override
    public ItemType save(ItemType itemType) {
        return itemTypeRepo.save(itemType);
    }

    @Override
    public List<ItemType> saveAll(List<ItemType> itemTypes) {
        return itemTypeRepo.saveAll(itemTypes);
    }
}
