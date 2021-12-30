package com.neoflex.coffeetime.service;

import com.neoflex.coffeetime.dao.AddressDAO;
import com.neoflex.coffeetime.model.Address;
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
    AddressDAO addressDAO;

    final
    JavaMailSender javaMailSender;

    public SenderMailService(AddressDAO addressDAO, JavaMailSender javaMailSender) {
        this.addressDAO = addressDAO;
        this.javaMailSender = javaMailSender;
    }

    public void send() throws Exception {
        List<Address> addresses = addressDAO.getAll();
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper contentMessage = new MimeMessageHelper(message, true);
        for (Address address : addresses) {
            contentMessage.setTo(address.getEmail());
            contentMessage.setText("<html><body><h1>" + address.getFirst_name() +", пора пить кофе!</h1><body></html>", true);
            contentMessage.setSubject("Кофепитие");
            javaMailSender.send(message);
        }
    }
}
