package com.infinity.app.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.ValidationException;
import jakarta.servlet.http.HttpServletRequest;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
//import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infinity.app.dto.IssueLogged;
import com.infinity.app.model.AtmDetail;
import com.infinity.app.model.AtmIssue;
import com.infinity.app.service.AtmDetailService;
import com.infinity.app.service.AtmIssueService;



@RestController
@RequestMapping("/atm")
public class AtmIssueLoggerController {
	
	private static final Logger logger = LoggerFactory.getLogger(AtmIssueLoggerController.class);
	
	private final AtmDetailService atmService;
	
	private final AtmIssueService issueService;
	
	private AtmDetail atmDetail;
	
	@Value("${atm.support.email}")
	private String supportEmail;
	
	public AtmIssueLoggerController(AtmDetailService atmService, AtmIssueService issueService) {
		this.atmService= atmService;
		this.issueService= issueService;
		//this.atmDetail=atmDetail;
	}
	
	@GetMapping("/")
	public String hello() {
		return "ATM issue app is available";
	}

	@PostMapping("/issue")
	public AtmIssue submitLoggedIssue(
			@RequestBody IssueLogged issueLogged, BindingResult bindingResult, HttpServletRequest request
			) {
		if(bindingResult.hasErrors()){
            throw new ValidationException("This issue log has errors and cannot be sent.");
        }
		

		
		atmDetail= atmService.getAtmDetail(issueLogged.getTerminalId());
		List<String> results=atmService.getActiveContacts(issueLogged.getTerminalId());
		String atmContacts= results.stream().collect(Collectors.joining(";"));
		
		
		AtmIssue atmIssueGen= new AtmIssue(issueLogged.getTerminalId(),issueLogged.getIssueDesc(),
				issueLogged.getBranchLogger(),issueLogged.getLoggerEmail(),issueLogged.getLoggerPhoneNo(),
				new Date(),supportEmail,
				atmContacts,
				atmDetail.getBranchEmail(),
				atmDetail.getBranchName(),atmDetail.getAtmName(),atmDetail.getPhysicalAddress(),
				atmDetail.getVendorName(),issueLogged.getUserEmail());
		
		AtmIssue atmIssue=issueService.save(atmIssueGen);
		
		logger.info("Form submitted successfully. "+ atmIssue);
		
		return atmIssue;				
	}
	
	@DeleteMapping("/delete/{id}")
	public void DeleteIssue(@PathVariable Long id) {
		issueService.deleteById(id);
		
	}

}
