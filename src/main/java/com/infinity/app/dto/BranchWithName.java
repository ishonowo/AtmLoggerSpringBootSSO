package com.infinity.app.dto;

public class BranchWithName {
	
	private Long id;

	private Long regionId;

	private String regionName;
	
	private String branchEmail;
	
	private String branchName;
	
	private String physicalAddress;
	
	private String solId;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

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

	public BranchWithName(Long id, Long regionId, String regionName, String branchEmail, String branchName,
			String physicalAddress, String solId) {
		this.id = id;
		this.regionId = regionId;
		this.regionName = regionName;
		this.branchEmail = branchEmail;
		this.branchName = branchName;
		this.physicalAddress = physicalAddress;
		this.solId = solId;
	}

	public BranchWithName(Long regionId, String regionName, String branchEmail, String branchName,
			String physicalAddress, String solId) {
		super();
		this.regionId = regionId;
		this.regionName = regionName;
		this.branchEmail = branchEmail;
		this.branchName = branchName;
		this.physicalAddress = physicalAddress;
		this.solId = solId;
	}

	public BranchWithName() {}

	@Override
	public String toString() {
		return "BranchWithNames [id=" + id + ", regionId=" + regionId + ", regionName=" + regionName + ", branchEmail="
				+ branchEmail + ", branchName=" + branchName + ", physicalAddress=" + physicalAddress + ", solId="
				+ solId + "]";
	}


}
