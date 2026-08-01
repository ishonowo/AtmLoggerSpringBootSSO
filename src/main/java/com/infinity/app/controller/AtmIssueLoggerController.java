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



/*@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = {"Content-Type", "Authorization"}, 
methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS},
allowCredentials = "true")*/
@RestController
@RequestMapping("/atm")
//@PreAuthorize("hasRole('ROLE_Logger')")
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
		
/*		String ip = request.getHeader("X-Forwarded-For");
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
*/
		
		atmDetail= atmService.getAtmDetail(issueLogged.getTerminalId());
		List<String> results=atmService.getActiveContacts(issueLogged.getTerminalId());
		String atmContacts= results.stream().collect(Collectors.joining(";"));
		
		
		AtmIssue atmIssueGen= new AtmIssue(issueLogged.getTerminalId(),issueLogged.getIssueDesc(),
				issueLogged.getBranchLogger(),issueLogged.getLoggerEmail(),issueLogged.getLoggerPhoneNo(),
				new Date(),supportEmail,
				atmContacts,
				atmDetail.getBranchEmail(),
				atmDetail.getBranchName(),atmDetail.getAtmName(),atmDetail.getPhysicalAddress(),
				atmDetail.getVendorName(),issueLogged.getUserEmail());//,ip,browser,hostname);
		
		AtmIssue atmIssue=issueService.save(atmIssueGen);
		
		logger.info("Form submitted successfully. "+ atmIssue);
		
		return atmIssue;				
	}
	
	@DeleteMapping("/delete/{id}")
	public void DeleteIssue(@PathVariable Long id) {
		issueService.deleteById(id);
		
	}

/*    private String parseBrowser(String userAgent) {
        if (userAgent == null) return "unknown";
        if (userAgent.contains("Edg/")) return "Edge";
        if (userAgent.contains("Chrome/") && !userAgent.contains("Chromium")) return "Chrome";
        if (userAgent.contains("Firefox/")) return "Firefox";
        if (userAgent.contains("Safari/") && !userAgent.contains("Chrome")) return "Safari";
        if (userAgent.contains("OPR/") || userAgent.contains("Opera")) return "Opera";
        return "unknown";
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

    }*/
}
