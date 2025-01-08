package com.infinity.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import org.springframework.context.annotation.Lazy;

@Entity
@Lazy(false)
@Table(name="vendor_contacts")
public class VendorContact {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@SequenceGenerator(name = "vendorContact", schema="dbo", sequenceName = "sq_vendorContact", allocationSize = 1)
	private Long id;

	@NotNull
	private String vendorId;
	
	@NotNull
	private String contact;
	
	@NotNull
	private Boolean status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
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

	@Override
	public String toString() {
		return "VendorContact [id=" + id + ", vendorId=" + vendorId + ", contact=" + contact + ", status=" + status
				+ "]";
	}

	public VendorContact() {}

	public VendorContact(Long id, @NotNull String vendorId, @NotNull String contact, @NotNull Boolean status) {
		this.id = id;
		this.vendorId = vendorId;
		this.contact = contact;
		this.status = status;
	}

	public VendorContact(@NotNull String vendorId, @NotNull String contact, @NotNull Boolean status) {
		this.vendorId = vendorId;
		this.contact = contact;
		this.status = status;
	}

	
	
}
