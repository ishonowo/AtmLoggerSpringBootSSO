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
import com.infinity.app.model.LoggedCall;
import com.infinity.app.model.Message;
import com.infinity.app.service.EmailIssueService;
import com.infinity.app.service.LoggedCallService;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.validation.Valid;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/atm/email")
public class EmailIssueController {

	private static final Logger logger = LoggerFactory.getLogger(EmailIssueController.class);
		
	private final EmailIssueService emailIssueService;
	
	private final LoggedCallService loggedService;
	
	public EmailIssueController(EmailIssueService emailIssueService, LoggedCallService loggedService) {
		this.emailIssueService= emailIssueService;
		this.loggedService=loggedService;
	}

	@PostMapping("/sendEmail")
	public ResponseEntity<?> sendEmail(@Valid @RequestBody EmailIssueMessageDto emailIssueMessage, HttpServletRequest request){
		logger.info("Received emailIssueMessage "+emailIssueMessage);
		try {
			// Convert request DTO to EmailIssue domain object
            EmailIssue tranEmailIssue = emailIssueService.convertToEmailIssue(emailIssueMessage);
            // Save and send the email
            /*Iterator<EmailIssue> iterator=tranEmailIssue.iterator();
            while (iterator.hasNext()) {
            	EmailIssue emailIssue= iterator.next();
            	emailIssue=emailIssueService.sendEmail(emailIssue);
            	logger.info("Email submitted successfully. "+ emailIssue);
            	emailIssueService.saveloggedCall(emailIssueMessage,emailIssue.getMessage().getId(),request);
            	//loggedService.saveObj(emailIssueMessage,emailIssue.getMessage().getId(),ip,browser,hostname);
            	logger.info("Call logged successfully. ");
            }*/
            EmailIssue savedEmailIssue = emailIssueService.sendEmail(tranEmailIssue);
            logger.info("Email submitted successfully. "+ savedEmailIssue); 
            emailIssueService.saveloggedCall(emailIssueMessage,tranEmailIssue.getMessage().getId(),request);
            logger.info("Call logged successfully. ");
            return new ResponseEntity<>(tranEmailIssue, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                new ErrorResponse("Failed to send email: " + e.getMessage()), 
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
		
        
    }
    

	

}
