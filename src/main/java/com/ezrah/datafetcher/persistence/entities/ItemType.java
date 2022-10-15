package com.ezrah.datafetcher.persistence.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A knesset item type, maps to a KNS_Type in knesset api
 */
@Entity
@Table(name = "item_types")
@Getter
@Setter
@NoArgsConstructor
public class ItemType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @JsonAlias("ItemTypeID")
    @Column(unique = true)
    Integer knsId;

    @JsonAlias("Desc")
    String description;

    @JsonAlias("LastUpdatedDate")
    String knsLastUpdatedDate;

}
