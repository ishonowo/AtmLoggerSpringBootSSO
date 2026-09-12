package com.infinity.app.dto;

import jakarta.persistence.Column;

public class AtmFaultObj {

	private String natureOfFault;
	
	private String description;
	
	private String faultType;

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

	public AtmFaultObj(String natureOfFault, String description, String faultType) {
		super();
		this.natureOfFault = natureOfFault;
		this.description = description;
		this.faultType = faultType;
	}

	public AtmFaultObj() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AtmFaultObj [natureOfFault=" + natureOfFault + ", description=" + description + ", faultType="
				+ faultType + "]";
	}
	
	
}
