package com.ezrah.datafetcher.persistence.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@ToString
public abstract class BaseBillInitiator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @JsonAlias({"BillInitiatorID", "BillHistoryInitiatorID"})
    Integer knsId;

    @JsonAlias("BillID")
    Integer knsBillId;

    @JsonAlias("PersonID")
    Integer knsPersonId;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    Bill bill;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    Person person;

    @JsonAlias("IsInitiator")
    Boolean isInitiator;

    @JsonAlias("Ordinal")
    Integer ordinal;

    @JsonAlias("LastUpdatedDate")
    LocalDateTime knsLastUpdatedDate;
}
