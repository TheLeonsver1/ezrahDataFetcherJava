package com.ezrah.datafetcher.jobs;

import com.ezrah.datafetcher.common.KnessetOdataApiForEachBatchTemplate;
import com.ezrah.datafetcher.definitions.Definitions;
import com.ezrah.datafetcher.objects.knessetOdataApi.ObjectBatch;
import com.ezrah.datafetcher.objects.persistence.entities.Bill;
import com.ezrah.datafetcher.services.knessetApi.APIBillService;
import com.ezrah.datafetcher.services.persistance.BillService;
import com.ezrah.datafetcher.services.persistance.ItemTypeService;
import com.ezrah.datafetcher.services.persistance.StatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class BillUpdatingJob implements Job {

    private final BillService billService;

    private final ItemTypeService itemTypeService;

    private final StatusService statusService;

    private final APIBillService apiBillService;

//    private final JobDataService jobDataService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            log.info("Started BillUpdatingJob");
            new KnessetOdataApiForEachBatchTemplate<Bill>() {
                @Override
                public void forEachBatch(int batchIndex, ObjectBatch<Bill> billBatch) {
                    log.info(String.format("BillUpdatingJob starting work on batch %d", batchIndex));
                    billBatch.getValue().stream()
                            .filter(updatedBill -> {
                                var persistedBill = billService.findByKnsBillId(updatedBill.getKnsBillId());
                                return persistedBill.isEmpty() || persistedBill.get().getKnsLastUpdatedDate().compareTo(updatedBill.getKnsLastUpdatedDate()) < 0;
                            })
                            .forEach(billService::upsertBill);
                }
            }.start(log, new ParameterizedTypeReference<>() {
            }, Definitions.KNESSET_ODATA_API_URL + "KNS_Bill");

            log.info("Finished BillUpdatingJob");
        } catch (Exception e) {
            log.error("BillUpdatingJob threw error", e);
        }

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
        trigger.setRepeatInterval(ChronoUnit.MINUTES.getDuration().toMillis() * 60);
        trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        return trigger;
    }
}
