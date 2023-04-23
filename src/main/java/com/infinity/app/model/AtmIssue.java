package com.infinity.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.CrossOrigin;

//@Bean
@CrossOrigin(origins="http://localhost:4200")
@Entity
@Lazy(false)
public class AtmIssue {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name = "atmIssues", schema="dbo", sequenceName = "sq_atmIssues", allocationSize = 1)
	private Long id;
	
	@NotNull
	@Size(min = 8, max = 8)
	private String terminalId;
	
	@NotNull
	@Size(min = 10)
	private String issueDesc;
	
	@NotNull
	private String branchLogger;
	
	@NotNull
	@Email
	private String loggerEmail;
	
	@Size(min = 11, max = 14)
	@NotNull
	private String loggerPhoneNo;
	
	@NotNull
	private Date logDate;
	
	@NotNull
	@Email
	@Value("${spring.support.email}")
	private String supportEmail;
	
	@NotNull
	private String contact;
	
	@Email
	@NotNull
	private String branchEmail;
	
	@NotNull
	private String branchName;
	
	@NotNull
	private String atmName;
	private String physicalAddress;
	
	@NotNull
	private String vendorName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
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
	public String getLoggerEmail() {
		return loggerEmail;
	}
	public void setLoggerEmail(String loggerEmail) {
		this.loggerEmail = loggerEmail;
	}
	public String getLoggerPhoneNo() {
		return loggerPhoneNo;
	}
	public void setLoggerPhoneNo(String loggerPhoneNo) {
		this.loggerPhoneNo = loggerPhoneNo;
	}
	public Date getLogDate() {
		return logDate;
	}
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}
	public String getSupportEmail() {
		return supportEmail;
	}
	public void setSupportEmail(String supportEmail) {
		this.supportEmail = supportEmail;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getBranchEmail() {
		return branchEmail;
	}
	public void setBranchEmail(String branchEmail) {
		this.branchEmail = branchEmail;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getAtmName() {
		return atmName;
	}
	public void setAtmName(String atmName) {
		this.atmName = atmName;
	}
	public String getPhysicalAddress() {
		return physicalAddress;
	}
	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendor(String vendorName) {
		this.vendorName = vendorName;
	}
	
	public AtmIssue(String terminalId, String issueDesc, String branchLogger, @Email String loggerEmail,
			String loggerPhoneNo, Date logDate, @Email String supportEmail, String contact, @Email String branchEmail,
			String branchName, String atmName, String physicalAddress, String vendorName) {
		super();
		this.terminalId = terminalId;
		this.issueDesc = issueDesc;
		this.branchLogger = branchLogger;
		this.loggerEmail = loggerEmail;
		this.loggerPhoneNo = loggerPhoneNo;
		this.logDate = logDate;
		this.supportEmail = supportEmail;
		this.contact = contact;
		this.branchEmail = branchEmail;
		this.branchName = branchName;
		this.atmName = atmName;
		this.physicalAddress = physicalAddress;
		this.vendorName = vendorName;
	}
	
	public AtmIssue() {
		super();
	}
	
	@Override
	public String toString() {
		return "AtmIssue [id=" + id + ", terminalId=" + terminalId + ", issueDesc=" + issueDesc + ", branchLogger="
				+ branchLogger + ", loggerEmail=" + loggerEmail + ", loggerPhoneNo=" + loggerPhoneNo + ", logDate="
				+ logDate + ", supportEmail=" + supportEmail + ", contact=" + contact + ", branchEmail=" + branchEmail
				+ ", branchName=" + branchName + ", atmName=" + atmName + ", physicalAddress=" + physicalAddress
				+ ", vendorName=" + vendorName + "]";
	}
	
	public AtmIssue(Long id, String terminalId, String issueDesc, String branchLogger, @Email String loggerEmail,
			String loggerPhoneNo, Date logDate, @Email String supportEmail, String contact, @Email String branchEmail,
			String branchName, String atmName, String physicalAddress, String vendorName) {
		super();
		this.id = id;
		this.terminalId = terminalId;
		this.issueDesc = issueDesc;
		this.branchLogger = branchLogger;
		this.loggerEmail = loggerEmail;
		this.loggerPhoneNo = loggerPhoneNo;
		this.logDate = logDate;
		this.supportEmail = supportEmail;
		this.contact = contact;
		this.branchEmail = branchEmail;
		this.branchName = branchName;
		this.atmName = atmName;
		this.physicalAddress = physicalAddress;
		this.vendorName = vendorName;
	}
	
	


}



