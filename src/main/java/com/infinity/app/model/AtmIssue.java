package com.infinity.app.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Lazy;

@Entity
@Lazy(false)
public class AtmIssue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 8, max = 8)
	private String terminalId;

	// AtmIssue gets its OWN join table here - it must not reuse Message's
	// "message_atm_fault" table, since that table's join column is a foreign
	// key against dbo.message(id), not dbo.atm_issue(id). Reusing it causes
	// an FK violation when Hibernate tries to insert an AtmIssue id into a
	// column that's constrained to reference Message rows.
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	@JoinTable(
		name = "atm_issue_atm_fault",
		joinColumns = @JoinColumn(name = "atm_issue_id"),
		inverseJoinColumns = @JoinColumn(name = "atm_fault_id")
	)
	private List<AtmFault> atmFaults = new ArrayList<>();

	// Free-text used only when "Others" is among the selected faults.
	// Not @NotNull at the entity level since it's conditionally required -
	// that check is enforced in the controller/service layer.
	
	@Size(min=5)
	private String otherFaultDesc;

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

	public AtmIssue() {
		super();
	}

	public AtmIssue(Long id, @NotNull @Size(min = 8, max = 8) String terminalId, @NotEmpty List<AtmFault> atmFaults,
			String otherFaultDesc, @NotNull String branchLogger, @NotNull @Email String loggerEmail,
			@Size(min = 11, max = 14) @NotNull String loggerPhoneNo, @NotNull Date logDate,
			@NotNull String supportEmail, @NotNull String contact, @Email @NotNull String branchEmail,
			@NotNull String branchName, @NotNull String atmName, String physicalAddress, @NotNull String vendorName,
			@NotNull @Email String userEmail) {
		super();
		this.id = id;
		this.terminalId = terminalId;
		this.atmFaults = atmFaults;
		this.otherFaultDesc = otherFaultDesc;
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
	}

	public AtmIssue(@NotNull @Size(min = 8, max = 8) String terminalId, @NotEmpty List<AtmFault> atmFaults,
			String otherFaultDesc, @NotNull String branchLogger, @NotNull @Email String loggerEmail,
			@Size(min = 11, max = 14) @NotNull String loggerPhoneNo, @NotNull Date logDate,
			@NotNull String supportEmail, @NotNull String contact, @Email @NotNull String branchEmail,
			@NotNull String branchName, @NotNull String atmName, String physicalAddress, @NotNull String vendorName,
			@NotNull @Email String userEmail) {
		super();
		this.terminalId = terminalId;
		this.atmFaults = atmFaults;
		this.otherFaultDesc = otherFaultDesc;
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
	}

	@Override
	public String toString() {
		return "AtmIssue [id=" + id + ", terminalId=" + terminalId + ", atmFaults=" + atmFaults + ", otherFaultDesc="
				+ otherFaultDesc + ", branchLogger=" + branchLogger + ", loggerEmail=" + loggerEmail
				+ ", loggerPhoneNo=" + loggerPhoneNo + ", logDate=" + logDate + ", supportEmail=" + supportEmail
				+ ", contact=" + contact + ", branchEmail=" + branchEmail + ", branchName=" + branchName + ", atmName="
				+ atmName + ", physicalAddress=" + physicalAddress + ", vendorName=" + vendorName + ", userEmail="
				+ userEmail + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(atmFaults, atmName, branchEmail, branchLogger, branchName, contact, id, logDate,
				loggerEmail, loggerPhoneNo, otherFaultDesc, physicalAddress, supportEmail, terminalId, userEmail,
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
		return Objects.equals(atmFaults, other.atmFaults) && Objects.equals(atmName, other.atmName)
				&& Objects.equals(branchEmail, other.branchEmail) && Objects.equals(branchLogger, other.branchLogger)
				&& Objects.equals(branchName, other.branchName) && Objects.equals(contact, other.contact)
				&& Objects.equals(id, other.id) && Objects.equals(logDate, other.logDate)
				&& Objects.equals(loggerEmail, other.loggerEmail) && Objects.equals(loggerPhoneNo, other.loggerPhoneNo)
				&& Objects.equals(otherFaultDesc, other.otherFaultDesc)
				&& Objects.equals(physicalAddress, other.physicalAddress)
				&& Objects.equals(supportEmail, other.supportEmail) && Objects.equals(terminalId, other.terminalId)
				&& Objects.equals(userEmail, other.userEmail) && Objects.equals(vendorName, other.vendorName);
	}

}
