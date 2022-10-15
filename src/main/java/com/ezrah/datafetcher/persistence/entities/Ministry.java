package com.ezrah.datafetcher.persistence.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Mapping of KNS_GovMinistry
 */
@Entity
@Table(name = "ministries")
@Getter
@Setter
public class Ministry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @JsonAlias("GovMinistryID")
    Integer knsId;

    @JsonAlias("Name")
    String name;

    @JsonAlias("IsActive")
    boolean isActive;

    @JsonAlias("LastUpdatedDate")
    LocalDateTime knsLastUpdatedDate;

    @OneToMany(mappedBy = "ministryInCharge")
    List<IsraeliLaw> lawsInChargeOf;

    @OneToMany(mappedBy = "ministryPersonWasIn")
    List<PersonPosition> personsInPositions;
}
