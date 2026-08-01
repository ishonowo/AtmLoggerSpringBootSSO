package com.infinity.app.dto;

import java.util.Date;
import java.util.Objects;

import jakarta.validation.constraints.NotNull;

public class LoggedCallDto {
	

	@NotNull
	private Long logId;
	
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
	
	//@NotNull
	//private String loggerEmail;
	
	@NotNull
	private String branchLogger;
	
	@NotNull
	private String loggerPhone;
	
	@NotNull
	private Date startingDate;
	
	@NotNull
	private Date dateCompleted;
	
	@NotNull
	private String fromEmail;
	
	private String browserUsed;
	
	private String hostName;
	
	private String loggerIP;
	
	@NotNull
	private String statusDesc;
	
	@NotNull
	private Long statusId;

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

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

	/*public String getLoggerEmail() {
		return loggerEmail;
	}

	public void setLoggerEmail(String loggerEmail) {
		this.loggerEmail = loggerEmail;
	}*/

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

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getBrowserUsed() {
		return browserUsed;
	}

	public void setBrowserUsed(String browserUsed) {
		this.browserUsed = browserUsed;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getLoggerIP() {
		return loggerIP;
	}

	public void setLoggerIP(String loggerIP) {
		this.loggerIP = loggerIP;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public LoggedCallDto(@NotNull Long logId, @NotNull String branchName, @NotNull String terminalId,
			@NotNull String terminalName, @NotNull String vendorName, @NotNull String issueDesc,
			@NotNull Date dateLogged,@NotNull String fromEmail,// @NotNull String loggerEmail,
			@NotNull String branchLogger,
			@NotNull String loggerPhone, @NotNull Date startingDate, @NotNull Date dateCompleted,
			 String browserUsed, String hostName, String loggerIP, @NotNull String statusDesc,
			@NotNull Long statusId) {
		super();
		this.logId = logId;
		this.branchName = branchName;
		this.terminalId = terminalId;
		this.terminalName = terminalName;
		this.vendorName = vendorName;
		this.issueDesc = issueDesc;
		this.dateLogged = dateLogged;
		this.fromEmail = fromEmail;
		//this.loggerEmail = loggerEmail;
		this.branchLogger = branchLogger;
		this.loggerPhone = loggerPhone;
		this.startingDate = startingDate;
		this.dateCompleted = dateCompleted;
		this.browserUsed = browserUsed;
		this.hostName = hostName;
		this.loggerIP = loggerIP;
		this.statusDesc = statusDesc;
		this.statusId = statusId;
	}

	public LoggedCallDto(@NotNull String branchName, @NotNull String terminalId, @NotNull String terminalName,
			@NotNull String vendorName, @NotNull String issueDesc, @NotNull Date dateLogged,
			//@NotNull String loggerEmail,
			@NotNull String branchLogger, @NotNull String loggerPhone,
			@NotNull Date startingDate, @NotNull Date dateCompleted, @NotNull String fromEmail, String browserUsed,
			String hostName, String loggerIP, @NotNull String statusDesc, @NotNull Long statusId) {
		super();
		this.branchName = branchName;
		this.terminalId = terminalId;
		this.terminalName = terminalName;
		this.vendorName = vendorName;
		this.issueDesc = issueDesc;
		this.dateLogged = dateLogged;
		//this.loggerEmail = loggerEmail;
		this.branchLogger = branchLogger;
		this.loggerPhone = loggerPhone;
		this.startingDate = startingDate;
		this.dateCompleted = dateCompleted;
		this.fromEmail = fromEmail;
		this.browserUsed = browserUsed;
		this.hostName = hostName;
		this.loggerIP = loggerIP;
		this.statusDesc = statusDesc;
		this.statusId = statusId;
	}

	public LoggedCallDto() {
		super();
		// TODO Auto-generated constructor stub
	}





	@Override
	public String toString() {
		return "LoggedCallDto [logId=" + logId + ", branchName=" + branchName + ", terminalId=" + terminalId
				+ ", terminalName=" + terminalName + ", vendorName=" + vendorName + ", issueDesc=" + issueDesc
				+ ", dateLogged=" + dateLogged //+ ", loggerEmail=" + loggerEmail 
				+ ", branchLogger=" + branchLogger
				+ ", loggerPhone=" + loggerPhone + ", startingDate=" + startingDate + ", dateCompleted=" + dateCompleted
				+ ", fromEmail=" + fromEmail + ", browserUsed=" + browserUsed + ", hostName=" + hostName + ", loggerIP="
				+ loggerIP + ", statusDesc=" + statusDesc + ", statusId=" + statusId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(branchLogger, branchName, browserUsed, dateCompleted, dateLogged, fromEmail, hostName,
				issueDesc, logId, //loggerEmail,
				loggerIP, loggerPhone, startingDate, statusDesc, statusId, terminalId,
				terminalName, vendorName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoggedCallDto other = (LoggedCallDto) obj;
		return Objects.equals(branchLogger, other.branchLogger) && Objects.equals(branchName, other.branchName)
				&& Objects.equals(browserUsed, other.browserUsed) && Objects.equals(dateCompleted, other.dateCompleted)
				&& Objects.equals(dateLogged, other.dateLogged) && Objects.equals(fromEmail, other.fromEmail)
				&& Objects.equals(hostName, other.hostName) && Objects.equals(issueDesc, other.issueDesc)
				&& Objects.equals(logId, other.logId) //&& Objects.equals(loggerEmail, other.loggerEmail)
				&& Objects.equals(loggerIP, other.loggerIP) && Objects.equals(loggerPhone, other.loggerPhone)
				&& Objects.equals(startingDate, other.startingDate) && Objects.equals(statusDesc, other.statusDesc)
				&& Objects.equals(statusId, other.statusId) && Objects.equals(terminalId, other.terminalId)
				&& Objects.equals(terminalName, other.terminalName) && Objects.equals(vendorName, other.vendorName);
	}




}
