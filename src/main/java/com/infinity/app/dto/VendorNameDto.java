package com.infinity.app.dto;

public class VendorNameDto {

	private Long id;
	
    private String vendorName;

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

	public VendorNameDto(Long id, String vendorName) {
		this.id = id;
		this.vendorName = vendorName;
	}

	public VendorNameDto() {}

	@Override
	public String toString() {
		return "VendorNameDto [id=" + id + ", vendorName=" + vendorName + "]";
	}
    
    
}
