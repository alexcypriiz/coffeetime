package com.neoflex.coffeetime.service;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;

/**
 * Created by Alexey Podlubnyy on 20.10.2021
 */
    public interface EmailService {

        void sendSimpleEmail(final String toAddress, final String subject, final String message);
        void sendEmailWithAttachment(final String toAddress, final String subject, final String message, final String attachment) throws MessagingException, FileNotFoundException;
}
