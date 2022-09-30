package com.ezrah.datafetcher.repositories;

import com.ezrah.datafetcher.objects.persistence.documents.jobData.JobData;
import org.springframework.data.repository.CrudRepository;

public interface JobDataRepo extends CrudRepository<JobData, Integer> {
}
