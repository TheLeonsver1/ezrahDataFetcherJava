package com.ezrah.datafetcher.enums;

import lombok.Getter;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;

@Getter
public enum KNSApiDataTypes {
    ITEM_TYPE("KNS_ItemType"),
    BILL("KNS_Bill"),
    STATUS("KNS_Status"),
    PERSON("KNS_Person"),
    POSITION("KNS_Position"),
    FACTION_PER_KNESSET("KNS_Faction"),
    MINISTRY("KNS_GovMinistry"),
    PARENT_COMMITTEE("KNS_Committee?$filter=ParentCommitteeID eq null"),
    CHILD_COMMITTEE("KNS_Committee?$filter=ParentCommitteeID ne null"),
    PERSON_TO_POSITION("KNS_PersonToPosition"),
    BILL_INITIATOR("KNS_BillInitiator"),
    BILL_HISTORY_INITIATOR("KNS_BillHistoryInitiator");

    private final String knsAPIUrl;

    private String encodedKnsAPIUrl;

    KNSApiDataTypes(String knsAPIUrl) {
        this.knsAPIUrl = knsAPIUrl;
        var indexOfQuestionMark = knsAPIUrl.indexOf("?");
        if (indexOfQuestionMark != -1) {
            this.encodedKnsAPIUrl = knsAPIUrl.substring(0, indexOfQuestionMark + 1) + UriUtils.encode(knsAPIUrl.substring(indexOfQuestionMark + 1), StandardCharsets.UTF_8);
        } else {
            this.encodedKnsAPIUrl = knsAPIUrl;
        }
    }
}
