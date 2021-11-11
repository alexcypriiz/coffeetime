package com.neoflex.coffeetime.quartz;

import com.neoflex.coffeetime.dao.AddressDAO;
import com.neoflex.coffeetime.service.SenderMailService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.List;


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
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            senderMailService.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}