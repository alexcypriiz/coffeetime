package com.neoflex.coffeetime.quartz;


import org.quartz.*;
import org.quartz.impl.calendar.CronCalendar;
import org.quartz.impl.calendar.HolidayCalendar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                withSchedule(CronScheduleBuilder.cronSchedule("0 0 19 ? * *")
                        .inTimeZone(TimeZone.getTimeZone("Europe/Saratov")))
                .modifiedByCalendar("holidays")
                .build();
    }

    @Bean("holidays")
    public HolidayCalendar holidayCalendar() {
        HolidayCalendar holidayCalendar = new HolidayCalendar();
        return buffReaderHolidayCalendar(holidayCalendar);
    }


    public HolidayCalendar buffReaderHolidayCalendar(HolidayCalendar holidayCalendar) {
        String year;
        String month;
        String day;
        try (BufferedReader br = new BufferedReader(new FileReader("data-20181017t0930-structure-20181017t0930_5.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Pattern patternYears = Pattern.compile("\\d{4}");
                Pattern patternMonths = Pattern.compile("\\\"(?:\\d[\\,\\*,\\+]*)+\\\"");
                Pattern patternDays = Pattern.compile("\\d{1,2}");
                Matcher mYears = patternYears.matcher(line);
                Matcher mMonths = patternMonths.matcher(line);
                if (mYears.find()) {
                    int countMonths = 0;
                    year = mYears.group(0);
                    while(mMonths.find()) {
                        countMonths += 1;
                        month = mMonths.group();
                        Matcher mDays = patternDays.matcher(month);
                        while (mDays.find()) {
                            day = mDays.group();
                            holidayCalendar.addExcludedDate(new SimpleDateFormat("yyyy/MM/dd").parse(year + "/" + countMonths + "/" + day)); // add date
                            System.out.println(year + "/" + countMonths + "/" + day);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return holidayCalendar;
    }

//          @Bean("holidays")
//    public CronCalendar calendar() throws ParseException {
//        CronCalendar calendar = new CronCalendar("10/20 * * ? * MON-FRI");
//        return calendar;
//    }

//        @Bean
//    public Trigger myJobTrigger() {
//        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
//                .simpleSchedule()
//                .withIntervalInSeconds(24)
//                .repeatForever();
//
//        return TriggerBuilder.newTrigger()
//                .forJob(scheduleJob())
//                .withIdentity("myJobTrigger")
//                .withSchedule(scheduleBuilder)
//                .startAt(DateBuilder.dateOf(21, 50,0))
//                .modifiedByCalendar("holidays")
//                .build();
//    }

}
