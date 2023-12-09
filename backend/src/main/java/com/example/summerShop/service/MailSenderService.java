package com.example.summerShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    @Value("${spring.mail.username}")
    private String username;

    private final JavaMailSender javaMailSender;

    @Autowired
    public MailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void send(String to, String subject, String message) {
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(to);
        mail.setFrom(username);
        mail.setSubject(subject);
        mail.setText(message);

        javaMailSender.send(mail);
    }
}
