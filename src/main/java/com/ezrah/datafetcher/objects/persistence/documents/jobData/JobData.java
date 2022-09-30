package com.ezrah.datafetcher.objects.persistence.documents.jobData;

import com.arangodb.springframework.annotation.Document;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Document("job_data")
@Getter
@Setter
@NoArgsConstructor
public class JobData {

    @Id
    Integer key;
    
    BillUpdatingJobData billUpdatingJobData;
}
