package com.ezrah.datafetcher.objects.knessetOdataApi;

import com.ezrah.datafetcher.definitions.Definitions;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;

@Data
public class OdataFeedObjectBatch<T> implements Serializable, ObjectBatch<T> {
    private List<T> value;
    @JsonProperty("odata.nextLink")
    private String nextLink;

    @Override
    public String getNextBatchUri() {
        if (StringUtils.hasText(nextLink)) {
            return Definitions.KNESSET_ODATA_API_URL + nextLink;
        } else {
            return null;
        }
    }
}
