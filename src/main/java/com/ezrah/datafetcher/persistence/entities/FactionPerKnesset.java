package com.ezrah.datafetcher.persistence.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * maps to KNS_Faction in api
 */
@Entity
@Table(name = "faction_per_knesset")
@Getter
@Setter
public class FactionPerKnesset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonAlias("FactionID")
    @Column(unique = true)
    private Integer knsId;

    @JsonAlias("StartDate")
    private LocalDateTime startDate;

    @JsonAlias("FinishDate")
    private LocalDateTime finishDate;

    @JsonAlias("Name")
    private String name;

    @JsonAlias("KnessetNum")
    private Integer knessetNum;

    @ManyToOne(fetch = FetchType.LAZY)
    private Faction faction;

    @OneToMany(mappedBy = "factionPersonWasIn")
    List<PersonPosition> peopleInPositions;
}
