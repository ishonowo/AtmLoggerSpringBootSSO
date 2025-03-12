package com.infinity.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infinity.app.dto.EmailIssueMessageDto;
import com.infinity.app.dto.ErrorResponse;
import com.infinity.app.model.EmailIssue;
import com.infinity.app.model.Message;
import com.infinity.app.service.EmailIssueService;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/email")
public class EmailIssueController {

	private static final Logger logger = LoggerFactory.getLogger(EmailIssueController.class);
		
	private final EmailIssueService emailIssueService;
	
	public EmailIssueController(EmailIssueService emailIssueService) {
		this.emailIssueService= emailIssueService;
	}

	@PostMapping("/sendEmail")
	public ResponseEntity<?> sendEmail(@Valid @RequestBody EmailIssueMessageDto emailIssueMessage){
		
		logger.info("Received emailIssue "+emailIssueMessage);
		try {
			// Convert request DTO to EmailIssue domain object
            EmailIssue emailIssue = convertToEmailIssue(emailIssueMessage);
            
            // Save and send the email
            EmailIssue savedEmailIssue = emailIssueService.sendEmail(emailIssue);
            logger.info("Email submitted successfully. "+ savedEmailIssue); 
            return new ResponseEntity<>(savedEmailIssue, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                new ErrorResponse("Failed to send email: " + e.getMessage()), 
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
		
        
    }
	
	private EmailIssue convertToEmailIssue(EmailIssueMessageDto dto) {
        // Parse date from string format
        LocalDate dateLogged = LocalDate.parse(dto.getDateLogged(), DateTimeFormatter.ISO_DATE);
        
        // Create Message object
        Message message = new Message(
            dto.getAtmLocation(),
            dto.getBranchName(),
            dto.getVendorName(),
            dto.getIssueDesc(),
            dto.getBranchLogger(),
            dto.getLoggerPhone(),
            dateLogged
        );
        
        // Create EmailIssue object
        EmailIssue emailIssue = new EmailIssue();
        emailIssue.setFromEmail(dto.getFromEmail());
        emailIssue.setToEmail(dto.getToEmail());
        emailIssue.setCc(dto.getCc());
        emailIssue.setSubject(dto.getSubject());
        emailIssue.setmIntro(dto.getmIntro());
        emailIssue.setMessage(message);
        emailIssue.setmEnd(dto.getmEnd());
        
        return emailIssue;
    }

}
