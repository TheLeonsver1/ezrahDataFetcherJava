package com.ezrah.datafetcher.jobs;

import com.ezrah.datafetcher.objects.persistence.documents.Bill;
import com.ezrah.datafetcher.objects.persistence.documents.jobData.JobData;
import com.ezrah.datafetcher.services.knessetApi.APIBillService;
import com.ezrah.datafetcher.services.persistance.BillService;
import com.ezrah.datafetcher.services.persistance.JobDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class BillUpdatingJob implements Job {

    private final BillService billService;

    private final APIBillService apiBillService;

    private final JobDataService jobDataService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            log.info("Started BillUpdatingJob");
            JobData jobData = jobDataService.getJobData();
            String nextUrl = null;
//        if(jobData != null) {
//            BillUpdatingJobData billUpdatingJobData = jobData.getBillUpdatingJobData();
//            if(billUpdatingJobData != null){
//                nextUrl = String.format("KNS_Bill?$filter=LastUpdatedDate>datetime'{}'", LocalDateTime.toStr)
//            }
//        }
            int iterationCounter = 0;
            do {
                // Sleep for a bit to not get Security Block(503)
                if (nextUrl != null && iterationCounter % 87 == 0) {
                    log.info("BillUpdatingJob - sleeping a bit to not get blocked");
                    Thread.sleep(ChronoUnit.MINUTES.getDuration().toMillis());
                }
                log.info("BillUpdatingJob - getting batch with next url: {}", nextUrl);
                var billBatch = apiBillService.getBillBatch(nextUrl);

                if (billBatch.isPresent()) {
                    var bills = billBatch.get().getValue();
                    List<Integer> knsBillIds = bills.stream()
                            .map(Bill::getKnsBillID).toList();

                    List<Bill> persistedBills = billService.getBillsByKnsBillIds(knsBillIds);

                    for (Bill updatedBill : bills) {
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

                    nextUrl = billBatch.get().getNextBatchUri();
                }
                iterationCounter++;
            }
            while (StringUtils.hasText(nextUrl));
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
