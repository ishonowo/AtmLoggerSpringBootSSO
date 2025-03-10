package com.infinity.app.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.infinity.app.dto.VendorContactObject;
import com.infinity.app.model.VendorContact;
import com.infinity.app.repo.VendorContactRepo;

import jakarta.persistence.EntityNotFoundException;


@Service
public class VendorContactService {
	private final VendorContactRepo vendorContactRepo;
	
	public VendorContactService(VendorContactRepo vendorContactRepo) {
			this.vendorContactRepo=vendorContactRepo;
		}
	
	public List<VendorContactObject> findAllVendorContactObject() {
        return vendorContactRepo.findAllVendorContactObject()
            .stream()
            .map(projection -> new VendorContactObject(
            	projection.getVendorId(),
            	projection.getContact(),
            	projection.getStatus(),
            	projection.getId(),
                projection.getVendorName()))
            .collect(Collectors.toList());
    }

	public VendorContact insertContact(VendorContact contact) {
		// TODO Auto-generated method stub
		return vendorContactRepo.save(contact);
	}

	public List<VendorContact> findAllVendorContacts() {
		return vendorContactRepo.findAll();
	}

	public VendorContact updateVendorContact(VendorContact updatedVendorContact) {
		VendorContact vendorContact = vendorContactRepo.findById(updatedVendorContact.getId())
	            .orElseThrow(() -> new EntityNotFoundException("Vendor not found with id: " + updatedVendorContact.getId()));
	        
	        vendorContact.setVendorId(updatedVendorContact.getVendorId());
	        vendorContact.setContact(updatedVendorContact.getContact());
	        vendorContact.setStatus(updatedVendorContact.getStatus());
		return vendorContactRepo.save(vendorContact);
	}

}
