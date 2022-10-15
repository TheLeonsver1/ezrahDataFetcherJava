package com.ezrah.datafetcher.persistence.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "commities")
@Getter
@Setter
public class Committee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @JsonAlias("CommitteeID")
    Integer knsId;

    @JsonAlias("Name")
    String name;

    @JsonAlias("KnessetNum")
    Integer knessetNum;

    @JsonAlias("Email")
    String email;

    @JsonAlias("StartDate")
    LocalDateTime startDate;

    @JsonAlias("FinishDate")
    LocalDateTime finishDate;

    @JsonAlias("ParentCommitteeID")
    Integer knsParentCommitteeId;

    @OneToMany(mappedBy = "parentCommittee")
    List<Committee> childCommittees;

    @ManyToOne(fetch = FetchType.LAZY)
    Committee parentCommittee;

    @JsonAlias("CategoryID")
    Integer categoryID;

    @JsonAlias("CategoryDesc")
    String categoryDescription;

    @JsonAlias("CommitteeTypeID")
    Integer committeeTypeId;

    @JsonAlias("CommitteeTypeDesc")
    String committeeTypeDescription;

    @JsonAlias("AdditionalTypeID")
    Integer additionalTypeId;

    @JsonAlias("AdditionalTypeDesc")
    String additionalTypeDescription;

    @OneToMany(mappedBy = "committeePersonWasIn")
    List<PersonPosition> peopleInCommitteePositions;
}
