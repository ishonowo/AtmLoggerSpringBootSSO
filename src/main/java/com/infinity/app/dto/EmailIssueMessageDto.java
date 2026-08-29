package com.infinity.app.dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.infinity.app.model.AtmFault;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
    private String physicalAddress;
    
    @NotNull
    private String branchName;
    
    @NotNull
    private String vendorName;
    
    @NotEmpty
    private List<AtmFault> atmFaults;
    
    private String otherFaultDesc;
    
    @NotNull
    private String branchLogger;
    
    @NotNull
    private String loggerPhone;
    
    @NotNull
    private Date dateLogged;
    
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

	public String getPhysicalAddress() {
		return physicalAddress;
	}

	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
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

	public List<AtmFault> getAtmFaults() {
		return atmFaults;
	}

	public void setAtmFaults(List<AtmFault> atmFaults) {
		this.atmFaults = atmFaults;
	}

	public String getOtherFaultDesc() {
		return otherFaultDesc;
	}

	public void setOtherFaultDesc(String otherFaultDesc) {
		this.otherFaultDesc = otherFaultDesc;
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

	public Date getDateLogged() {
		return dateLogged;
	}

	public void setDateLogged(Date dateLogged) {
		this.dateLogged = dateLogged;
	}

	public String getmEnd() {
		return mEnd;
	}

	public void setmEnd(String mEnd) {
		this.mEnd = mEnd;
	}

	public EmailIssueMessageDto(@NotNull @Email String fromEmail, @NotNull String toEmail, @NotNull String cc,
			@NotNull @Size(min = 10) String subject, @NotNull String mIntro, @NotNull String physicalAddress,
			@NotNull String branchName, @NotNull String vendorName, @NotEmpty List<AtmFault> atmFaults,
			String otherFaultDesc, @NotNull String branchLogger, @NotNull String loggerPhone, @NotNull Date dateLogged,
			@NotNull String mEnd) {
		super();
		this.fromEmail = fromEmail;
		this.toEmail = toEmail;
		this.cc = cc;
		this.subject = subject;
		this.mIntro = mIntro;
		this.physicalAddress = physicalAddress;
		this.branchName = branchName;
		this.vendorName = vendorName;
		this.atmFaults = atmFaults;
		this.otherFaultDesc = otherFaultDesc;
		this.branchLogger = branchLogger;
		this.loggerPhone = loggerPhone;
		this.dateLogged = dateLogged;
		this.mEnd = mEnd;
	}

	public EmailIssueMessageDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "EmailIssueMessageDto [fromEmail=" + fromEmail + ", toEmail=" + toEmail + ", cc=" + cc + ", subject="
				+ subject + ", mIntro=" + mIntro + ", physicalAddress=" + physicalAddress + ", branchName=" + branchName
				+ ", vendorName=" + vendorName + ", atmFaults=" + atmFaults + ", otherFaultDesc=" + otherFaultDesc
				+ ", branchLogger=" + branchLogger + ", loggerPhone=" + loggerPhone + ", dateLogged=" + dateLogged
				+ ", mEnd=" + mEnd + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(atmFaults, branchLogger, branchName, cc, dateLogged, fromEmail, loggerPhone, mEnd, mIntro,
				otherFaultDesc, physicalAddress, subject, toEmail, vendorName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailIssueMessageDto other = (EmailIssueMessageDto) obj;
		return Objects.equals(atmFaults, other.atmFaults) && Objects.equals(branchLogger, other.branchLogger)
				&& Objects.equals(branchName, other.branchName) && Objects.equals(cc, other.cc)
				&& Objects.equals(dateLogged, other.dateLogged) && Objects.equals(fromEmail, other.fromEmail)
				&& Objects.equals(loggerPhone, other.loggerPhone) && Objects.equals(mEnd, other.mEnd)
				&& Objects.equals(mIntro, other.mIntro) && Objects.equals(otherFaultDesc, other.otherFaultDesc)
				&& Objects.equals(physicalAddress, other.physicalAddress) && Objects.equals(subject, other.subject)
				&& Objects.equals(toEmail, other.toEmail) && Objects.equals(vendorName, other.vendorName);
	}


    
}
