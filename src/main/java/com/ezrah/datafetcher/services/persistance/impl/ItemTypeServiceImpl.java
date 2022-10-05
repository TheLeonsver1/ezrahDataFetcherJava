package com.ezrah.datafetcher.services.persistance.impl;

import com.ezrah.datafetcher.objects.persistence.entities.ItemType;
import com.ezrah.datafetcher.repositories.ItemTypeRepo;
import com.ezrah.datafetcher.services.persistance.ItemTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ItemTypeServiceImpl implements ItemTypeService {
    private final ItemTypeRepo itemTypeRepo;

    public ItemType getByKnsTypeID(Integer knsTypeId) {
        return itemTypeRepo.findByKnsTypeId(knsTypeId);
    }

    @Override
    public ItemType save(ItemType itemType) {
        return itemTypeRepo.save(itemType);
    }

//    @Override
//    public List<ItemType> saveAll(List<ItemType> itemTypes) {
//        return itemTypeRepo.saveAll(itemTypes);
//    }
}
