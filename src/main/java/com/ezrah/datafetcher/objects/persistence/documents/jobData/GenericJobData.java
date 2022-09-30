package com.ezrah.datafetcher.objects.persistence.documents.jobData;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class GenericJobData {

    private LocalDateTime lastAttemptDate;

    private LocalDateTime lastSuccessfulAttemptDate;
}
