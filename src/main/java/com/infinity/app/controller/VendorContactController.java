package com.infinity.app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infinity.app.model.VendorContactObject;
import com.infinity.app.service.VendorContactService;

@RestController
@RequestMapping("/api/contacts")
public class VendorContactController {
	
	private final VendorContactService vendorContactService;
	
	public VendorContactController(VendorContactService vendorContactService) {
		this.vendorContactService=vendorContactService;
	}
	
    @GetMapping
    public ResponseEntity<List<VendorContactObject>> findAllVendorContactObject() {
        List<VendorContactObject> newContact = vendorContactService.findAllVendorContactObject();
        System.out.println(newContact);
        return ResponseEntity.ok(newContact);
    }

}
