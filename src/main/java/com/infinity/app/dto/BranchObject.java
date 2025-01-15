package com.infinity.app.dto;

public class BranchObject {

	private String regionName;
	
	private String branchEmail;
	
	private String branchName;
	
	private String physicalAddress;
	
	private String solId;

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
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

	public String getPhysicalAddress() {
		return physicalAddress;
	}

	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}

	public String getSolId() {
		return solId;
	}

	public void setSolId(String solId) {
		this.solId = solId;
	}

	public BranchObject(String regionName, String branchEmail, String branchName, String physicalAddress,
			String solId) {
		this.regionName = regionName;
		this.branchEmail = branchEmail;
		this.branchName = branchName;
		this.physicalAddress = physicalAddress;
		this.solId = solId;
	}
	
	public BranchObject(){}

	@Override
	public String toString() {
		return "BranchObject [regionName=" + regionName + ", branchEmail=" + branchEmail + ", branchName=" + branchName
				+ ", physicalAddress=" + physicalAddress + ", solId=" + solId + "]";
	}


}
