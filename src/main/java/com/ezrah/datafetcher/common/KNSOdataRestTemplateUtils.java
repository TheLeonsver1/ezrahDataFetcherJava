package com.ezrah.datafetcher.common;

import com.ezrah.datafetcher.objects.knessetOdataApi.OdataFeedObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class KNSOdataRestTemplateUtils {
    public OdataFeedObject getOdataFeed(String firstLink, String nextBatchUri) {
        RestTemplate restTemplate = new RestTemplate();
        if (Objects.isNull(nextBatchUri)) {
            nextBatchUri = firstLink;
        }
        RequestEntity<Void> requestEntity = RequestEntity
                .get(nextBatchUri)
                .accept(MediaType.APPLICATION_JSON)
                .build();
        ResponseEntity<OdataFeedObject> response = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<>() {
        });
        if (response.getStatusCodeValue() == 200) {
            return response.getBody();
        } else {
            return new OdataFeedObject();
        }
    }
}
