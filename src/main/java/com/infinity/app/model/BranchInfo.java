package com.infinity.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.CrossOrigin;

@Entity
@Lazy(false)
@CrossOrigin(origins="http://localhost:4200")
public class BranchInfo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name = "branchInfo", schema="dbo", sequenceName = "sq_branchInfo", allocationSize = 1)
	private Long id;
	
	@NotNull
	@Size(min = 3, max = 3)
	private String solId;
	
	@NotNull
	@Email
	private String branchEmail;
	
	@NotNull
	private String physicalAddress;
	
	@NotNull
	private String branchName;
	
	@NotNull
	private String region;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSolId() {
		return solId;
	}

	public void setSolId(String solId) {
		this.solId = solId;
	}

	public String getBranchEmail() {
		return branchEmail;
	}

	public void setBranchEmail(String branchEmail) {
		this.branchEmail = branchEmail;
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

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return "BranchInfo [id=" + id + ", solId=" + solId + ", branchEmail=" + branchEmail + ", physicalAddress="
				+ physicalAddress + ", branchName=" + branchName + ", region=" + region + "]";
	}

	public BranchInfo(Long id, @NotNull @Size(min = 3, max = 3) String solId, @NotNull @Email String branchEmail,
			@NotNull String physicalAddress, @NotNull String branchName, @NotNull String region) {
		super();
		this.id = id;
		this.solId = solId;
		this.branchEmail = branchEmail;
		this.physicalAddress = physicalAddress;
		this.branchName = branchName;
		this.region = region;
	}

	public BranchInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
