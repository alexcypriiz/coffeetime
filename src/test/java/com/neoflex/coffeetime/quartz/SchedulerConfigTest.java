package com.neoflex.coffeetime.quartz;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.quartz.*;
import org.quartz.impl.calendar.HolidayCalendar;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Created by Alexey Podlubnyy on 02.01.2022
 */
@SpringBootTest
class SchedulerConfigTest {

    @Mock
    private JobDetail jobDetail;

    @Mock
    private JobBuilder jobBuilder;

    @Mock
    private TriggerBuilder triggerBuilder;

    @Mock
    private Trigger trigger;

    @InjectMocks
    private SchedulerConfig schedulerConfig;


    @Test
    void scheduleJob() {
        when(jobBuilder.build()).thenReturn(jobDetail);
        String scheduleDescription = "Schedule coffee time";
        assertEquals(scheduleDescription, schedulerConfig.scheduleJob().getDescription());

    }

    @Test
    void scheduleTrigger() {
        when(triggerBuilder.build()).thenReturn(trigger);
        String triggerCalendar = "holidays";
        assertEquals(triggerCalendar, schedulerConfig.scheduleTrigger().getCalendarName());

    }

    @Test
    void holidayCalendar() throws ParseException {
        Date date = new SimpleDateFormat("yyyy/MM/dd").parse("1999/1/1");
        assertEquals(date, schedulerConfig.holidayCalendar().getExcludedDates().first());
    }

    @Test
    void buffReaderHolidayCalendar() {
        HolidayCalendar holidayCalendar = new HolidayCalendar();
        assertEquals(3278, schedulerConfig.buffReaderHolidayCalendar(holidayCalendar).getExcludedDates().size());
    }
}