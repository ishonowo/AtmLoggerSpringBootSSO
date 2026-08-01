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
public class AtmDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name = "atmDetail", schema="dbo", sequenceName = "sq_atmDetail", allocationSize = 1)
	private Long id;
	
	@Email
	@NotNull
	private String branchEmail;
	
	@NotNull
	private String branchName;
	
	@NotNull
	@Size(min=8, max=8)
	private String terminalId;
	
	@NotNull
	private String atmName;
	
	private String physicalAddress;
	
	/*@NotNull
	private String contact;*/
	
	@NotNull
	private String vendorName;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
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
	
	/*public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}*/
	
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	public AtmDetail(String branchEmail, String branchName, String terminalId, String atmName,
			String physicalAddress, //String contact,
			String vendorName) {
		this.branchEmail = branchEmail;
		this.branchName = branchName;
		this.terminalId = terminalId;
		this.atmName = atmName;
		this.physicalAddress = physicalAddress;
		//this.contact = contact;
		this.vendorName = vendorName;
	}
	
	public AtmDetail(Long id, 
			String branchEmail, String branchName, String terminalId, String atmName,
			String physicalAddress, //String contact,
			String vendorName) {
		//super();
		this.id= id;
		this.branchEmail = branchEmail;
		this.branchName = branchName;
		this.terminalId = terminalId;
		this.atmName = atmName;
		this.physicalAddress = physicalAddress;
		//this.contact = contact;
		this.vendorName = vendorName;
	}
	
	public AtmDetail() {}
	
	@Override
	public String toString() {
		return "AtmDetail ["
				+ " id" + id + ", "
				+ "branchEmail=" + branchEmail + ", branchName=" + branchName + ", terminalId=" + terminalId
				+ ", atmName=" + atmName + ", physicalAddress=" + physicalAddress //+ ", contact=" + contact
				+ ", vendorName=" + vendorName + "]";
	}
	
}


