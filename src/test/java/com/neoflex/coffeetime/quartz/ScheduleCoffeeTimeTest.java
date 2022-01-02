package com.neoflex.coffeetime.quartz;

import com.neoflex.coffeetime.service.SenderMailService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.quartz.JobExecutionContext;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

/**
 * Created by Alexey Podlubnyy on 01.01.2022
 */
@SpringBootTest
class ScheduleCoffeeTimeTest {

    @Mock
    private SenderMailService senderMailService;

    @Mock
    private JobExecutionContext jobExecutionContext;

    @InjectMocks
    private ScheduleCoffeeTime scheduleCoffeeTime;

    @Test
    void executeInternal() throws Exception {
        scheduleCoffeeTime.executeInternal(jobExecutionContext);
        verify(senderMailService).send();
    }
}