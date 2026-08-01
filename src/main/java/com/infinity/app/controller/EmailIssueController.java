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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/email")
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
		String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isBlank()) {
            ip = request.getRemoteAddr();
        } else {
            // X-Forwarded-For can be a comma-separated chain; first entry is the original client
            ip = ip.split(",")[0].trim();
        }

        
        ip=ipv4(ip);
        
        
        String userAgent = request.getHeader("User-Agent");
        String browser = parseBrowser(userAgent);

        String hostname;
        try {
            hostname = InetAddress.getByName(ip).getCanonicalHostName();
        } catch (UnknownHostException e) {
            hostname = "unknown";
        }

        
		logger.info("Received emailIssueMessage "+emailIssueMessage);
		try {
			// Convert request DTO to EmailIssue domain object
            EmailIssue tranEmailIssue = emailIssueService.convertToEmailIssue(emailIssueMessage);
            // Save and send the email
            EmailIssue savedEmailIssue = emailIssueService.sendEmail(tranEmailIssue);
            logger.info("Email submitted successfully. "+ savedEmailIssue); 
            loggedService.saveObj(emailIssueMessage,tranEmailIssue.getMessage().getId(),ip,browser,hostname);
            logger.info("Call logged successfully. ");
            return new ResponseEntity<>(savedEmailIssue, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                new ErrorResponse("Failed to send email: " + e.getMessage()), 
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
		
        
    }
    private String ipv4(String ip)
    {
        // Normalize localhost IPv6 loopback to IPv4 loopback
        if (ip.equals("0:0:0:0:0:0:0:1") || ip.equals("::1")) {
            return "127.0.0.1";
        }

        try {
            InetAddress addr = InetAddress.getByName(ip);

            // If it's an IPv4-mapped IPv6 address (::ffff:192.168.1.1),
            // getHostAddress() on the resolved Inet4Address form extracts the IPv4 part
            if (addr instanceof Inet4Address) {
                return addr.getHostAddress();
            } else {
                // True IPv6 address with no IPv4 mapping — can't be meaningfully converted
                return ip; // or "unavailable", depending on what you want downstream
            }
        } catch (UnknownHostException e) {
            return ip; // fallback to whatever raw value we had
        }

    }

    private String parseBrowser(String userAgent) {
        if (userAgent == null) return "unknown";
        if (userAgent.contains("Edg/")) return "Edge";
        if (userAgent.contains("Chrome/") && !userAgent.contains("Chromium")) return "Chrome";
        if (userAgent.contains("Firefox/")) return "Firefox";
        if (userAgent.contains("Safari/") && !userAgent.contains("Chrome")) return "Safari";
        if (userAgent.contains("OPR/") || userAgent.contains("Opera")) return "Opera";
        return "unknown";
    }	
	

}
