package com.infinity.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EmailIssueMessageDto {
	@NotNull
    @Email
    private String fromEmail;
    
    @NotNull
    private String toEmail;
    
    @NotNull
    private String cc;
    
    @NotNull
    @Size(min = 10)
    private String subject;
    
    @NotNull
    private String mIntro;
    
    @NotNull
    private String atmLocation;
    
    @NotNull
    private String branchName;
    
    @NotNull
    private String vendorName;
    
    @NotNull
    private String issueDesc;
    
    @NotNull
    private String branchLogger;
    
    @NotNull
    private String loggerPhone;
    
    @NotNull
    private String dateLogged;
    
    @NotNull
    private String mEnd;

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getmIntro() {
		return mIntro;
	}

	public void setmIntro(String mIntro) {
		this.mIntro = mIntro;
	}

	public String getAtmLocation() {
		return atmLocation;
	}

	public void setAtmLocation(String atmLocation) {
		this.atmLocation = atmLocation;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
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

	public String getDateLogged() {
		return dateLogged;
	}

	public void setDateLogged(String dateLogged) {
		this.dateLogged = dateLogged;
	}

	public String getmEnd() {
		return mEnd;
	}

	public void setmEnd(String mEnd) {
		this.mEnd = mEnd;
	}

	public EmailIssueMessageDto() {}

	public EmailIssueMessageDto(@NotNull @Email String fromEmail, @NotNull String toEmail, @NotNull String cc,
			@NotNull @Size(min = 10) String subject, @NotNull String mIntro, @NotNull String atmLocation,
			@NotNull String branchName, @NotNull String vendorName, @NotNull String issueDesc,
			@NotNull String branchLogger, @NotNull String loggerPhone, @NotNull String dateLogged,
			@NotNull String mEnd) {
		this.fromEmail = fromEmail;
		this.toEmail = toEmail;
		this.cc = cc;
		this.subject = subject;
		this.mIntro = mIntro;
		this.atmLocation = atmLocation;
		this.branchName = branchName;
		this.vendorName = vendorName;
		this.issueDesc = issueDesc;
		this.branchLogger = branchLogger;
		this.loggerPhone = loggerPhone;
		this.dateLogged = dateLogged;
		this.mEnd = mEnd;
	}

	@Override
	public String toString() {
		return "EmailIssueMessageDto [fromEmail=" + fromEmail + ", toEmail=" + toEmail + ", cc=" + cc + ", subject="
				+ subject + ", mIntro=" + mIntro + ", atmLocation=" + atmLocation + ", branchName=" + branchName
				+ ", vendorName=" + vendorName + ", issueDesc=" + issueDesc + ", branchLogger=" + branchLogger
				+ ", loggerPhone=" + loggerPhone + ", dateLogged=" + dateLogged + ", mEnd=" + mEnd + "]";
	}

    
}
