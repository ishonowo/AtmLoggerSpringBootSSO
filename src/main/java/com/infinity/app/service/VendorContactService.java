package com.infinity.app.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.infinity.app.model.VendorContactObject;
import com.infinity.app.repo.VendorContactObjectRepo;
import com.infinity.app.repo.VendorContactRepo;


@Service
public class VendorContactService {
	private final VendorContactRepo vendorContactRepo;
	
	private final VendorContactObjectRepo vendorContactObjectRepo;

	public VendorContactService(VendorContactRepo vendorContactRepo,
			VendorContactObjectRepo vendorContactObjectRepo) {
			this.vendorContactRepo=vendorContactRepo;
			this.vendorContactObjectRepo=vendorContactObjectRepo;
		}
	
	public List<VendorContactObject> findAllVendorContactObject(){
		return vendorContactObjectRepo.findAllVendorContactObject();
	}

}
