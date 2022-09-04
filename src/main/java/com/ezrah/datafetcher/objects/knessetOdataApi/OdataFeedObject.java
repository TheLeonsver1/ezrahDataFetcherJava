package com.ezrah.datafetcher.objects.knessetOdataApi;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OdataFeedObject implements Serializable {
    private List<Object> value;
    private String nextLink;
}
