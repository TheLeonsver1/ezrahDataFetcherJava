package com.ezrah.datafetcher.persistence.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "positions")
@Getter
@Setter
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @JsonAlias("PositionID")
    @Column(unique = true)
    Integer knsId;

    @JsonAlias("Description")
    String description;

    @JsonAlias("LastUpdatedDate")
    LocalDateTime knsLastUpdatedDate;

    @OneToMany(mappedBy = "position")
    List<PersonPosition> peopleInPosition;

}
