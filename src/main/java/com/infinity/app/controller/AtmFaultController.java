package com.infinity.app.controller;

import com.infinity.app.dto.AtmFaultObj;
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
@RequestMapping("/atm/database/fault")
public class AtmFaultController {

    private final AtmFaultService atmFaultService;

    public AtmFaultController(AtmFaultService atmFaultService) {
        this.atmFaultService = atmFaultService;
    }

    @GetMapping
    public ResponseEntity<List<AtmFault>> getAtmFaults() {
    	System.out.println(atmFaultService.getAllFaults());
        return ResponseEntity.ok(atmFaultService.getAllFaults());
    }
    
    @PostMapping
    public ResponseEntity<AtmFault> insertFault(@RequestBody AtmFaultObj fault) {
    	AtmFault updatedFault=new AtmFault(fault.getNatureOfFault(),fault.getDescription(),fault.getFaultType());
    	AtmFault savedFault= atmFaultService.insertFault(updatedFault);
        return ResponseEntity.ok(savedFault);
    }
    
    @PutMapping
    public ResponseEntity<AtmFault> updateFault(@RequestBody AtmFault updatedFault) {
        return ResponseEntity.ok((AtmFault)atmFaultService.updateFault(updatedFault));
    }

}
