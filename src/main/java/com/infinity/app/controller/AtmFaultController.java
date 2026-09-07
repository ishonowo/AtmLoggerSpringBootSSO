package com.infinity.app.controller;

import com.infinity.app.model.AtmFault;
import com.infinity.app.service.AtmFaultService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atm")
public class AtmFaultController {

    private final AtmFaultService atmFaultService;

    public AtmFaultController(AtmFaultService atmFaultService) {
        this.atmFaultService = atmFaultService;
    }

    @GetMapping("/atm-faults")
    public ResponseEntity<List<AtmFault>> getAtmFaults() {
    	System.out.println(atmFaultService.getAllFaults());
        return ResponseEntity.ok(atmFaultService.getAllFaults());
    }
    
    // Insert a new vendor
    @PostMapping("/atm-faults")
    public ResponseEntity<AtmFault> insertFault(@RequestBody AtmFault fault) {
    	AtmFault savedFault = atmFaultService.insertFault(fault);
        return ResponseEntity.ok(savedFault);
    }
    
    @PutMapping("/atm-faults")
    public ResponseEntity<AtmFault> updateFault(@RequestBody AtmFault updatedFault) {
        return ResponseEntity.ok((AtmFault)atmFaultService.updateFault(updatedFault));
    }

}
