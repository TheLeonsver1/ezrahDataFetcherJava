package com.ezrah.datafetcher.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * No equivalent in api, a parent object to all faction instances
 */
@Entity
@Table(name = "factions")
@Getter
@Setter
public class Faction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "faction")
    List<FactionPerKnesset> factionPerKnesset;

    @OneToMany(mappedBy = "duplicateOf")
    List<Faction> duplicates;

    @ManyToOne(fetch = FetchType.LAZY)
    Faction duplicateOf;

    @JoinTable(
            name = "faction_mergers",
            joinColumns = {@JoinColumn(name = "resulting_faction_id")},
            inverseJoinColumns = {@JoinColumn(name = "merged_faction_id")}
    )
    @ManyToMany
    List<Faction> mergerOf;

    @ManyToMany(mappedBy = "mergerOf")
    List<Faction> wasPartOfMerges;
}
