package com.ezrah.datafetcher.jobs;

import com.ezrah.datafetcher.objects.knessetOdataApi.KNSBill;
import com.ezrah.datafetcher.objects.knessetOdataApi.OdataFeedObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Component
public class BillUpdatingJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        WebClient webClient = WebClient.builder().build();
        String nextUrl = null;
        do {
            OdataFeedObject responseFeed = webClient.get()
                    .uri("https://knesset.gov.il/Odata/ParliamentInfo.svc/KNS_Bill")
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(OdataFeedObject.class).block();
            if (Objects.nonNull(responseFeed)) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.convertValue(responseFeed.getValue(), new TypeReference<List<KNSBill>>() {
                });
                nextUrl = responseFeed.getNextLink();
            }
        }
        while (StringUtils.hasText(nextUrl));

    }

    @Bean
    public JobDetailFactoryBean jobDetail() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(BillUpdatingJob.class);
        jobDetailFactory.setDescription("Fetch Bills From API...");
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }


    @Bean
    public SimpleTriggerFactoryBean trigger(JobDetail job) {
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(job);
        trigger.setRepeatInterval(ChronoUnit.SECONDS.getDuration().toMillis() * 5);
        trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        return trigger;
    }
}
