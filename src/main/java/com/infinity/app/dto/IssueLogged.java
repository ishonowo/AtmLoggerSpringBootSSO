package com.infinity.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class IssueLogged {
	
	@NotNull
	@Email
	private String userEmail;

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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getIssueDesc() {
		return issueDesc;
	}

	public void setIssueDesc(String issueDesc) {
		this.issueDesc = issueDesc;
	}

	public String getBranchLogger() {
		return branchLogger;
	}

	public void setBranchLogger(String branchLogger) {
		this.branchLogger = branchLogger;
	}

	public String getLoggerEmail() {
		return loggerEmail;
	}

	public void setLoggerEmail(String loggerEmail) {
		this.loggerEmail = loggerEmail;
	}

	public String getLoggerPhoneNo() {
		return loggerPhoneNo;
	}

	public void setLoggerPhoneNo(String loggerPhoneNo) {
		this.loggerPhoneNo = loggerPhoneNo;
	}

	public IssueLogged(@NotNull String userEmail, @NotNull @Size(min = 8, max = 8) String terminalId, @NotNull String issueDesc,
			@NotNull String branchLogger, @NotNull String loggerEmail, @NotNull @Size(min = 11, max = 14) String loggerPhoneNo) {
		super();
		this.userEmail= userEmail;
		this.terminalId = terminalId;
		this.issueDesc = issueDesc;
		this.branchLogger = branchLogger;
		this.loggerEmail = loggerEmail;
		this.loggerPhoneNo = loggerPhoneNo;
	}

	public IssueLogged() {
		super();
	}

	@Override
	public String toString() {
		return "IssueLogged [userEmail="+ userEmail + ", terminalId=" + terminalId + ", issueDesc=" + issueDesc + ", branchLogger=" + branchLogger
				+ ", loggerEmail=" + loggerEmail + ", loggerPhoneNo=" + loggerPhoneNo + "]";
	}
	
	
	
	
}
