package com.infinity.app.service;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
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
	private final LoggedCallService loggedService;

	@Value("${power.contact}")
	private String powerContactEmail;
	
	public EmailIssueService(EmailIssueRepo emailIssueRepo, MessageRepo messageRepo, AtmFaultRepo atmFaultRepo,
			Environment environment, Configuration freemarkerConfig, LoggedCallService loggedService) {
		this.emailIssueRepo = emailIssueRepo;
		this.messageRepo = messageRepo;
		this.atmFaultRepo = atmFaultRepo;
		this.freemarkerConfig = freemarkerConfig;
		this.loggedService = loggedService;

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

		List<Long> faultIds = dto.getAtmFaults() == null ? List.of()
				: dto.getAtmFaults().stream().map(AtmFault::getId).toList();

		List<AtmFault> canonicalFaults = atmFaultRepo.findAllById(faultIds);
		//List<AtmFault> powerFaults = new ArrayList<>(); 

		/*Iterator<AtmFault> iterator = canonicalFaults.iterator();
		while (iterator.hasNext()) {
			AtmFault fault = iterator.next();
			if ("POWER".equals(fault.getFaultType().toUpperCase())) {
				powerFaults.add(fault);
				iterator.remove();
			}
		}*/
		// Create Message object using the canonical (untouched) faults.
		EmailIssue tranEmailIssue = null;//, powerEmailIssue = null;

		if (!canonicalFaults.isEmpty()) {
			Message message = new Message(dto.getPhysicalAddress(), dto.getBranchName(), dto.getVendorName(),
					canonicalFaults, dto.getOtherFaultDesc(), dto.getBranchLogger(), dto.getLoggerPhone(),
					dto.getDateLogged());
			messageRepo.save(message);

			// Create EmailIssue object
			EmailIssue emailIssue = new EmailIssue(dto.getFromEmail(), dto.getToEmail(), dto.getCc(), dto.getSubject(),
					dto.getmIntro(), message, dto.getmEnd());

			tranEmailIssue = emailIssueRepo.save(emailIssue);
		}

		
		return tranEmailIssue;
	}

	@Transactional
	public EmailIssue sendEmail(EmailIssue emailIssue) {

		try {
			// Create a MIME message
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);

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

	public void saveloggedCall(@Valid EmailIssueMessageDto emailIssueMessage, Long id, HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isBlank()) {
			ip = request.getRemoteAddr();
		} else {
			// X-Forwarded-For can be a comma-separated chain; first entry is the original
			// client
			ip = ip.split(",")[0].trim();
		}
		String hostname;
		try {
			hostname = InetAddress.getByName(ip).getCanonicalHostName();
		} catch (UnknownHostException e) {
			hostname = "unknown";
		}

		ip = ipv4(ip);
		
		String userAgent = request.getHeader("User-Agent");
		String browser = parseBrowser(userAgent);

		loggedService.saveObj(emailIssueMessage, id, ip, browser, hostname);
	}

	private String ipv4(String ip) {
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