package com.infinity.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.CrossOrigin;

@Entity
@Lazy(false)
//@CrossOrigin(origins="http://localhost:4200")
@Table(name="branch_info")
public class BranchInfo {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@SequenceGenerator(name = "branchInfo", schema="dbo", sequenceName = "sq_branchInfo", allocationSize = 1)
	private Long id;
	
	@NotNull
	@Size(min = 3, max = 3)
	@Column(name="sol_id")
	private String solId;
	
	@NotNull
	@Email
	@Column(name="branch_email")
	private String branchEmail;
	
	@NotNull
	@Column(name="physical_address")
	private String physicalAddress;
	
	@NotNull
	@Column(name="branch_name")
	private String branchName;
	
	@NotNull
	@Column(name="region_id")
	private Long regionId;

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
		this.branchEmail = branchEmail.toLowerCase();
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
		this.branchName = branchName.toUpperCase();
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	@Override
	public String toString() {
		return "BranchInfo [id=" + id + ", solId=" + solId + ", branchEmail=" + branchEmail + ", physicalAddress="
				+ physicalAddress + ", branchName=" + branchName + ", regionId=" + regionId + "]";
	}

	public BranchInfo(Long id, @NotNull @Size(min = 3, max = 3) String solId, @NotNull @Email String branchEmail,
			@NotNull String physicalAddress, @NotNull String branchName, @NotNull Long regionId) {
		this.id = id;
		this.solId = solId;
		this.branchEmail = branchEmail.toLowerCase();
		this.physicalAddress = physicalAddress;
		this.branchName = branchName.toUpperCase();
		this.regionId = regionId;
	}

	public BranchInfo(@NotNull @Size(min = 3, max = 3) String solId, @NotNull @Email String branchEmail,
			@NotNull String physicalAddress, @NotNull String branchName, @NotNull Long regionId) {
		this.solId = solId;
		this.branchEmail = branchEmail.toLowerCase();
		this.physicalAddress = physicalAddress;
		this.branchName = branchName.toUpperCase();
		this.regionId = regionId;
	}

	public BranchInfo(){}
	
	
}
