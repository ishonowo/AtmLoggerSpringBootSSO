package com.infinity.app.dto;

import java.util.Date;

import jakarta.validation.constraints.NotNull;

public class LoggedIssueDto {
	
	@NotNull
	private String branchName;
	
	@NotNull
	private String terminalId;
	
	@NotNull
	private String terminalName;
	
	@NotNull
	private String vendorName;
	
	@NotNull
	private String issueDesc;
	
	@NotNull
	private Date dateLogged;
	
	@NotNull
	private String branchLogger;
	
	@NotNull
	private String loggerPhone;
	
	@NotNull
	private Date startingDate;
	
	@NotNull
	private Date dateCompleted;
	
	@NotNull
	private String status;

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getTerminalName() {
		return terminalName;
	}

	public void setTerminalName(String terminalName) {
		this.terminalName = terminalName;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getIssueDesc() {
		return issueDesc;
	}

	public void setIssueDesc(String issueDesc) {
		this.issueDesc = issueDesc;
	}

	public Date getDateLogged() {
		return dateLogged;
	}

	public void setDateLogged(Date dateLogged) {
		this.dateLogged = dateLogged;
	}

	public String getBranchLogger() {
		return branchLogger;
	}

	public void setBranchLogger(String branchLogger) {
		this.branchLogger = branchLogger;
	}

	public String getLoggerPhone() {
		return loggerPhone;
	}

	public void setLoggerPhone(String loggerPhone) {
		this.loggerPhone = loggerPhone;
	}

	public Date getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}

	public Date getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(Date dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LoggedIssueDto(@NotNull String branchName, @NotNull String terminalId, @NotNull String terminalName,
			@NotNull String vendorName, @NotNull String issueDesc, @NotNull Date dateLogged,
			@NotNull String branchLogger, @NotNull String loggerPhone, @NotNull Date startingDate,
			@NotNull Date dateCompleted, @NotNull String status) {
		this.branchName = branchName;
		this.terminalId = terminalId;
		this.terminalName = terminalName;
		this.vendorName = vendorName;
		this.issueDesc = issueDesc;
		this.dateLogged = dateLogged;
		this.branchLogger = branchLogger;
		this.loggerPhone = loggerPhone;
		this.startingDate = startingDate;
		this.dateCompleted = dateCompleted;
		this.status = status;
	}

	public LoggedIssueDto() {}

	@Override
	public String toString() {
		return "LoggedIssueDto [branchName=" + branchName + ", terminalId=" + terminalId + ", terminalName="
				+ terminalName + ", vendorName=" + vendorName + ", issueDesc=" + issueDesc + ", dateLogged="
				+ dateLogged + ", branchLogger=" + branchLogger + ", loggerPhone=" + loggerPhone + ", startingDate="
				+ startingDate + ", dateCompleted=" + dateCompleted + ", status=" + status + "]";
	}

	
	
}
