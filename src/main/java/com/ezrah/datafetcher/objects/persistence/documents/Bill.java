package com.ezrah.datafetcher.objects.persistence.documents;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.PersistentIndexed;
import com.arangodb.springframework.annotation.Ref;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A knesset bill, maps to a KNS_Bill in knesset api
 */
@Document("bills")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bill implements Serializable {

    @Id
    Integer key;

    /**
     * Bill id in knesset db
     */
    @PersistentIndexed(unique = true)
    @JsonProperty("BillID")
    Integer knsBillID;

    /**
     * The knesset number when the bill passed 3rd call
     */
    @JsonProperty("KnessetNum")
    Integer knessetNum;

    /**
     * Bill name
     */
    @JsonProperty("Name")
    String name;

    @Ref
    ItemType itemType;

    @Ref
    Status status;

    /**
     * The official summary of the bill
     * Only has text if the bill passed 3rd call,
     */
    @JsonProperty("SummaryLaw")
    String summaryLaw;

    /**
     * The datetime the bill was officially published to the public in the books
     */
    @JsonProperty("PublicationDate")
    LocalDateTime publicationDate;

    /**
     * The datetime the bill last updated in the Knesset api
     */
    @JsonProperty("LastUpdatedDate")
    LocalDateTime knsLastUpdatedDate;

    /**
     * The bill's subtype in the knesset api
     */
    @JsonProperty("SubTypeID")
    Integer knsSubTypeID;

    /**
     * The bill's status in the knesset api
     */
    @JsonProperty("StatusID")
    Integer knsStatusID;

    /**
     * The datetime the bill was added to our db
     */
    @CreatedDate
    LocalDateTime createdDate;

    /**
     * The datetime the bill was last updated in our db
     */
    @LastModifiedDate
    LocalDateTime lastModifiedDate;
}
