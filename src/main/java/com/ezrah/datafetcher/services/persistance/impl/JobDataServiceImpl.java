package com.ezrah.datafetcher.services.persistance.impl;

import com.ezrah.datafetcher.objects.persistence.documents.jobData.JobData;
import com.ezrah.datafetcher.repositories.JobDataRepo;
import com.ezrah.datafetcher.services.persistance.JobDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class JobDataServiceImpl implements JobDataService {

    private final JobDataRepo jobDataRepo;

    @Override
    public JobData getJobData() {
        return jobDataRepo.findAll().iterator().next();
    }
}
