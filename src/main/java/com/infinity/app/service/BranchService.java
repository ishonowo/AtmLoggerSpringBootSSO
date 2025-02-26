package com.infinity.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.infinity.app.dto.BranchWithName;
import com.infinity.app.dto.TerminalWithNames;
import com.infinity.app.model.BranchInfo;
import com.infinity.app.model.Terminals;
import com.infinity.app.repo.BranchRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BranchService {
	
	private final BranchRepo branchRepo;
	
	public BranchService(BranchRepo branchRepo) {
		this.branchRepo = branchRepo;
	}
	
	public List<BranchWithName> findAllBranchesWithNames(){
		return branchRepo.findAllBranchesWithNames()
				.stream()
				.map(projection -> new BranchWithName(
						projection.getId(),
						projection.getRegionId(),
						projection.getRegionName(),
						projection.getBranchEmail(),
						projection.getBranchName(),
						projection.getPhysicalAddress(),
						projection.getSolId()))
				.collect(Collectors.toList());
	}

	public Boolean findSol(String sol) {
		return branchRepo.existsBySolId(sol);
	}

	public BranchInfo insertBranch(BranchInfo branch) {
		return branchRepo.save(branch);
	}

	public BranchInfo updateBranch(BranchWithName updatedBranch) {
			BranchInfo branch = branchRepo.findById(updatedBranch.getId())
		            .orElseThrow(() -> new EntityNotFoundException("Branch not found with id: " + updatedBranch.getId()));
		        
			branch.setRegionId(updatedBranch.getRegionId());
			branch.setBranchEmail(updatedBranch.getBranchEmail());
			branch.setBranchName(updatedBranch.getBranchName());
			branch.setPhysicalAddress(updatedBranch.getPhysicalAddress());
			branch.setSolId(updatedBranch.getSolId());
	        
			return branchRepo.save(branch);
		
		
	}

}
