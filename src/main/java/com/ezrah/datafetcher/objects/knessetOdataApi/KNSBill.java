package com.ezrah.datafetcher.objects.knessetOdataApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KNSBill {
    @JsonProperty("BillID")
    Integer billID;

    @JsonProperty("KnessetNum")
    Integer knessetNum;

    @JsonProperty("Name")
    String name;

    @JsonProperty("SubTypeID")
    Integer subTypeID;

    @JsonProperty("SubTypeDesc")
    String subTypeDesc;

    @JsonProperty("StatusID")
    Integer statusID;

    @JsonProperty("SummaryLaw")
    String summaryLaw;

    @JsonProperty("PublicationDate")
    Timestamp publicationDate;

    @JsonProperty("LastUpdatedDate")
    Timestamp lastUpdatedDate;
}
