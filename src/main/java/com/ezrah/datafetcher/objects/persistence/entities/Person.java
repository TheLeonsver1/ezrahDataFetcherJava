package com.ezrah.datafetcher.objects.persistence.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @Column(unique = true)
    Integer knsPersonId;
}
