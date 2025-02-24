package com.infinity.app.dto;


public class TerminalWithNames {

	private Long vendorId;
	private String vendorName;
	private Long id;
	private String terminalId;
	private String atmName;
	private Boolean offsite;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public Long getVendorId() {
		return vendorId;
	}
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
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
	public Boolean getOffsite() {
		return offsite;
	}
	public void setOffsite(Boolean offsite) {
		this.offsite = offsite;
	}
	
	public TerminalWithNames(Long vendorId, String vendorName, Long id, String terminalId, String atmName,
			Boolean offsite) {
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.id = id;
		this.terminalId = terminalId;
		this.atmName = atmName;
		this.offsite = offsite;
	}
	
	public TerminalWithNames(String vendorName, Long vendorId, String terminalId, String atmName, Boolean offsite) {
		this.vendorName = vendorName;
		this.vendorId = vendorId;
		this.terminalId = terminalId;
		this.atmName = atmName;
		this.offsite = offsite;
	}
	
	public TerminalWithNames(){}
	
	@Override
	public String toString() {
		return "TerminalObjects [id=" + id + ", vendorName=" + vendorName + ", vendorId=" + vendorId + ", terminalId="
				+ terminalId + ", atmName=" + atmName + ", offsite=" + offsite + "]";
	}

}