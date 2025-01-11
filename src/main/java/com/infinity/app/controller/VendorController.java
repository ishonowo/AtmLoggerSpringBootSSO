package com.infinity.app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infinity.app.dto.VendorNameDto;
import com.infinity.app.model.Vendor;
import com.infinity.app.service.VendorService;


@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorService vendorService;
    
    public VendorController(VendorService vendorService) {
    	this.vendorService=vendorService;
    }
    // Fetch all vendors
    @GetMapping
    public ResponseEntity<List<Vendor>> getAllVendors() {
        List<Vendor> vendors = vendorService.getAllVendors();
        System.out.println(vendors);
        return ResponseEntity.ok(vendors);
    }

    // Insert a new vendor
    @PostMapping
    public ResponseEntity<Vendor> insertVendor(@RequestBody Vendor vendorString) {
    	vendorString.setShortName(vendorString.getShortName().toUpperCase());
    	Vendor savedVendor = vendorService.insertVendor(vendorString);
        return ResponseEntity.ok(savedVendor);
    }
    
    @GetMapping("/names")
    public ResponseEntity<List<VendorNameDto>> findAllNames() {
        return ResponseEntity.ok(vendorService.findAllNames());
    }
}
