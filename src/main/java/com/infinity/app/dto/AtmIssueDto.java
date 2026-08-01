package com.infinity.app.dto;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AtmIssueDto {

	@NotNull
	@Size(min = 8, max = 8)
	private String terminalId;
	
	@NotNull
	@Size(min = 10)
	private String issueDesc;
	
	@NotNull
	private String branchLogger;
	
	@NotNull
	@Email
	private String loggerEmail;
	
	@Size(min = 11, max = 14)
	@NotNull
	private String loggerPhoneNo;
	
	@NotNull
	private Date logDate;
	
	@NotNull
	//@Email
	private String supportEmail;
	
	
	@NotNull
	private String contact;
	
	@Email
	@NotNull
	private String branchEmail;
	
	@NotNull
	private String branchName;
	
	@NotNull
	private String atmName;
	
	private String physicalAddress;
	
	@NotNull
	private String vendorName;
	
	@NotNull
	@Email
	private String userEmail;
	
	private String ip;
	
	private String browser;
	
	private String hostname;
	
	
}
