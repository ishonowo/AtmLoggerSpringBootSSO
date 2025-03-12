package com.infinity.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    
    private final JavaMailSender javaMailSender;
    
    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    
    public void sendEmail(String from, String to, String cc, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        if (to != null && !to.isEmpty()) {
            message.setCc(to.split("[,;]"));
        }
        
        // Handle multiple CC addresses if present
        if (cc != null && !cc.isEmpty()) {
            message.setCc(cc.split("[,;]"));
        }
        
        message.setSubject(subject);
        message.setText(body);
        
        javaMailSender.send(message);
    }
}