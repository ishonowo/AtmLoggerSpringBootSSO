package com.infinity.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.infinity.app.model.AtmFault;
import com.infinity.app.repo.AtmFaultRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AtmFaultService {

    private final AtmFaultRepo atmFaultRepo;

    public AtmFaultService(AtmFaultRepo atmFaultRepo) {
        this.atmFaultRepo = atmFaultRepo;
    }

    public List<AtmFault> getAllFaults() {
        return atmFaultRepo.findAllOrdered();
    }

	public List<AtmFault> findAllById(List<Long> atmFaultIds) {
		return atmFaultRepo.findAllById(atmFaultIds);
	}

	public AtmFault insertFault(AtmFault fault) {
		return atmFaultRepo.save(fault);
	}

	public AtmFault updateFault(AtmFault updatedFault) {
		AtmFault fault=atmFaultRepo.findById(updatedFault.getId())
	            .orElseThrow(() -> new EntityNotFoundException("ATM fault not found with id: " + updatedFault.getId()));
		fault.setNatureOfFault(updatedFault.getNatureOfFault());
		fault.setDescription(updatedFault.getDescription());
		fault.setFaultType(updatedFault.getFaultType());
		return atmFaultRepo.save(fault);
	}
}
