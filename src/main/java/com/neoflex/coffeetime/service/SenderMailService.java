package com.neoflex.coffeetime.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * Created by Alexey Podlubnyy on 04.11.2021
 */
@Service
public class SenderMailService {

    final
    JavaMailSender javaMailSender;

    public SenderMailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void send(List<String> emailList) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper contentMessage = new MimeMessageHelper(message, true);
        for (String email : emailList) {
            contentMessage.setTo(email);
            contentMessage.setText("<html><body><h1>Ержан, пора пить чай!</h1><body></html>", true);
            contentMessage.setSubject("Чаепитие");
            javaMailSender.send(message);
        }
    }
}
