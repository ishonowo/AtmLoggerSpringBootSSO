package com.infinity.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.infinity.app.model.AtmFault;
import com.infinity.app.repo.AtmFaultRepo;

@Service
public class AtmFaultService {

    private final AtmFaultRepo atmFaultRepo;

    public AtmFaultService(AtmFaultRepo atmFaultRepo) {
        this.atmFaultRepo = atmFaultRepo;
    }

    public List<AtmFault> getAllFaults() {
        return atmFaultRepo.findAllOrdered();
    }
}
