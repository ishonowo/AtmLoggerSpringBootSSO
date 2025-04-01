package com.infinity.app.dto;

import java.util.Date;

import jakarta.validation.constraints.NotNull;

public class CallWithStatusDto {
	
	@NotNull
    private Long logId;
	
	@NotNull
    private Long statusId;
	
	@NotNull
    private String statusDesc;
	
	@NotNull
    private Date dateCompleted;

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public Date getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(Date dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

	public CallWithStatusDto(@NotNull Long logId, @NotNull Long statusId, @NotNull String statusDesc,
			@NotNull Date dateCompleted) {
		this.logId = logId;
		this.statusId = statusId;
		this.statusDesc = statusDesc;
		this.dateCompleted = dateCompleted;
	}

	public CallWithStatusDto() {}
	
	

}
