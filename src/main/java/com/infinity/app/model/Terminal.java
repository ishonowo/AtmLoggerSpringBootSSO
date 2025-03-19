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
@Table(name="terminals")
public class Terminal {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@SequenceGenerator(name = "terminal", schema="dbo", sequenceName = "sq_terminal", allocationSize = 1)
	private Long id;
	
	@NotNull
	private Long vendorId;
	
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
		this.atmName = atmName.toUpperCase();
	}

	public Boolean isOffsite() {
		return offsite;
	}

	public void setOffsite(Boolean offsite) {
		this.offsite = offsite;
	}

	@Override
	public String toString() {
		return "Terminals [id=" + id + ", vendorId=" + vendorId + ", terminalId=" + terminalId + ", atmName=" + atmName
				+ ", offsite=" + offsite + "]";
	}

	public Terminal() {}

	public Terminal(Long id, @NotNull Long vendorId, @NotNull String terminalId, @NotNull String atmName,
			@NotNull Boolean offsite) {
		this.id = id;
		this.vendorId = vendorId;
		this.terminalId = terminalId;
		this.atmName = atmName.toUpperCase();
		this.offsite = offsite;
	}

	public Terminal(@NotNull Long vendorId, @NotNull String terminalId, @NotNull String atmName,
			@NotNull Boolean offsite) {
		this.vendorId = vendorId;
		this.terminalId = terminalId;
		this.atmName = atmName.toUpperCase();
		this.offsite = offsite;
	}

	
}
