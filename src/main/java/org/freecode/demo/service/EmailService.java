package org.freecode.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage emailMsg = new SimpleMailMessage();
        emailMsg.setTo(to);
        emailMsg.setSubject(subject);
        emailMsg.setText(message);

        mailSender.send(emailMsg);
    }
}
