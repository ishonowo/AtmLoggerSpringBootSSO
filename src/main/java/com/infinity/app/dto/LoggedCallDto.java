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
	
	@NotNull
	private String branchLogger;
	
	@NotNull
	private String loggerPhone;
	
	@NotNull
	private Date startingDate;
	
	//@NotNull
	private Date dateCompleted;
	
	@NotNull
	private String loggerEmail;
	
	private String loggerBrowserUsed;
	
	private String loggerHostName;
	
	private String loggerIP;
	
	private String closerEmail;
	
	private String closerBrowserUsed;
	
	private String closerHostName;
	
	private String closerIP;
	
	private String reopenerEmail;
	
	private String reopenerBrowserUsed;
	
	private String reopenerHostName;
	
	private String reopenerIP;
	
	@NotNull
	private String statusDesc;
	
	@NotNull
	private Long statusId;

	// --- SLA fields (not @NotNull: this DTO is also used as the PUT/update
	// request body via LoggedCallController.updateLoggedCall, and those
	// callers don't supply these — they're populated server-side on GET) ---

	private Date holdStart;

	private Date holdEnd;

	private Integer allowedHours;

	private Date slaDeadline;

	private boolean slaBreached;


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

	public String getLoggerEmail() {
		return loggerEmail;
	}

	public void setLoggerEmail(String loggerEmail) {
		this.loggerEmail = loggerEmail;
	}

	public String getLoggerBrowserUsed() {
		return loggerBrowserUsed;
	}

	public void setLoggerBrowserUsed(String loggerBrowserUsed) {
		this.loggerBrowserUsed = loggerBrowserUsed;
	}

	public String getLoggerHostName() {
		return loggerHostName;
	}

	public void setLoggerHostName(String loggerHostName) {
		this.loggerHostName = loggerHostName;
	}

	public String getLoggerIP() {
		return loggerIP;
	}

	public void setLoggerIP(String loggerIP) {
		this.loggerIP = loggerIP;
	}

	public String getCloserEmail() {
		return closerEmail;
	}

	public void setCloserEmail(String closerEmail) {
		this.closerEmail = closerEmail;
	}

	public String getCloserBrowserUsed() {
		return closerBrowserUsed;
	}

	public void setCloserBrowserUsed(String closerBrowserUsed) {
		this.closerBrowserUsed = closerBrowserUsed;
	}

	public String getCloserHostName() {
		return closerHostName;
	}

	public void setCloserHostName(String closerHostName) {
		this.closerHostName = closerHostName;
	}

	public String getCloserIP() {
		return closerIP;
	}

	public void setCloserIP(String closerIP) {
		this.closerIP = closerIP;
	}

	public String getReopenerEmail() {
		return reopenerEmail;
	}

	public void setReopenerEmail(String reopenerEmail) {
		this.reopenerEmail = reopenerEmail;
	}

	public String getReopenerBrowserUsed() {
		return reopenerBrowserUsed;
	}

	public void setReopenerBrowserUsed(String reopenerBrowserUsed) {
		this.reopenerBrowserUsed = reopenerBrowserUsed;
	}

	public String getReopenerHostName() {
		return reopenerHostName;
	}

	public void setReopenerHostName(String reopenerHostName) {
		this.reopenerHostName = reopenerHostName;
	}

	public String getReopenerIP() {
		return reopenerIP;
	}

	public void setReopenerIP(String reopenerIP) {
		this.reopenerIP = reopenerIP;
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

	public Date getHoldStart() {
		return holdStart;
	}

	public void setHoldStart(Date holdStart) {
		this.holdStart = holdStart;
	}

	public Date getHoldEnd() {
		return holdEnd;
	}

	public void setHoldEnd(Date holdEnd) {
		this.holdEnd = holdEnd;
	}

	public Integer getAllowedHours() {
		return allowedHours;
	}

	public void setAllowedHours(Integer allowedHours) {
		this.allowedHours = allowedHours;
	}

	public Date getSlaDeadline() {
		return slaDeadline;
	}

	public void setSlaDeadline(Date slaDeadline) {
		this.slaDeadline = slaDeadline;
	}

	public boolean isSlaBreached() {
		return slaBreached;
	}

	public void setSlaBreached(boolean slaBreached) {
		this.slaBreached = slaBreached;
	}

	public LoggedCallDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public LoggedCallDto(@NotNull String branchName, @NotNull String terminalId, @NotNull String terminalName,
			@NotNull String vendorName, @NotNull String issueDesc, @NotNull Date dateLogged,
			@NotNull String branchLogger, @NotNull String loggerPhone, @NotNull Date startingDate, Date dateCompleted,
			@NotNull String loggerEmail, String loggerBrowserUsed, String loggerHostName, String loggerIP,
			String closerEmail, String closerBrowserUsed, String closerHostName, String closerIP, String reopenerEmail,
			String reopenerBrowserUsed, String reopenerHostName, String reopenerIP, @NotNull String statusDesc,
			@NotNull Long statusId, Date holdStart, Date holdEnd, Integer allowedHours, Date slaDeadline,
			boolean slaBreached) {
		super();
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
		this.loggerEmail = loggerEmail;
		this.loggerBrowserUsed = loggerBrowserUsed;
		this.loggerHostName = loggerHostName;
		this.loggerIP = loggerIP;
		this.closerEmail = closerEmail;
		this.closerBrowserUsed = closerBrowserUsed;
		this.closerHostName = closerHostName;
		this.closerIP = closerIP;
		this.reopenerEmail = reopenerEmail;
		this.reopenerBrowserUsed = reopenerBrowserUsed;
		this.reopenerHostName = reopenerHostName;
		this.reopenerIP = reopenerIP;
		this.statusDesc = statusDesc;
		this.statusId = statusId;
		this.holdStart = holdStart;
		this.holdEnd = holdEnd;
		this.allowedHours = allowedHours;
		this.slaDeadline = slaDeadline;
		this.slaBreached = slaBreached;
	}

	public LoggedCallDto(@NotNull Long logId, @NotNull String branchName, @NotNull String terminalId,
			@NotNull String terminalName, @NotNull String vendorName, @NotNull String issueDesc,
			@NotNull Date dateLogged, @NotNull String branchLogger, @NotNull String loggerPhone,
			@NotNull Date startingDate, Date dateCompleted, @NotNull String loggerEmail, String loggerBrowserUsed,
			String loggerHostName, String loggerIP, String closerEmail, String closerBrowserUsed, String closerHostName,
			String closerIP, String reopenerEmail, String reopenerBrowserUsed, String reopenerHostName,
			String reopenerIP, @NotNull String statusDesc, @NotNull Long statusId, Date holdStart, Date holdEnd,
			Integer allowedHours, Date slaDeadline, boolean slaBreached) {
		super();
		this.logId = logId;
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
		this.loggerEmail = loggerEmail;
		this.loggerBrowserUsed = loggerBrowserUsed;
		this.loggerHostName = loggerHostName;
		this.loggerIP = loggerIP;
		this.closerEmail = closerEmail;
		this.closerBrowserUsed = closerBrowserUsed;
		this.closerHostName = closerHostName;
		this.closerIP = closerIP;
		this.reopenerEmail = reopenerEmail;
		this.reopenerBrowserUsed = reopenerBrowserUsed;
		this.reopenerHostName = reopenerHostName;
		this.reopenerIP = reopenerIP;
		this.statusDesc = statusDesc;
		this.statusId = statusId;
		this.holdStart = holdStart;
		this.holdEnd = holdEnd;
		this.allowedHours = allowedHours;
		this.slaDeadline = slaDeadline;
		this.slaBreached = slaBreached;
	}


	@Override
	public String toString() {
		return "LoggedCallDto [logId=" + logId + ", branchName=" + branchName + ", terminalId=" + terminalId
				+ ", terminalName=" + terminalName + ", vendorName=" + vendorName + ", issueDesc=" + issueDesc
				+ ", dateLogged=" + dateLogged + ", branchLogger=" + branchLogger + ", loggerPhone=" + loggerPhone
				+ ", startingDate=" + startingDate + ", dateCompleted=" + dateCompleted + ", loggerEmail=" + loggerEmail
				+ ", loggerBrowserUsed=" + loggerBrowserUsed + ", loggerHostName=" + loggerHostName + ", loggerIP="
				+ loggerIP + ", closerEmail=" + closerEmail + ", closerBrowserUsed=" + closerBrowserUsed
				+ ", closerHostName=" + closerHostName + ", closerIP=" + closerIP + ", reopenerEmail=" + reopenerEmail
				+ ", reopenerBrowserUsed=" + reopenerBrowserUsed + ", reopenerHostName=" + reopenerHostName
				+ ", reopenerIP=" + reopenerIP + ", statusDesc=" + statusDesc + ", statusId=" + statusId
				+ ", holdStart=" + holdStart + ", holdEnd=" + holdEnd + ", allowedHours=" + allowedHours
				+ ", slaDeadline=" + slaDeadline + ", slaBreached=" + slaBreached + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(allowedHours, branchLogger, branchName, closerBrowserUsed, closerEmail, closerHostName,
				closerIP, dateCompleted, dateLogged, holdEnd, holdStart, issueDesc, loggerBrowserUsed, loggerEmail,
				loggerHostName, loggerIP, loggerPhone, reopenerBrowserUsed, reopenerEmail, reopenerHostName, reopenerIP,
				slaBreached, slaDeadline, startingDate, statusDesc, statusId, terminalId, terminalName, vendorName);
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
		return Objects.equals(allowedHours, other.allowedHours) && Objects.equals(branchLogger, other.branchLogger)
				&& Objects.equals(branchName, other.branchName)
				&& Objects.equals(closerBrowserUsed, other.closerBrowserUsed)
				&& Objects.equals(closerEmail, other.closerEmail)
				&& Objects.equals(closerHostName, other.closerHostName) && Objects.equals(closerIP, other.closerIP)
				&& Objects.equals(dateCompleted, other.dateCompleted) && Objects.equals(dateLogged, other.dateLogged)
				&& Objects.equals(holdEnd, other.holdEnd) && Objects.equals(holdStart, other.holdStart)
				&& Objects.equals(issueDesc, other.issueDesc)
				&& Objects.equals(loggerBrowserUsed, other.loggerBrowserUsed)
				&& Objects.equals(loggerEmail, other.loggerEmail)
				&& Objects.equals(loggerHostName, other.loggerHostName) && Objects.equals(loggerIP, other.loggerIP)
				&& Objects.equals(loggerPhone, other.loggerPhone)
				&& Objects.equals(reopenerBrowserUsed, other.reopenerBrowserUsed)
				&& Objects.equals(reopenerEmail, other.reopenerEmail)
				&& Objects.equals(reopenerHostName, other.reopenerHostName)
				&& Objects.equals(reopenerIP, other.reopenerIP) && slaBreached == other.slaBreached
				&& Objects.equals(slaDeadline, other.slaDeadline) && Objects.equals(startingDate, other.startingDate)
				&& Objects.equals(statusDesc, other.statusDesc) && Objects.equals(statusId, other.statusId)
				&& Objects.equals(terminalId, other.terminalId) && Objects.equals(terminalName, other.terminalName)
				&& Objects.equals(vendorName, other.vendorName);
	}

	// NOTE: hashCode/equals intentionally left unchanged (based on the
	// original required fields only) so existing equality checks elsewhere
	// in the codebase aren't affected by the newly added SLA fields.
}
