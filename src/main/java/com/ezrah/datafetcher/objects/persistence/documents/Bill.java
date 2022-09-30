package com.ezrah.datafetcher.objects.persistence.documents;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.PersistentIndexed;
import com.arangodb.springframework.annotation.Ref;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.time.LocalDateTime;

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
     * Only has text if the bill passed 3rd call
     */
    @JsonProperty("SummaryLaw")
    String summaryLaw;

    /**
     * When was the bill published
     */
    @JsonProperty("PublicationDate")
    LocalDateTime publicationDate;

    /**
     * When was the bill last updated in the Knesset api
     */
    @JsonProperty("LastUpdatedDate")
    LocalDateTime knsLastUpdatedDate;

    @Transient
    @JsonProperty("SubTypeID")
    Integer knsSubTypeID;

    @Transient
    @JsonProperty("StatusID")
    Integer knsStatusID;
}
