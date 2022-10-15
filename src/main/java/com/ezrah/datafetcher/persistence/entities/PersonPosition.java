package com.ezrah.datafetcher.persistence.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "person_to_position")
@Getter
@Setter
@ToString
public class PersonPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @JsonAlias("PersonToPositionID")
    Integer knsId;

    @JsonAlias("PersonID")
    Integer knsPersonId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    Person person;

    @JsonAlias("PositionID")
    Integer knsPositionId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    Position position;

    @JsonAlias("KnessetNum")
    Integer knessetNum;

    @JsonAlias("StartDate")
    LocalDateTime startDate;

    @JsonAlias("FinishDate")
    LocalDateTime finishDate;

    @JsonAlias("GovMinistryID")
    Integer knsGovMinistryId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    Ministry ministryPersonWasIn;

    @JsonAlias("DutyDesc")
    String dutyDescription;

    @JsonAlias("FactionID")
    Integer knsFactionId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    FactionPerKnesset factionPersonWasIn;

    Integer knsCommitteeId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    Committee committeePersonWasIn;
}
