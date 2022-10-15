package com.ezrah.datafetcher.persistence.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This exists because I don't know if KNS_BillInitiator and KNS_BillHistoryInitiator share ids
 * but this entity is for bill initiators that are no longer bill initiators for whatever reason
 */
@Entity
@Table(name = "bill_history_initiators")
@ToString(callSuper = true)
public class BillHistoryInitiator extends BaseBillInitiator {

    @JsonAlias("ReasonID")
    Integer knsReasonId;

    @JsonAlias("ReasonDesc")
    String reasonStoppedDescription;
}
