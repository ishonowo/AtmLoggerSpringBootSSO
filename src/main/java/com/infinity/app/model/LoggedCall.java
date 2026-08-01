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
	private String fromEmail;
	
	private String ip;
	
	private String browser;
	
	private String hostname;
	
	private Date dateCompleted;

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

	public void setStatus_id(Long statusId) {
		this.statusId = statusId;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public Date getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(Date dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

	public LoggedCall(Long id, @NotNull Long branchId, @NotNull Long tId, @NotNull Long vendorId,
			@NotNull Long messageId, @NotNull Date dateLogged, @NotNull Date startingDate, @NotNull Long statusId,
			@Email String fromEmail, String ip, String browser, String hostname, Date dateCompleted) {
		super();
		this.id = id;
		this.branchId = branchId;
		this.tId = tId;
		this.vendorId = vendorId;
		this.messageId = messageId;
		this.dateLogged = dateLogged;
		this.startingDate = startingDate;
		this.statusId = statusId;
		this.fromEmail = fromEmail;
		this.ip = ip;
		this.browser = browser;
		this.hostname = hostname;
		this.dateCompleted = dateCompleted;
	}

	public LoggedCall() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoggedCall(@NotNull Long branchId, @NotNull Long tId, @NotNull Long vendorId, @NotNull Long messageId,
			@NotNull Date dateLogged, @NotNull Date startingDate, @NotNull Long statusId, @Email String fromEmail,
			String ip, String browser, String hostname, Date dateCompleted) {
		super();
		this.branchId = branchId;
		this.tId = tId;
		this.vendorId = vendorId;
		this.messageId = messageId;
		this.dateLogged = dateLogged;
		this.startingDate = startingDate;
		this.statusId = statusId;
		this.fromEmail = fromEmail;
		this.ip = ip;
		this.browser = browser;
		this.hostname = hostname;
		this.dateCompleted = dateCompleted;
	}

	@Override
	public String toString() {
		return "LoggedCall [id=" + id + ", branchId=" + branchId + ", tId=" + tId + ", vendorId=" + vendorId
				+ ", messageId=" + messageId + ", dateLogged=" + dateLogged + ", startingDate=" + startingDate
				+ ", statusId=" + statusId + ", fromEmail=" + fromEmail + ", ip=" + ip + ", browser=" + browser
				+ ", hostname=" + hostname + ", dateCompleted=" + dateCompleted + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(branchId, browser, dateCompleted, dateLogged, fromEmail, hostname, id, ip, messageId,
				startingDate, statusId, tId, vendorId);
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
		return Objects.equals(branchId, other.branchId) && Objects.equals(browser, other.browser)
				&& Objects.equals(dateCompleted, other.dateCompleted) && Objects.equals(dateLogged, other.dateLogged)
				&& Objects.equals(fromEmail, other.fromEmail) && Objects.equals(hostname, other.hostname)
				&& Objects.equals(id, other.id) && Objects.equals(ip, other.ip)
				&& Objects.equals(messageId, other.messageId) && Objects.equals(startingDate, other.startingDate)
				&& Objects.equals(statusId, other.statusId) && Objects.equals(tId, other.tId)
				&& Objects.equals(vendorId, other.vendorId);
	}

	
}
