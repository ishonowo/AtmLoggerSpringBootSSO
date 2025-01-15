package com.infinity.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.infinity.app.dto.BranchObject;
import com.infinity.app.model.BranchInfo;
import com.infinity.app.repo.BranchRepo;

@Service
public class BranchService {
	
	private final BranchRepo branchRepo;
	
	public BranchService(BranchRepo branchRepo) {
		this.branchRepo = branchRepo;
	}
	
	public List<BranchObject> findAllBranchObject(){
		return branchRepo.findAllBranchObject()
				.stream()
				.map(projection -> new BranchObject(
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

}
