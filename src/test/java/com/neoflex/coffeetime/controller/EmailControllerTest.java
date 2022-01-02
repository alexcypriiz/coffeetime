package com.neoflex.coffeetime.controller;

import com.neoflex.coffeetime.quartz.ScheduleCoffeeTime;
import com.neoflex.coffeetime.service.SenderMailService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

/**
 * Created by Alexey Podlubnyy on 30.12.2021
 */
@WebMvcTest(EmailController.class)
class EmailControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ScheduleCoffeeTime scheduleCoffeeTime;

    @MockBean
    SenderMailService senderMailService;

    @Test
    void sendSimpleEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/sendEmail"))
                .andExpect(redirectedUrl("/address"))
                .andExpect(MockMvcResultMatchers.status().isFound());
    }
}