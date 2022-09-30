package com.ezrah.datafetcher.jobs;

import com.ezrah.datafetcher.definitions.Definitions;
import com.ezrah.datafetcher.objects.knessetOdataApi.OdataFeedObject;
import com.ezrah.datafetcher.objects.persistence.documents.Bill;
import com.ezrah.datafetcher.services.persistance.BillService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Optional;

@Component
public class BillUpdatingJob implements Job {

    @Autowired
    private BillService billService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        WebClient webClient = WebClient.builder().build();
        String nextUrl = "KNS_Bill";
        do {
            OdataFeedObject responseFeed = webClient.get()
                    .uri(Definitions.KNESSET_ODATA_API_URL + nextUrl)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(OdataFeedObject.class).block();
            if (Objects.nonNull(responseFeed)) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                List<Bill> updatedBills = objectMapper.convertValue(responseFeed.getValue(), new TypeReference<>() {
                });

                List<Integer> knsBillIds = updatedBills.stream()
                        .map(Bill::getKnsBillID).toList();

                List<Bill> persistedBills = billService.getBillsByKnsBillIds(knsBillIds);

                for (Bill updatedBill : updatedBills) {
                    Optional<Bill> persistedBill = persistedBills.stream().filter(bill -> bill.getKnsBillID().equals(updatedBill.getKnsBillID())).findFirst();
                    if (persistedBill.isPresent() && persistedBill.get().getKnsLastUpdatedDate().compareTo(updatedBill.getKnsLastUpdatedDate()) < 0) {
                        persistedBill.get().setName(updatedBill.getName());
                        persistedBill.get().setKnessetNum(updatedBill.getKnessetNum());
                        persistedBill.get().setPublicationDate(updatedBill.getPublicationDate());
                        persistedBill.get().setSummaryLaw(updatedBill.getSummaryLaw());
                        persistedBill.get().setKnsLastUpdatedDate(updatedBill.getKnsLastUpdatedDate());
                        billService.save(persistedBill.get());
                    } else if (persistedBill.isEmpty()) {
                        billService.save(updatedBill);
                    }
                }

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
