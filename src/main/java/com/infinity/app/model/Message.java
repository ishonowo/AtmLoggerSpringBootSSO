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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String physicalAddress;

    @NotNull
    private String branchName;

    @NotNull
    private String vendorName;

    // A message can now reference several faults from the atm_faultes master
    // list, instead of a single one. AtmFault rows are reference data, so we
    // don't cascade persist/remove them here - only the association itself
    // is owned by Message.
    @NotEmpty
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    @JoinTable(
        name = "message_atm_fault",
        joinColumns = @JoinColumn(name = "message_id"),
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
    private String loggerPhone;

    @NotNull
    private Date dateLogged;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Message(Long id, @NotNull String physicalAddress, @NotNull String branchName, @NotNull String vendorName,
			@NotEmpty List<AtmFault> atmFaults, String otherFaultDesc, @NotNull String branchLogger,
			@NotNull String loggerPhone, @NotNull Date dateLogged) {
		super();
		this.id = id;
		this.physicalAddress = physicalAddress;
		this.branchName = branchName;
		this.vendorName = vendorName;
		this.atmFaults = atmFaults;
		this.otherFaultDesc = otherFaultDesc;
		this.branchLogger = branchLogger;
		this.loggerPhone = loggerPhone;
		this.dateLogged = dateLogged;
	}

	public Message(@NotNull String physicalAddress, @NotNull String branchName, @NotNull String vendorName,
			@NotEmpty List<AtmFault> atmFaults, String otherFaultDesc, @NotNull String branchLogger,
			@NotNull String loggerPhone, @NotNull Date dateLogged) {
		super();
		this.physicalAddress = physicalAddress;
		this.branchName = branchName;
		this.vendorName = vendorName;
		this.atmFaults = atmFaults;
		this.otherFaultDesc = otherFaultDesc;
		this.branchLogger = branchLogger;
		this.loggerPhone = loggerPhone;
		this.dateLogged = dateLogged;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", physicalAddress=" + physicalAddress + ", branchName=" + branchName
				+ ", vendorName=" + vendorName + ", atmFaults=" + atmFaults + ", otherFaultDesc=" + otherFaultDesc
				+ ", branchLogger=" + branchLogger + ", loggerPhone=" + loggerPhone + ", dateLogged=" + dateLogged
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(atmFaults, branchLogger, branchName, dateLogged, id, loggerPhone, otherFaultDesc,
				physicalAddress, vendorName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		return Objects.equals(atmFaults, other.atmFaults) && Objects.equals(branchLogger, other.branchLogger)
				&& Objects.equals(branchName, other.branchName) && Objects.equals(dateLogged, other.dateLogged)
				&& Objects.equals(id, other.id) && Objects.equals(loggerPhone, other.loggerPhone)
				&& Objects.equals(otherFaultDesc, other.otherFaultDesc)
				&& Objects.equals(physicalAddress, other.physicalAddress)
				&& Objects.equals(vendorName, other.vendorName);
	}
    
    

}