package com.ezrah.datafetcher.persistence.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * An item's status in the knesset, maps to a KNS_Status in knesset api
 */
@Entity
@Table(name = "statuses")
@Getter
@Setter
@NoArgsConstructor
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @JsonAlias("StatusID")
    @Column(unique = true)
    Integer knsId;

    @ManyToOne(fetch = FetchType.LAZY)
    ItemType statusType;

    @JsonAlias("TypeID")
    Integer knsSubTypeId;

    @JsonAlias("Desc")
    String description;

    @JsonAlias("LastUpdatedDate")
    String knsLastUpdatedDate;

}
