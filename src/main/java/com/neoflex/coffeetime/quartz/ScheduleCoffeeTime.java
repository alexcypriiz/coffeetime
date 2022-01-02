package com.neoflex.coffeetime.quartz;

import com.neoflex.coffeetime.service.SenderMailService;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;


/**
 * Created by Alexey Podlubnyy on 09.11.2021
 */

@Component
public class ScheduleCoffeeTime extends QuartzJobBean {
    final
    SenderMailService senderMailService;

    public ScheduleCoffeeTime(SenderMailService senderMailService) {
        this.senderMailService = senderMailService;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        try {
            senderMailService.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
