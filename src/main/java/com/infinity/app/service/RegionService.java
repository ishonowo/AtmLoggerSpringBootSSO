package com.infinity.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.infinity.app.model.Region;
//import com.infinity.app.model.Region;
import com.infinity.app.repo.RegionRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RegionService {
    private final RegionRepo regionRepo;

	public RegionService(RegionRepo regionRepo) {
		this.regionRepo=regionRepo;
	}    
    
	/*public boolean isRegionFound(long regionId) {
        return regionRepo.existsById(regionId);
    }*/
	
	public Region insertRegion(String regionName) {
		Region region = new Region();
        region.setRegionName(regionName);
        return regionRepo.save(region);
    }
	
	public List<Region> getAllRegions() {
        return regionRepo.findAll();
    }

	public Region updateRegion(Region updatedRegion) {
		Region region= regionRepo.findById(updatedRegion.getId())
				.orElseThrow(() -> new EntityNotFoundException("Region not found with id: " + updatedRegion.getId()));
		region.setRegionName(updatedRegion.getRegionName());
		return regionRepo.save(region);
	}
	
}