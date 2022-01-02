package com.neoflex.coffeetime.service;

import com.neoflex.coffeetime.dao.AddressDAO;
import com.neoflex.coffeetime.model.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


/**
 * Created by Alexey Podlubnyy on 01.01.2022
 */
@SpringBootTest
class SenderMailServiceTest {

    @Mock
    private AddressDAO addressDAO;

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private SenderMailService senderMailService;

    private MimeMessage mimeMessage;

    private List<Address> addressList;

    @BeforeEach
    void setUp() {
        this.addressList = new ArrayList<>();
        this.addressList.add(new Address(0, "Alex", "Podlubnyy", "alexey.podlubnyy@yandex.ru"));
        this.addressList.add(new Address(1, "Alex", "Cypriiz", "alexcypriizy@yandex.ru"));
        this.addressList.add(new Address(2, "Anton", "Ivanov", "anton.ivanov@yandex.ru"));
    }

    @Test
    void send() throws Exception {
        mimeMessage = new MimeMessage((Session)null);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
        String recipient = "anton.ivanov@yandex.ru";
        when(addressDAO.getAll()).thenReturn(addressList);
        senderMailService.send();
        assertEquals(recipient, mimeMessage.getRecipients(MimeMessage.RecipientType.TO)[0].toString());
    }
}