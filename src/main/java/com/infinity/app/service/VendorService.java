package com.infinity.app.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.infinity.app.dto.VendorNameDto;
import com.infinity.app.model.Vendor;
import com.infinity.app.repo.VendorRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class VendorService {
	private final VendorRepo vendorRepo;

	public VendorService(VendorRepo vendorRepo) {
			this.vendorRepo=vendorRepo;
		}

	public Vendor insertVendor(Vendor vendorString) {
		return vendorRepo.save(vendorString);
	}

	public List<Vendor> getAllVendors() {
		return vendorRepo.findAll();
	}
	
	public List<VendorNameDto> findAllNames(){
		return vendorRepo.findAllNames();
	}

	public Object updateVendor(Vendor updatedVendor) {
		Vendor vendor = vendorRepo.findById(updatedVendor.getId())
	            .orElseThrow(() -> new EntityNotFoundException("Vendor not found with id: " + updatedVendor.getId()));
	        
	        vendor.setVendorName(updatedVendor.getVendorName());
	        vendor.setShortName(updatedVendor.getShortName());
		return vendorRepo.save(vendor);
	}

}