package com.ezrah.datafetcher.jobs;

import com.ezrah.datafetcher.services.knessetApi.UpdateDataService;
import com.ezrah.datafetcher.services.persistance.ItemTypeService;
import com.ezrah.datafetcher.services.persistance.StatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class BillUpdatingJob implements Job {

    private final UpdateDataService updateDataService;

    private final ItemTypeService itemTypeService;

    private final StatusService statusService;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            log.info("Started BillUpdatingJob");
            if (itemTypeService.countItemTypes() != 0 && statusService.countStatuses() != 0) {
//                updateDataService.updateBills(null);
            } else {
                log.error("No statuses or itemTypes, not updating bills");
            }
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
