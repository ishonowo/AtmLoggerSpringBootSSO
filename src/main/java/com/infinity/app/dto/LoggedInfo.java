package com.infinity.app.dto;

import java.util.Date;

import jakarta.validation.constraints.NotNull;

public class LoggedInfo {

	@NotNull
	private String branchName;
	
	@NotNull
	private String atmLocation;
	
	@NotNull
	private String vendorName;
	
	@NotNull
	private Long messageId;
	
	@NotNull
	private Date dateLogged;
	
	private Date startingDate;
	
	private Date dateCompleted;
	
	@NotNull
	private Long statusId;

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getAtmLocation() {
		return atmLocation;
	}

	public void setAtmLocation(String atmLocation) {
		this.atmLocation = atmLocation;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
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

	public Date getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(Date dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public LoggedInfo(@NotNull String branchName, @NotNull String atmLocation, @NotNull String vendorName,
			@NotNull Long messageId, @NotNull Date dateLogged, Date startingDate, Date dateCompleted,
			@NotNull Long statusId) {
		this.branchName = branchName;
		this.atmLocation = atmLocation;
		this.vendorName = vendorName;
		this.messageId = messageId;
		this.dateLogged = dateLogged;
		this.startingDate = startingDate;
		this.dateCompleted = dateCompleted;
		this.statusId = statusId;
	}

	public LoggedInfo() {}

	@Override
	public String toString() {
		return "LoggedInfo [branchName=" + branchName + ", atmLocation=" + atmLocation + ", vendorName=" + vendorName
				+ ", messageId=" + messageId + ", dateLogged=" + dateLogged + ", startingDate=" + startingDate
				+ ", dateCompleted=" + dateCompleted + ", statusId=" + statusId + "]";
	}
	
	
}
