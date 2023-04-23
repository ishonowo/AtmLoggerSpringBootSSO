package com.infinity.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Lazy;

@Entity
@Lazy(false)
public class VendorContact {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name = "vendorContact", schema="dbo", sequenceName = "sq_vendorContact", allocationSize = 1)
	private Long id;

	@NotNull
	private String vendorName;
	
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

	@Override
	public String toString() {
		return "VendorContact [id=" + id + ", vendorName=" + vendorName + ", contact=" + contact + ", status=" + status
				+ "]";
	}

	public VendorContact(Long id, @NotNull String vendorName, @NotNull String contact, @NotNull Boolean status) {
		super();
		this.id = id;
		this.vendorName = vendorName;
		this.contact = contact;
		this.status = status;
	}

	public VendorContact() {
		super();
	}
	
	
}
