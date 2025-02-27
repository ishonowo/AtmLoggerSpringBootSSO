package com.infinity.app.controller;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.infinity.app.model.EmailIssue;
import com.infinity.app.service.EmailIssueService;

import jakarta.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = {"Content-Type", "Authorization"}, 
methods = {RequestMethod.POST, RequestMethod.DELETE},
allowCredentials = "true")*/
@RestController
@RequestMapping("/email")
//@PreAuthorize("hasRole('ROLE_Logger')")
public class EmailIssueController {

	private static final Logger logger = LoggerFactory.getLogger(EmailIssueController.class);
		
	private final EmailIssueService emailIssueService;
	
	public EmailIssueController(EmailIssueService emailIssueService) {
		this.emailIssueService= emailIssueService;
	}

	@PostMapping("/sendEmail")
	public void sendEmail(@RequestBody EmailIssue emailIssue,
            BindingResult bindingResult){
		if(bindingResult.hasErrors()){
            throw new ValidationException("This email has errors and cannot be sent.");
        }
		logger.info("Received emailIssue "+emailIssue);
		emailIssueService.sendEmail(emailIssue);
        logger.info("Email submitted successfully. "+ emailIssue); 
        
    }
	
	
}
