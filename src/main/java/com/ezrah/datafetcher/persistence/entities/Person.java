package com.ezrah.datafetcher.persistence.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * A person who is related to the knesset, maps to a KNS_Person in knesset api
 */
@Entity
@Table(name = "persons")
@Getter
@Setter
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @JsonAlias("PersonID")
    @Column(unique = true)
    Integer knsId;

    @JsonAlias("FirstName")
    String firstName;

    @JsonAlias("LastName")
    String lastName;

    @JsonAlias("Email")
    String email;

    @OneToMany(mappedBy = "person")
    List<PersonPosition> positionHistory;

    @OneToMany(mappedBy = "person")
    List<BillInitiator> billsInitiated;
}
