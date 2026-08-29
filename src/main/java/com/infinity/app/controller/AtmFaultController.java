package com.infinity.app.controller;

import com.infinity.app.model.AtmFault;
import com.infinity.app.service.AtmFaultService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atm")
public class AtmFaultController {

    private final AtmFaultService atmFaultService;

    public AtmFaultController(AtmFaultService atmFaultService) {
        this.atmFaultService = atmFaultService;
    }

    // GET /atm/atm-faults -> list of nature_of_fault options for the buttons
    @GetMapping("/atm-faults")
    public ResponseEntity<List<AtmFault>> getAtmFaults() {
    	System.out.println(atmFaultService.getAllFaults());
        return ResponseEntity.ok(atmFaultService.getAllFaults());
    }
}
