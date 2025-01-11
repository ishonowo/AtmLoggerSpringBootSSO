package com.infinity.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendors")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(nullable = false, length = 100, name="vendor_name")
    private String vendorName;

    @Column(nullable = false, length = 20, name="short_name")
    private String shortName;

    // Getters and Setters
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

	public Vendor(String vendorName, String shortName) {
		this.vendorName = vendorName;
		this.shortName = shortName;
	}

	public Vendor(Long id, String vendorName, String shortName) {
		this.id = id;
		this.vendorName = vendorName;
		this.shortName = shortName;
	}

	public Vendor() {}

	@Override
	public String toString() {
		return "Vendor [id=" + id + ", vendorName=" + vendorName + ", shortName=" + shortName + "]";
	}
    
}
