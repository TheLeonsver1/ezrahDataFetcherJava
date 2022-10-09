package com.ezrah.datafetcher.objects.persistence.entities;

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

    @Column(unique = true)
    Integer knsStatusId;

    String description;
}
