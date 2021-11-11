package com.neoflex.coffeetime.quartz;


import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by Alexey Podlubnyy on 09.11.2021
 */
@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail scheduleJob() {
        return JobBuilder.newJob(ScheduleCoffeeTime.class).storeDurably()
                .withIdentity("schedule_coffee_time").withDescription("Schedule coffee time").build();
    }
    @Bean
    public Trigger scheduleTrigger() {
        return newTrigger().withIdentity("trigger").forJob(scheduleJob()).
                withSchedule(CronScheduleBuilder.cronSchedule("0 0 19 ? * MON-FRI")
                        .inTimeZone(TimeZone.getTimeZone("Europe/Saratov"))).build();
    }
}
