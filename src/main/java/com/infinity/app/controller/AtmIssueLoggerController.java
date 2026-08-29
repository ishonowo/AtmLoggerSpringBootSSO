package com.infinity.app.controller;
import java.util.Date;
import java.util.List;

import jakarta.validation.ValidationException;
import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infinity.app.dto.IssueLogged;
import com.infinity.app.model.AtmDetail;
import com.infinity.app.model.AtmFault;
import com.infinity.app.model.AtmIssue;
import com.infinity.app.repo.AtmFaultRepo;
import com.infinity.app.service.AtmDetailService;
import com.infinity.app.service.AtmIssueService;

@RestController
@RequestMapping("/atm")
public class AtmIssueLoggerController {

	private static final Logger logger = LoggerFactory.getLogger(AtmIssueLoggerController.class);

	private final AtmDetailService atmService;

	private final AtmIssueService issueService;

	private final AtmFaultRepo atmFaultRepo;

	private AtmDetail atmDetail;

	@Value("${atm.support.email}")
	private String supportEmail;

	public AtmIssueLoggerController(AtmDetailService atmService, AtmIssueService issueService,
			AtmFaultRepo atmFaultRepo) {
		this.atmService = atmService;
		this.issueService = issueService;
		this.atmFaultRepo = atmFaultRepo;
	}

	@GetMapping("/")
	public String hello() {
		return "ATM issue app is available";
	}

	@PostMapping("/issue")
	public AtmIssue submitLoggedIssue(
			@RequestBody IssueLogged issueLogged, BindingResult bindingResult, HttpServletRequest request
			) {
		if (bindingResult.hasErrors()) {
			throw new ValidationException("This issue log has errors and cannot be sent.");
		}

		atmDetail = atmService.getAtmDetail(issueLogged.getTerminalId());
		String atmContacts = issueService.getContacts(issueLogged.getTerminalId());

		// Resolve the selected fault ids into their actual AtmFault rows.
		List<AtmFault> selectedFaults = atmFaultRepo.findAllById(issueLogged.getAtmFaultIds());

		if (selectedFaults.isEmpty()) {
			throw new ValidationException("No valid faults were selected for this issue.");
		}

		boolean othersSelected = selectedFaults.stream()
				.anyMatch(f -> "Others".equals(f.getNatureOfFault()));

		if (othersSelected
				&& (issueLogged.getOtherFaultDesc() == null || issueLogged.getOtherFaultDesc().trim().length() < 10)) {
			throw new ValidationException("A description of at least 10 characters is required when 'Others' is selected.");
		}

		AtmIssue atmIssueGen = new AtmIssue(issueLogged.getTerminalId(), selectedFaults,
				issueLogged.getOtherFaultDesc(),
				issueLogged.getBranchLogger(), issueLogged.getLoggerEmail(), issueLogged.getLoggerPhoneNo(),
				new Date(), supportEmail,
				atmContacts,
				atmDetail.getBranchEmail(),
				atmDetail.getBranchName(), atmDetail.getAtmName(), atmDetail.getPhysicalAddress(),
				atmDetail.getVendorName(), issueLogged.getUserEmail());

		AtmIssue atmIssue = issueService.save(atmIssueGen);

		logger.info("Form submitted successfully. " + atmIssue);

		return atmIssue;
	}

	@DeleteMapping("/delete/{id}")
	public void DeleteIssue(@PathVariable Long id) {
		issueService.deleteById(id);
	}
}