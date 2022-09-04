package com.ezrah.datafetcher.objects.persistence.documents;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.PersistentIndexed;
import com.arangodb.springframework.annotation.Ref;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

@Document
@Getter
@Setter
@NoArgsConstructor
public class Bill implements Serializable {

    @Id
    Integer key;

    /**
     * Bill id in knesset db
     */
    @PersistentIndexed(unique = true)
    Integer knsBillID;

    /**
     * The knesset number when the bill passed 3rd call
     */
    Integer knessetNum;

    /**
     * Bill name
     */
    String name;

    @Ref
    BillSubType subType;

    @Ref
    Status status;

    /**
     * Only has text if the bill passed 3rd call
     */
    String summaryLaw;

    /**
     * When was the bill published
     */
    LocalDateTime publicationDate;

    /**
     * When was the bill last updated in the Knesset api
     */
    LocalDateTime knsLastUpdatedDate;
}
