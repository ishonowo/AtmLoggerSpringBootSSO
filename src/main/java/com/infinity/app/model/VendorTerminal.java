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
public class VendorTerminal {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name = "vendorTerminal", schema="dbo", sequenceName = "sq_vendorTerminal", allocationSize = 1)
	private Long id;
	
	@NotNull
	private String vendorName;
	
	@NotNull
	private String terminalId;
	
	@NotNull
	private String atmName;
	
	@NotNull
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

	@Override
	public String toString() {
		return "VendorTerminal [id=" + id + ", vendorName=" + vendorName + ", terminalId=" + terminalId + ", atmName="
				+ atmName + ", offsite=" + offsite + "]";
	}

	public VendorTerminal(Long id, @NotNull String vendorName, @NotNull String terminalId, @NotNull String atmName,
			@NotNull Boolean offsite) {
		super();
		this.id = id;
		this.vendorName = vendorName;
		this.terminalId = terminalId;
		this.atmName = atmName;
		this.offsite = offsite;
	}

	public VendorTerminal() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
