package com.neoflex.coffeetime.controller;

import com.neoflex.coffeetime.config.MyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Alexey Podlubnyy on 21.10.2021
 */

@Controller
public class SimpleEmailExampleController {

    public final JavaMailSender emailSender;

    public SimpleEmailExampleController(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @ResponseBody
    @RequestMapping("/sendSimpleEmail")
    public String sendSimpleEmail() {

        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(MyConstants.FRIEND_EMAIL);
        message.setSubject("Test Simple Email");
        message.setText("Hello, Im testing Simple Email");

        // Send Message!
        this.emailSender.send(message);

        return "Email Sent!";
    }

}
