package com.infinity.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

//Java Entity (RegionEntity.java)
@Entity
@Table(name = "Regions")
public class Regions {
	
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @SequenceGenerator(name = "region", schema="dbo", sequenceName = "sq_region", allocationSize = 1)
 @Column(name = "id")
 private Long id;
 
 @Column(name = "region_name")
 private String regionName;
 
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getRegionName() {
	return regionName;
}
public void setRegionName(String regionName) {
	this.regionName = regionName;
}
@Override
public String toString() {
	return "Region [id=" + id + ", RegionName=" + regionName + "]";
}

}
