package com.infinity.app.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.infinity.app.model.Vendors;
import com.infinity.app.repo.VendorRepo;

@Service
public class VendorService {
	private final VendorRepo vendorRepo;

	public VendorService(VendorRepo vendorRepo) {
			this.vendorRepo=vendorRepo;
		}

	public Vendors insertVendor(Vendors vendorString) {
		return vendorRepo.save(vendorString);
	}

	public List<Vendors> getAllVendors() {
		return vendorRepo.findAll();
	}

}