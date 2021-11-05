package com.neoflex.coffeetime.controller;

import com.neoflex.coffeetime.dao.AddressDAO;
import com.neoflex.coffeetime.service.SenderMailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Created by Alexey Podlubnyy on 21.10.2021
 */
@Controller
public class EmailController {
    final
    AddressDAO addressDAO;

    final
    SenderMailService senderMailService;

    public EmailController(SenderMailService senderMailService, AddressDAO addressDAO) {
        this.senderMailService = senderMailService;
        this.addressDAO = addressDAO;
    }

    @GetMapping("/sendEmail")
    public String sendSimpleEmail() {
        List<String> emailList = addressDAO.getAllEmail();
        try {
            senderMailService.send(emailList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/address";
    }
}
