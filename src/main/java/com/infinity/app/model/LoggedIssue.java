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
@Table(name="logged_issues")
public class LoggedIssue {
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
	private Date startingDate;
	
	@NotNull
	private Date dateCompleted;
	
	@NotNull
	private Long statusId;

	public LoggedIssue(Long id, @NotNull Long branchId, @NotNull Long tId, @NotNull Long vendorId,
			@NotNull Long messageId, @NotNull Date startingDate, @NotNull Date dateCompleted, @NotNull Long statusId) {
		this.id = id;
		this.branchId = branchId;
		this.tId = tId;
		this.vendorId = vendorId;
		this.messageId = messageId;
		this.startingDate = startingDate;
		this.dateCompleted = dateCompleted;
		this.statusId = statusId;
	}

	public LoggedIssue(@NotNull Long branchId, @NotNull Long tId, @NotNull Long vendorId, @NotNull Long messageId,
			@NotNull Date startingDate, @NotNull Date dateCompleted, @NotNull Long statusId) {
		this.branchId = branchId;
		this.tId = tId;
		this.vendorId = vendorId;
		this.messageId = messageId;
		this.startingDate = startingDate;
		this.dateCompleted = dateCompleted;
		this.statusId = statusId;
	}

	public LoggedIssue() {}

	@Override
	public String toString() {
		return "LoggedIssue [id=" + id + ", branchId=" + branchId + ", tId=" + tId + ", vendorId=" + vendorId
				+ ", messageId=" + messageId + ", startingDate=" + startingDate + ", dateCompleted=" + dateCompleted
				+ ", statusId=" + statusId + "]";
	}
	
}
