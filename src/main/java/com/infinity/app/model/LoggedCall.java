package com.infinity.app.model;

import java.util.Date;

import org.springframework.context.annotation.Lazy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	
	private Date dateCompleted;
	
	@NotNull
	private Long statusId;

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

	public LoggedCall(Long id, @NotNull Long branchId, @NotNull Long tId, @NotNull Long vendorId,
			@NotNull Long messageId, @NotNull Date dateLogged, @NotNull Date startingDate, Date dateCompleted,
			@NotNull Long statusId) {
		this.id = id;
		this.branchId = branchId;
		this.tId = tId;
		this.vendorId = vendorId;
		this.messageId = messageId;
		this.dateLogged = dateLogged;
		this.startingDate = startingDate;
		this.dateCompleted = dateCompleted;
		this.statusId = statusId;
	}

	public LoggedCall(@NotNull Long branchId, @NotNull Long tId, @NotNull Long vendorId, @NotNull Long messageId,
			@NotNull Date dateLogged, @NotNull Date startingDate, Date dateCompleted, @NotNull Long statusId) {
		super();
		this.branchId = branchId;
		this.tId = tId;
		this.vendorId = vendorId;
		this.messageId = messageId;
		this.dateLogged = dateLogged;
		this.startingDate = startingDate;
		this.dateCompleted = dateCompleted;
		this.statusId = statusId;
	}

	public LoggedCall() {}

	@Override
	public String toString() {
		return "LoggedCall [id=" + id + ", branchId=" + branchId + ", tId=" + tId + ", vendorId=" + vendorId
				+ ", messageId=" + messageId + ", dateLogged=" + dateLogged + ", startingDate=" + startingDate
				+ ", dateCompleted=" + dateCompleted + ", statusId=" + statusId + "]";
	}

	
}
