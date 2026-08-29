package com.infinity.app.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "atm_faults", schema = "dbo")
public class AtmFault {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name = "atmFaults", schema="dbo", sequenceName = "sq_atmFaults", allocationSize = 1)
	private Long id;
	
	@Column(name = "nature_of_fault", nullable = false)
	private String natureOfFault;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "fault_type", nullable = false)
	private String faultType;
	
//	private String otherFaultDesc;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNatureOfFault() {
		return natureOfFault;
	}

	public void setNatureOfFault(String natureOfFault) {
		this.natureOfFault = natureOfFault;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFaultType() {
		return faultType;
	}

	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}

/*	public String getOtherFaultDesc() {
		return otherFaultDesc;
	}

	public void setOtherFaultDesc(String otherFaultDesc) {
		this.otherFaultDesc = otherFaultDesc;
	}
*/
	public AtmFault() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AtmFault(Long id, String natureOfFault, String description, String faultType) {//, String otherFaultDesc) {
		super();
		this.id = id;
		this.natureOfFault = natureOfFault;
		this.description = description;
		this.faultType = faultType;
		//this.otherFaultDesc = otherFaultDesc;
	}

	public AtmFault(String natureOfFault, String description, String faultType) {//, String otherFaultDesc) {
		super();
		this.natureOfFault = natureOfFault;
		this.description = description;
		this.faultType = faultType;
		//this.otherFaultDesc = otherFaultDesc;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, faultType, id, natureOfFault);//, otherFaultDesc);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtmFault other = (AtmFault) obj;
		return Objects.equals(description, other.description) && Objects.equals(faultType, other.faultType)
				&& Objects.equals(id, other.id) && Objects.equals(natureOfFault, other.natureOfFault);
				//&& Objects.equals(otherFaultDesc, other.otherFaultDesc);
	}

	
}
