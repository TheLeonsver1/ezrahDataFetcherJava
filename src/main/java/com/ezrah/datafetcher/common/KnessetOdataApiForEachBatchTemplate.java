package com.ezrah.datafetcher.common;

import com.ezrah.datafetcher.objects.knessetOdataApi.ObjectBatch;
import com.ezrah.datafetcher.objects.knessetOdataApi.OdataFeedObjectBatch;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

public interface KnessetOdataApiForEachBatchTemplate<T> {

    default void start(Logger logger, ParameterizedTypeReference<OdataFeedObjectBatch<T>> parameterizedTypeReference, String firstLink) throws InterruptedException {
        Assert.hasText(firstLink, "First link doesn't have text");
        int iterationCounter = 0;
        RestTemplate restTemplate = new RestTemplate();
        String nextBatchUri = firstLink;
        do {
            // Sleep for a bit to not get Security Block(503)
            if (nextBatchUri != null && iterationCounter > 0 && iterationCounter % 87 == 0) {
                Thread.sleep(ChronoUnit.MINUTES.getDuration().toMillis());
            }

            try {
                var billBatch = getOdataFeed(restTemplate, parameterizedTypeReference, nextBatchUri);

                if (billBatch.isPresent()) {
                    forEachBatch(iterationCounter, billBatch.get());
                    nextBatchUri = billBatch.get().getNextBatchUri();
                }
            } catch (Exception e) {
                // TODO: 02/10/2022 handle exceptions
                logger.error("Error encountered", e);
            }
            iterationCounter++;
        }
        while (StringUtils.hasText(nextBatchUri));
    }


    default Optional<ObjectBatch<T>> getOdataFeed(RestTemplate restTemplate, ParameterizedTypeReference<OdataFeedObjectBatch<T>> parameterizedTypeReference, String nextBatchUri) throws RequestFailed, RestClientException {
        RequestEntity<Void> requestEntity = RequestEntity
                .get(nextBatchUri)
                .accept(MediaType.APPLICATION_JSON)
                .build();

        ResponseEntity<OdataFeedObjectBatch<T>> response = restTemplate.exchange(requestEntity, parameterizedTypeReference);
        if (response.getStatusCodeValue() == 200) {
            return Optional.ofNullable(response.getBody());
        } else {
            throw new RequestFailed(response.getStatusCodeValue());
        }
    }

    void forEachBatch(int batchIndex, ObjectBatch<T> objectBatch);

    @AllArgsConstructor
    class RequestFailed extends Exception {
        private int statusCode;

        @Override
        public String getMessage() {
            return String.format("Request didn't return status 200, it returned %d", statusCode);
        }
    }
}
