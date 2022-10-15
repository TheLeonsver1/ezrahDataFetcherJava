package com.ezrah.datafetcher.services.persistance.impl;

import com.ezrah.datafetcher.persistence.entities.ItemType;
import com.ezrah.datafetcher.persistence.repositories.ItemTypeRepo;
import com.ezrah.datafetcher.services.persistance.ItemTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ItemTypeServiceImpl implements ItemTypeService {
    private final ItemTypeRepo itemTypeRepo;

    public Optional<ItemType> getByKnsId(Integer knsId) {
        return itemTypeRepo.findByKnsId(knsId);
    }

    @Override
    public ItemType save(ItemType itemType) {
        return itemTypeRepo.save(itemType);
    }

    @Override
    public long countItemTypes() {
        return itemTypeRepo.count();
    }

//    @Override
//    public List<ItemType> saveAll(List<ItemType> itemTypes) {
//        return itemTypeRepo.saveAll(itemTypes);
//    }
}
