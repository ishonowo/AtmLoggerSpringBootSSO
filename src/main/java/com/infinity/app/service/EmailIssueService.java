package com.infinity.app.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.infinity.app.dto.EmailIssueMessageDto;
import com.infinity.app.model.EmailIssue;
import com.infinity.app.model.Message;
import com.infinity.app.repo.EmailIssueRepo;
import com.infinity.app.repo.MessageRepo;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class EmailIssueService {

    private final EmailIssueRepo emailIssueRepo;
    private final MessageRepo messageRepo;
    private final JavaMailSenderImpl mailSender;
    private final Configuration freemarkerConfig;

    public EmailIssueService(EmailIssueRepo emailIssueRepo, MessageRepo messageRepo,
    		Environment environment, Configuration freemarkerConfig) {
        this.emailIssueRepo = emailIssueRepo;
        this.messageRepo = messageRepo;
        this.freemarkerConfig = freemarkerConfig;

        // Configure mail sender from environment properties
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost(environment.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));
        mailSender.setUsername(environment.getProperty("spring.mail.username"));
        mailSender.setPassword(environment.getProperty("spring.mail.password"));
    }
    
	public EmailIssue convertToEmailIssue(EmailIssueMessageDto dto) {
        // Parse date from string format
        //LocalDate dateLogged = LocalDate.parse(dto.getDateLogged(), DateTimeFormatter.ISO_DATE);
        
        // Create Message object
        Message message = new Message(
            dto.getPhysicalAddress(),
            dto.getBranchName(),
            dto.getVendorName(),
            dto.getIssueDesc(),
            dto.getBranchLogger(),
            dto.getLoggerPhone(),
            dto.getDateLogged()
        );
        Message tranMessage =messageRepo.save(message);
        
        // Create EmailIssue object
        EmailIssue emailIssue = new EmailIssue(
        	    dto.getFromEmail(),
        	    dto.getToEmail(),
        	    dto.getCc(),
        	    dto.getSubject(),
        	    dto.getmIntro(),
        	    message,
        	    dto.getmEnd()
        	);
        
        EmailIssue tranEmailIssue = emailIssueRepo.save(emailIssue);
        
        return tranEmailIssue;
    }


    @Transactional
    public EmailIssue sendEmail(EmailIssue emailIssue) {
        
        try {
            // Create a MIME message
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, 
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);

            // Set up the message parameters
            helper.setFrom(emailIssue.getFromEmail());
            helper.setTo(emailIssue.getToEmail().split(";"));
            
            if (emailIssue.getCc() != null && !emailIssue.getCc().isEmpty()) {
                helper.setCc(emailIssue.getCc().split(";"));
            }
            
            helper.setSubject(emailIssue.getSubject());

            // Create model for the template
            Map<String, Object> templateModel = new HashMap<>();
            templateModel.put("emailIssue", emailIssue);
            templateModel.put("intro", emailIssue.getmIntro());
            templateModel.put("physicalAddress", emailIssue.getMessage().getPhysicalAddress());
            templateModel.put("branchName", emailIssue.getMessage().getBranchName());
            templateModel.put("vendorName", emailIssue.getMessage().getVendorName());
            templateModel.put("issueDesc", emailIssue.getMessage().getIssueDesc());
            templateModel.put("branchLogger", emailIssue.getMessage().getBranchLogger());
            templateModel.put("loggerPhone", emailIssue.getMessage().getLoggerPhone());
            templateModel.put("dateLogged", emailIssue.getMessage().getDateLogged());
            templateModel.put("conclusion", emailIssue.getmEnd());

            // Process the FreeMarker template
            String htmlContent = "";
            try {
                Template template = freemarkerConfig.getTemplate("email-template.ftl");
                htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, templateModel);
            } catch (IOException | TemplateException e) {
                Logger.getLogger(EmailIssueService.class.getName()).log(Level.SEVERE, "Error processing template", e);
                throw new RuntimeException("Failed to process email template", e);
            }

            // Set the HTML content
            helper.setText(htmlContent, true);

            // Send the email
            mailSender.send(msg);
            
            return emailIssue;
            
        } catch (MessagingException ex) {
            Logger.getLogger(EmailIssueService.class.getName()).log(Level.SEVERE, "Error sending email", ex);
            throw new RuntimeException("Failed to send email", ex);
        }
    }
}