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
 * A person who is related to the knesset, maps to a KNS_Person in knesset api
 */
@Document("persons")
@Getter
@Setter
@NoArgsConstructor
public class Person {

    @Id
    Integer key;

    @PersistentIndexed(unique = true)
    Integer knsPersonID;

    /**
     * The datetime the person was added to our db
     */
    @CreatedDate
    LocalDateTime createdDate;

    /**
     * The datetime the person was last updated in our db
     */
    @LastModifiedDate
    LocalDateTime lastModifiedDate;
}
