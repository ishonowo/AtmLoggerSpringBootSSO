package com.infinity.app.model;

import java.util.Date;
import java.util.Objects;

import org.springframework.context.annotation.Lazy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
@Lazy(false)
@Table(name="logged_calls")
public class LoggedCall {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Long branchId;
	
	@NotNull
	private Long tId;
	
	@NotNull
	private Long vendorId;
	
	@NotNull
	private Long messageId;
	
	@NotNull
	private Date dateLogged;
	
	@NotNull
	private Date startingDate;
	
	@NotNull
	private Long statusId;
	
	@Email
	private String loggerEmail;
	
	private String loggerIp;
	
	private String loggerBrowser;
	
	private String loggerHostname;
	
	
	@Email
	private String closerEmail;
	
	private String closerIp;
	
	private String closerBrowser;
	
	private String closerHostname;
	
	
	@Email
	private String reopenerEmail;
	
	private String reopenerIp;
	
	private String reopenerBrowser;
	
	private String reopenerHostname;
	

	private Date dateCompleted;
	
	private Date holdStart;
	
	private Date holdEnd;

	public LoggedCall() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoggedCall(@NotNull Long branchId, @NotNull Long tId, @NotNull Long vendorId, @NotNull Long messageId,
			@NotNull Date dateLogged, @NotNull Date startingDate, @NotNull Long statusId, @Email String loggerEmail,
			String loggerIp, String loggerBrowser, String loggerHostname, @Email String closerEmail, String closerIp,
			String closerBrowser, String closerHostname, @Email String reopenerEmail, String reopenerIp,
			String reopenerBrowser, String reopenerHostname, Date dateCompleted, Date holdStart, Date holdEnd) {
		super();
		this.branchId = branchId;
		this.tId = tId;
		this.vendorId = vendorId;
		this.messageId = messageId;
		this.dateLogged = dateLogged;
		this.startingDate = startingDate;
		this.statusId = statusId;
		this.loggerEmail = loggerEmail;
		this.loggerIp = loggerIp;
		this.loggerBrowser = loggerBrowser;
		this.loggerHostname = loggerHostname;
		this.closerEmail = closerEmail;
		this.closerIp = closerIp;
		this.closerBrowser = closerBrowser;
		this.closerHostname = closerHostname;
		this.reopenerEmail = reopenerEmail;
		this.reopenerIp = reopenerIp;
		this.reopenerBrowser = reopenerBrowser;
		this.reopenerHostname = reopenerHostname;
		this.dateCompleted = dateCompleted;
		this.holdStart = holdStart;
		this.holdEnd = holdEnd;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Long gettId() {
		return tId;
	}

	public void settId(Long tId) {
		this.tId = tId;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public Date getDateLogged() {
		return dateLogged;
	}

	public void setDateLogged(Date dateLogged) {
		this.dateLogged = dateLogged;
	}

	public Date getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getLoggerEmail() {
		return loggerEmail;
	}

	public void setLoggerEmail(String loggerEmail) {
		this.loggerEmail = loggerEmail;
	}

	public String getLoggerIp() {
		return loggerIp;
	}

	public void setLoggerIp(String loggerIp) {
		this.loggerIp = loggerIp;
	}

	public String getLoggerBrowser() {
		return loggerBrowser;
	}

	public void setLoggerBrowser(String loggerBrowser) {
		this.loggerBrowser = loggerBrowser;
	}

	public String getLoggerHostname() {
		return loggerHostname;
	}

	public void setLoggerHostname(String loggerHostname) {
		this.loggerHostname = loggerHostname;
	}

	public String getCloserEmail() {
		return closerEmail;
	}

	public void setCloserEmail(String closerEmail) {
		this.closerEmail = closerEmail;
	}

	public String getCloserIp() {
		return closerIp;
	}

	public void setCloserIp(String closerIp) {
		this.closerIp = closerIp;
	}

	public String getCloserBrowser() {
		return closerBrowser;
	}

	public void setCloserBrowser(String closerBrowser) {
		this.closerBrowser = closerBrowser;
	}

	public String getCloserHostname() {
		return closerHostname;
	}

	public void setCloserHostname(String closerHostname) {
		this.closerHostname = closerHostname;
	}

	public String getReopenerEmail() {
		return reopenerEmail;
	}

	public void setReopenerEmail(String reopenerEmail) {
		this.reopenerEmail = reopenerEmail;
	}

	public String getReopenerIp() {
		return reopenerIp;
	}

	public void setReopenerIp(String reopenerIp) {
		this.reopenerIp = reopenerIp;
	}

	public String getReopenerBrowser() {
		return reopenerBrowser;
	}

	public void setReopenerBrowser(String reopenerBrowser) {
		this.reopenerBrowser = reopenerBrowser;
	}

	public String getReopenerHostname() {
		return reopenerHostname;
	}

	public void setReopenerHostname(String reopenerHostname) {
		this.reopenerHostname = reopenerHostname;
	}

	public Date getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(Date dateCompleted) {
		this.dateCompleted = dateCompleted;
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

	@Override
	public String toString() {
		return "LoggedCall [id=" + id + ", branchId=" + branchId + ", tId=" + tId + ", vendorId=" + vendorId
				+ ", messageId=" + messageId + ", dateLogged=" + dateLogged + ", startingDate=" + startingDate
				+ ", statusId=" + statusId + ", loggerEmail=" + loggerEmail + ", loggerIp=" + loggerIp
				+ ", loggerBrowser=" + loggerBrowser + ", loggerHostname=" + loggerHostname + ", closerEmail="
				+ closerEmail + ", closerIp=" + closerIp + ", closerBrowser=" + closerBrowser + ", closerHostname="
				+ closerHostname + ", reopenerEmail=" + reopenerEmail + ", reopenerIp=" + reopenerIp
				+ ", reopenerBrowser=" + reopenerBrowser + ", reopenerHostname=" + reopenerHostname + ", dateCompleted="
				+ dateCompleted + ", holdStart=" + holdStart + ", holdEnd=" + holdEnd + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(branchId, closerBrowser, closerEmail, closerHostname, closerIp, dateCompleted, dateLogged,
				holdEnd, holdStart, id, loggerBrowser, loggerEmail, loggerHostname, loggerIp, messageId,
				reopenerBrowser, reopenerEmail, reopenerHostname, reopenerIp, startingDate, statusId, tId, vendorId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoggedCall other = (LoggedCall) obj;
		return Objects.equals(branchId, other.branchId) && Objects.equals(closerBrowser, other.closerBrowser)
				&& Objects.equals(closerEmail, other.closerEmail)
				&& Objects.equals(closerHostname, other.closerHostname) && Objects.equals(closerIp, other.closerIp)
				&& Objects.equals(dateCompleted, other.dateCompleted) && Objects.equals(dateLogged, other.dateLogged)
				&& Objects.equals(holdEnd, other.holdEnd) && Objects.equals(holdStart, other.holdStart)
				&& Objects.equals(id, other.id) && Objects.equals(loggerBrowser, other.loggerBrowser)
				&& Objects.equals(loggerEmail, other.loggerEmail)
				&& Objects.equals(loggerHostname, other.loggerHostname) && Objects.equals(loggerIp, other.loggerIp)
				&& Objects.equals(messageId, other.messageId) && Objects.equals(reopenerBrowser, other.reopenerBrowser)
				&& Objects.equals(reopenerEmail, other.reopenerEmail)
				&& Objects.equals(reopenerHostname, other.reopenerHostname)
				&& Objects.equals(reopenerIp, other.reopenerIp) && Objects.equals(startingDate, other.startingDate)
				&& Objects.equals(statusId, other.statusId) && Objects.equals(tId, other.tId)
				&& Objects.equals(vendorId, other.vendorId);
	}

	

}
