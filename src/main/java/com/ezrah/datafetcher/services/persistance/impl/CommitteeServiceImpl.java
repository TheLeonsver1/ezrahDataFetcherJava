package com.ezrah.datafetcher.services.persistance.impl;

import com.ezrah.datafetcher.persistence.entities.Committee;
import com.ezrah.datafetcher.persistence.repositories.CommitteeRepo;
import com.ezrah.datafetcher.services.persistance.CommitteeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommitteeServiceImpl implements CommitteeService {
    private final CommitteeRepo committeeRepo;

    @Override
    public Optional<Committee> findByKnsId(Integer knsId) {
        return committeeRepo.findByKnsId(knsId);
    }

    @Override
    public Committee save(Committee committee) {
        if (committee.getKnsParentCommitteeId() != null) {
            var parentSearchResult = committeeRepo.findByKnsId(committee.getKnsParentCommitteeId());
            if (parentSearchResult.isPresent()) {
                committee.setParentCommittee(parentSearchResult.get());
            } else {
                log.error("Couldn't find parent committee in database");
            }
        }
        return committeeRepo.save(committee);
    }
}
