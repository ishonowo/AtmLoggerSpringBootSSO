package com.infinity.app.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
import com.infinity.app.model.AtmFault;
import com.infinity.app.model.EmailIssue;
import com.infinity.app.model.Message;
import com.infinity.app.repo.AtmFaultRepo;
import com.infinity.app.repo.EmailIssueRepo;
import com.infinity.app.repo.MessageRepo;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class EmailIssueService {

    private final EmailIssueRepo emailIssueRepo;
    private final MessageRepo messageRepo;
    private final AtmFaultRepo atmFaultRepo;
    private final JavaMailSenderImpl mailSender;
    private final Configuration freemarkerConfig;

    public EmailIssueService(EmailIssueRepo emailIssueRepo, MessageRepo messageRepo,
    		AtmFaultRepo atmFaultRepo, Environment environment, Configuration freemarkerConfig) {
        this.emailIssueRepo = emailIssueRepo;
        this.messageRepo = messageRepo;
        this.atmFaultRepo = atmFaultRepo;
        this.freemarkerConfig = freemarkerConfig;

        // Configure mail sender from environment properties
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost(environment.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));
        mailSender.setUsername(environment.getProperty("spring.mail.username"));
        mailSender.setPassword(environment.getProperty("spring.mail.password"));
    }

	public EmailIssue convertToEmailIssue(EmailIssueMessageDto dto) {

		// IMPORTANT: never pass dto.getAtmFaults() straight through to
		// Message's @ManyToMany(cascade = MERGE) association. Those objects
		// come from the client and may carry an edited description (the
		// email form lets users tweak wording before sending). If Hibernate
		// merges them as-is, it overwrites the SHARED master rows in
		// atm_faults for every user, permanently, since it's the same
		// physical row every submission points to.
		//
		// Instead: re-resolve the canonical, unmodified AtmFault entities by
		// id from the repository before persisting. otherFaultDesc travels
		// as its own top-level field on the DTO (AtmFault itself has no
		// such column).

		List<Long> faultIds = dto.getAtmFaults() == null ? List.of() : dto.getAtmFaults().stream()
				.map(AtmFault::getId)
				.toList();

		List<AtmFault> canonicalFaults = atmFaultRepo.findAllById(faultIds);

		// Create Message object using the canonical (untouched) faults.
        Message message = new Message(
                dto.getPhysicalAddress(),
                dto.getBranchName(),
                dto.getVendorName(),
                canonicalFaults,
                dto.getOtherFaultDesc(),
                dto.getBranchLogger(),
                dto.getLoggerPhone(),
                dto.getDateLogged()
            );
        messageRepo.save(message);

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

            templateModel.put("atmFaults", emailIssue.getMessage().getAtmFaults());

            // Only added to the model when actually present, so the template
            // should guard access with <#if otherFaultDesc??> rather than
            // assuming the key always exists.
            String otherFaultDesc = emailIssue.getMessage().getOtherFaultDesc();
            if (otherFaultDesc != null && !otherFaultDesc.isBlank()) {
                templateModel.put("otherFaultDesc", otherFaultDesc);
            }

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