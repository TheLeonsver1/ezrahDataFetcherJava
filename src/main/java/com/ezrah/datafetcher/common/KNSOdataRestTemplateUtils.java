package com.ezrah.datafetcher.common;

import com.ezrah.datafetcher.objects.knessetOdataApi.ObjectBatch;
import com.ezrah.datafetcher.objects.knessetOdataApi.OdataFeedObjectBatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

@Component
@Slf4j
public class KNSOdataRestTemplateUtils {
    public <T> Optional<ObjectBatch<T>> getOdataFeed(ParameterizedTypeReference<OdataFeedObjectBatch<T>> parameterizedTypeReference, String firstLink, String nextBatchUri) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            if (Objects.isNull(nextBatchUri)) {
                nextBatchUri = firstLink;
            }
            RequestEntity<Void> requestEntity = RequestEntity
                    .get(nextBatchUri)
                    .accept(MediaType.APPLICATION_JSON)
                    .build();

            ResponseEntity<OdataFeedObjectBatch<T>> response = restTemplate.exchange(requestEntity, parameterizedTypeReference);
            if (response.getStatusCodeValue() == 200) {
                return Optional.ofNullable(response.getBody());
            } else {
                log.error("Failed getting batch from api, response status code", response.getStatusCodeValue());
                return Optional.empty();
            }
        } catch (Exception e) {
            log.error("Rest template threw error", e);
            return Optional.empty();
        }
    }
}
