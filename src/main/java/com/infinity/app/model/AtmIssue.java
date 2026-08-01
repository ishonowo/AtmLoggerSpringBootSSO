package com.infinity.app.model;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.CrossOrigin;

//@Bean
@CrossOrigin(origins="http://localhost:4200")
@Entity
@Lazy(false)
public class AtmIssue {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	//@Email
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
	
	@NotNull
	@Email
	private String userEmail;
	
/*	private String ip;
	
	private String browser;
	
	private String hostname;
*/
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

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

/*	public String getIp() {
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
	}*/

	public AtmIssue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AtmIssue(Long id, @NotNull @Size(min = 8, max = 8) String terminalId,
			@NotNull @Size(min = 10) String issueDesc, @NotNull String branchLogger, @NotNull @Email String loggerEmail,
			@Size(min = 11, max = 14) @NotNull String loggerPhoneNo, @NotNull Date logDate,
			@NotNull String supportEmail, @NotNull String contact, @Email @NotNull String branchEmail,
			@NotNull String branchName, @NotNull String atmName, String physicalAddress, @NotNull String vendorName,
			@NotNull @Email String userEmail) {

//			, String ip, String browser, String hostname) {
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
		this.userEmail = userEmail;
		/*this.ip = ip;
		this.browser = browser;
		this.hostname = hostname;*/
	}

	public AtmIssue(@NotNull @Size(min = 8, max = 8) String terminalId, @NotNull @Size(min = 10) String issueDesc,
			@NotNull String branchLogger, @NotNull @Email String loggerEmail,
			@Size(min = 11, max = 14) @NotNull String loggerPhoneNo, @NotNull Date logDate,
			@NotNull String supportEmail, @NotNull String contact, @Email @NotNull String branchEmail,
			@NotNull String branchName, @NotNull String atmName, String physicalAddress, @NotNull String vendorName,
			@NotNull @Email String userEmail) {
			//, String ip, String browser, String hostname) {
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
		this.userEmail = userEmail;
		/*this.ip = ip;
		this.browser = browser;
		this.hostname = hostname;*/
	}

	@Override
	public String toString() {
		return "AtmIssue [id=" + id + ", terminalId=" + terminalId + ", issueDesc=" + issueDesc + ", branchLogger="
				+ branchLogger + ", loggerEmail=" + loggerEmail + ", loggerPhoneNo=" + loggerPhoneNo + ", logDate="
				+ logDate + ", supportEmail=" + supportEmail + ", contact=" + contact + ", branchEmail=" + branchEmail
				+ ", branchName=" + branchName + ", atmName=" + atmName + ", physicalAddress=" + physicalAddress
				+ ", vendorName=" + vendorName + ", userEmail=" + userEmail +"]";/*+ ", ip=" + ip + ", browser=" + browser
				+ ", hostname=" + hostname + "]"*/
	}

	@Override
	public int hashCode() {
		return Objects.hash(atmName, branchEmail, branchLogger, branchName, contact, id, issueDesc, logDate,
				loggerEmail, loggerPhoneNo, physicalAddress, supportEmail, terminalId,  vendorName,userEmail);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtmIssue other = (AtmIssue) obj;
		return Objects.equals(atmName, other.atmName) && Objects.equals(branchEmail, other.branchEmail)
				&& Objects.equals(branchLogger, other.branchLogger) && Objects.equals(branchName, other.branchName)
				&& Objects.equals(contact, other.contact) && Objects.equals(id, other.id)
				&& Objects.equals(issueDesc, other.issueDesc) && Objects.equals(logDate, other.logDate)
				&& Objects.equals(loggerEmail, other.loggerEmail) && Objects.equals(loggerPhoneNo, other.loggerPhoneNo)
				&& Objects.equals(physicalAddress, other.physicalAddress)
				&& Objects.equals(supportEmail, other.supportEmail) && Objects.equals(terminalId, other.terminalId)
				&& Objects.equals(vendorName, other.vendorName) && Objects.equals(userEmail, other.userEmail) ;
	}

/*	@Override
	public int hashCode() {
		return Objects.hash(atmName, branchEmail, branchLogger, branchName, browser, contact, hostname, id, ip,
				issueDesc, logDate, loggerEmail, loggerPhoneNo, physicalAddress, supportEmail, terminalId, userEmail,
				vendorName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtmIssue other = (AtmIssue) obj;
		return Objects.equals(atmName, other.atmName) && Objects.equals(branchEmail, other.branchEmail)
				&& Objects.equals(branchLogger, other.branchLogger) && Objects.equals(branchName, other.branchName)
				&& Objects.equals(browser, other.browser) && Objects.equals(contact, other.contact)
				&& Objects.equals(hostname, other.hostname) && Objects.equals(id, other.id)
				&& Objects.equals(ip, other.ip) && Objects.equals(issueDesc, other.issueDesc)
				&& Objects.equals(logDate, other.logDate) && Objects.equals(loggerEmail, other.loggerEmail)
				&& Objects.equals(loggerPhoneNo, other.loggerPhoneNo)
				&& Objects.equals(physicalAddress, other.physicalAddress)
				&& Objects.equals(supportEmail, other.supportEmail) && Objects.equals(terminalId, other.terminalId)
				&& Objects.equals(userEmail, other.userEmail) && Objects.equals(vendorName, other.vendorName);
	}
	*/


}



