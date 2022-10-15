package com.ezrah.datafetcher.objects;

import java.util.List;

public interface ObjectBatch<T> {
    List<T> getValue();

    String getNextBatchUri();
}
