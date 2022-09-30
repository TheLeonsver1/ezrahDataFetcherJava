package com.ezrah.datafetcher.objects.knessetOdataApi;

import java.util.List;

public interface ObjectBatchWrapper {
    List<Object> getValue();

    String getNextBatchUri();
}
