package com.infinity.app.dto;


public class VendorContactObject {

	
	private String vendorName;
	
	private String contact;
	
	private Boolean status;



	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public VendorContactObject(  String vendorName,  String contact,
			Boolean status) {
		this.vendorName = vendorName;
		this.contact = contact;
		this.status = status;
	}

	public VendorContactObject() {}

	@Override
	public String toString() {
		return "VendorContactObject [ vendorName=" + vendorName + ", contact=" + contact + ", status="
				+ status + "]";
	}
	
	



}
