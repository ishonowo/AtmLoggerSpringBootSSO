package com.infinity.app.model;

import org.springframework.context.annotation.Lazy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Lazy(false)
@Table(name="terminalObjs")
public class TerminalObjects {

	@Id
	@NotNull
	@Column(name="id")
	private Long id;
	
	@NotNull
	@Column(name="vendor_name")
	private String vendorName;
	
	@NotNull
	@Column(name="terminal_id")
	private String terminalId;
	
	@NotNull
	@Column(name="atm_name")
	private String atmName;
	
	@NotNull
	@Column(name="offsite")
	private Boolean offsite;

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public TerminalObjects(@NotNull String vendorName, @NotNull Long id,
			@NotNull String terminalId,
			@NotNull String atmName, @NotNull Boolean offsite) {
		this.vendorName = vendorName;
		this.id = id;
		this.terminalId = terminalId;
		this.atmName = atmName;
		this.offsite = offsite;
	}

	public TerminalObjects() {}

	@Override
	public String toString() {
		return "TerminalObjs [vendorName=" + vendorName + ", id=" + id + ", terminalId=" + terminalId + ", atmName="
				+ atmName + ", offsite=" + offsite + "]";
	}

	
}
