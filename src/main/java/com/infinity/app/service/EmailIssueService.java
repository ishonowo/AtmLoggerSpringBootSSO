package com.infinity.app.service;

import com.infinity.app.model.EmailIssue;
import com.infinity.app.repo.EmailIssueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmailIssueService {
    
    private final EmailIssueRepo emailIssueRepository;
    private final EmailSenderService emailSenderService;
    
    @Autowired
    public EmailIssueService(EmailIssueRepo emailIssueRepository, EmailSenderService emailSenderService) {
        this.emailIssueRepository = emailIssueRepository;
        this.emailSenderService = emailSenderService;
    }
    
    @Transactional
    public EmailIssue sendEmail(EmailIssue emailIssue) {
        // First save the email issue to the database
        EmailIssue savedEmailIssue = emailIssueRepository.save(emailIssue);
        
        // Construct email content from the email issue object
        String emailContent = constructEmailContent(emailIssue);
        
        // Send the actual email
        emailSenderService.sendEmail(
            emailIssue.getFromEmail(),
            emailIssue.getToEmail(),
            emailIssue.getCc(),
            emailIssue.getSubject(),
            emailContent
        );
        
        return savedEmailIssue;
    }
    
    private String constructEmailContent(EmailIssue emailIssue) {
        StringBuilder contentBuilder = new StringBuilder();
        
        // Add intro
        contentBuilder.append(emailIssue.getmIntro()).append("\n\n");
        
        // Add message details in a formatted way
        contentBuilder.append("ATM LOCATION: ").append(emailIssue.getMessage().getAtmLocation()).append("\n");
        contentBuilder.append("BRANCH IN CHARGE: ").append(emailIssue.getMessage().getBranchName()).append("\n");
        contentBuilder.append("VENDOR: ").append(emailIssue.getMessage().getVendorName()).append("\n");
        contentBuilder.append("ISSUE(S): ").append(emailIssue.getMessage().getIssueDesc()).append("\n");
        contentBuilder.append("BRANCH CONTACT: ").append(emailIssue.getMessage().getBranchLogger()).append("\n");
        contentBuilder.append("CONTACT PHONE NUMBER: ").append(emailIssue.getMessage().getLoggerPhone()).append("\n");
        contentBuilder.append("DATE LOGGED: ").append(emailIssue.getMessage().getDateLogged()).append("\n\n");
        
        // Add ending
        contentBuilder.append(emailIssue.getmEnd());
        
        return contentBuilder.toString();
    }
}
