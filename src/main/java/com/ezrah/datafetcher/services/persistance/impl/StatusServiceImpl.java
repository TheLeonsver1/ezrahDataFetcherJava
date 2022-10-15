package com.ezrah.datafetcher.services.persistance.impl;

import com.ezrah.datafetcher.persistence.entities.Status;
import com.ezrah.datafetcher.persistence.repositories.StatusRepo;
import com.ezrah.datafetcher.services.persistance.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class StatusServiceImpl implements StatusService {
    private final StatusRepo statusRepo;

    @Override
    public Optional<Status> getByKnsId(Integer knsId) {
        return statusRepo.findByKnsId(knsId);
    }

    @Override
    public Status save(Status status) {
        return statusRepo.save(status);
    }

    @Override
    public long countStatuses() {
        return statusRepo.count();
    }
}
