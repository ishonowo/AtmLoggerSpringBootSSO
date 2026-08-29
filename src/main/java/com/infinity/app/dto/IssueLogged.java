package com.infinity.app.dto;

import java.util.List;
import java.util.Objects;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class IssueLogged {

	@NotNull
	@Email
	private String userEmail;

	@NotNull
	@Email
	private String supportEmail;

	@NotNull
	@Size(min = 8, max = 8)
	private String terminalId;

	// ids of the AtmFault rows the user selected in the UI.
	@NotEmpty
	private List<Long> atmFaultIds;

	// Free-text used only when "Others" is among the selected faults. Not
	// @NotNull here since it's conditionally required - enforced in the
	// controller/service layer. Left null in the JSON when Others wasn't
	// selected, since the frontend omits the key entirely in that case.
	
	@Size(min=10)
	private String otherFaultDesc;

	@NotNull
	private String branchLogger;

	@NotNull
	@Email
	private String loggerEmail;

	@Size(min = 11, max = 14)
	@NotNull
	private String loggerPhoneNo;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getSupportEmail() {
		return supportEmail;
	}

	public void setSupportEmail(String supportEmail) {
		this.supportEmail = supportEmail;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public List<Long> getAtmFaultIds() {
		return atmFaultIds;
	}

	public void setAtmFaultIds(List<Long> atmFaultIds) {
		this.atmFaultIds = atmFaultIds;
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

	public IssueLogged() {
		super();
	}

	@Override
	public String toString() {
		return "IssueLogged [userEmail=" + userEmail + ", supportEmail=" + supportEmail + ", terminalId=" + terminalId
				+ ", atmFaultIds=" + atmFaultIds + ", otherFaultDesc=" + otherFaultDesc + ", branchLogger=" + branchLogger
				+ ", loggerEmail=" + loggerEmail + ", loggerPhoneNo=" + loggerPhoneNo + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(atmFaultIds, branchLogger, loggerEmail, loggerPhoneNo, otherFaultDesc, supportEmail,
				terminalId, userEmail);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IssueLogged other = (IssueLogged) obj;
		return Objects.equals(atmFaultIds, other.atmFaultIds) && Objects.equals(branchLogger, other.branchLogger)
				&& Objects.equals(loggerEmail, other.loggerEmail) && Objects.equals(loggerPhoneNo, other.loggerPhoneNo)
				&& Objects.equals(otherFaultDesc, other.otherFaultDesc)
				&& Objects.equals(supportEmail, other.supportEmail) && Objects.equals(terminalId, other.terminalId)
				&& Objects.equals(userEmail, other.userEmail);
	}

}