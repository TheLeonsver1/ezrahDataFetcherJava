package com.ezrah.datafetcher.objects.persistence.documents;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.PersistentIndexed;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * An item's status in the knesset, maps to a KNS_Status in knesset api
 */
@Document("statuses")
@Getter
@Setter
@NoArgsConstructor
public class Status {
    @Id
    Integer key;

    @PersistentIndexed(unique = true)
    Integer knsStatusID;

    String description;

    /**
     * The datetime the status was added to our db
     */
    @CreatedDate
    LocalDateTime createdDate;

    /**
     * The datetime the status was last updated in our db
     */
    @LastModifiedDate
    LocalDateTime lastModifiedDate;

}
