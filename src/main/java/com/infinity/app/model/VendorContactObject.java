package com.infinity.app.model;

import org.springframework.context.annotation.Lazy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Lazy(false)
@Table(name="vendor_contact_objects")
public class VendorContactObject {

	@Id
	@NotNull
	@Column(name="id")
	private Long id;
	
	@NotNull
	@Column(name="vendor_name")
	private String vendorName;
	
	@NotNull
	@Column(name="contact")
	private String contact;
	
	@NotNull
	@Column(name="status")
	private Boolean status;

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

	public VendorContactObject(@NotNull Long id, @NotNull String vendorName, @NotNull String contact,
			@NotNull Boolean status) {
		this.id = id;
		this.vendorName = vendorName;
		this.contact = contact;
		this.status = status;
	}

	public VendorContactObject() {}

	@Override
	public String toString() {
		return "VendorContactObject [id=" + id + ", vendorName=" + vendorName + ", contact=" + contact + ", status="
				+ status + "]";
	}
	
	



}
