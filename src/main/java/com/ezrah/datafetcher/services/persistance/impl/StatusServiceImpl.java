package com.ezrah.datafetcher.services.persistance.impl;

import com.ezrah.datafetcher.objects.persistence.entities.Status;
import com.ezrah.datafetcher.repositories.StatusRepo;
import com.ezrah.datafetcher.services.persistance.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class StatusServiceImpl implements StatusService {
    private final StatusRepo statusRepo;

    @Override
    public Status getByKnsStatusID(Integer knsStatusID) {
        return statusRepo.findByKnsStatusId(knsStatusID);
    }

    @Override
    public Status save(Status status) {
        return statusRepo.save(status);
    }
}
