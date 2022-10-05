package com.ezrah.datafetcher.objects.persistence.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A knesset bill, maps to a KNS_Bill in knesset api
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    /**
     * Bill id in knesset db
     */
    @JsonAlias("BillID")
    @Column(unique = true)
    Integer knsBillId;

    /**
     * The knesset number when the bill passed 3rd call
     */
    @JsonAlias("KnessetNum")
    Integer knessetNum;

    /**
     * Bill name
     */
    @JsonAlias("Name")
    String name;

    @ManyToOne
    ItemType subType;

    @ManyToOne
    Status status;

    /**
     * The official summary of the bill
     * Only has text if the bill passed 3rd call,
     */
    @JsonAlias("SummaryLaw")
    @Column(length = 3000)
    String officialLawSummary;

    /**
     * The datetime the bill was officially published to the public in the books
     */
    @JsonAlias("PublicationDate")
    LocalDateTime publicationDate;

    /**
     * The datetime the bill last updated in the Knesset api
     */
    @JsonAlias("LastUpdatedDate")
    LocalDateTime knsLastUpdatedDate;

    /**
     * The bill's subtype in the knesset api
     */
    @JsonAlias("SubTypeID")
    Integer knsSubTypeId;

    /**
     * The bill's status in the knesset api
     */
    @JsonAlias("StatusID")
    Integer knsStatusId;
}
