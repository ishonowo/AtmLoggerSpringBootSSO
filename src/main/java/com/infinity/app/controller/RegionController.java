package com.infinity.app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infinity.app.model.Region;
import com.infinity.app.service.RegionService;

/*@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = {"Content-Type", "Authorization"}, 
methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS},
allowCredentials = "true")*/
@RestController
@RequestMapping("/api/regions")
public class RegionController {
    private final RegionService regionService;
    
    public RegionController(RegionService regionService) {
    	this.regionService=regionService;
    }
    
    /*@GetMapping("/validate/{regionId}")
    public boolean validateRegion(@PathVariable Long regionId) {
        boolean isFound = regionService.isRegionFound(regionId);
        System.out.println(isFound);
        return isFound;
    }*/
    
    @PostMapping
    public ResponseEntity<Region> insertRegion(@RequestBody String regionName) {
    	System.out.println("Saved.");
        Region savedRegion = regionService.insertRegion(regionName);
        return ResponseEntity.ok(savedRegion);
    }
    
    @PutMapping
    public ResponseEntity<Region> updateRegion(@RequestBody Region updatedRegion) {
    	return ResponseEntity.ok(regionService.updateRegion(updatedRegion));
    }
    
    @GetMapping
    public ResponseEntity<List<Region>> getAllRegions() {
        return ResponseEntity.ok(regionService.getAllRegions());
    }
}
