package com.infinity.app.dto;


public class VendorContactObject {

	private Long vendorId;
	
	private String contact;
	
	private Boolean status;
	
	private Long id;
	
	private String vendorName;

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
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

	public VendorContactObject(Long vendorId, String contact, Boolean status, Long id, String vendorName) {
		this.vendorId = vendorId;
		this.contact = contact;
		this.status = status;
		this.id = id;
		this.vendorName = vendorName;
	}

	public VendorContactObject() {}

	public VendorContactObject(Long vendorId, String contact, Boolean status, String vendorName) {
		this.vendorId = vendorId;
		this.contact = contact;
		this.status = status;
		this.vendorName = vendorName;
	}

	@Override
	public String toString() {
		return "VendorContactObject [vendorId=" + vendorId + ", contact=" + contact + ", status=" + status + ", id="
				+ id + ", vendorName=" + vendorName + "]";
	}
	




}
