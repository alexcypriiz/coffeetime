package com.neoflex.coffeetime.controller;

import com.neoflex.coffeetime.dao.AddressDAO;
import com.neoflex.coffeetime.quartz.ScheduleCoffeeTime;
import com.neoflex.coffeetime.service.SenderMailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Alexey Podlubnyy on 21.10.2021
 */
@Controller
public class EmailController {
    final
    ScheduleCoffeeTime scheduleCoffeeTime;

    final
    SenderMailService senderMailService;

    public EmailController(ScheduleCoffeeTime scheduleCoffeeTime, SenderMailService senderMailService) {
        this.scheduleCoffeeTime = scheduleCoffeeTime;
        this.senderMailService = senderMailService;
    }

    @GetMapping("/sendEmail")
    public String sendSimpleEmail() {
        try {
            senderMailService.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/address";
    }
}
