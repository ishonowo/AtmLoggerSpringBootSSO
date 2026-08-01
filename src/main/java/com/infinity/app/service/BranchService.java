package com.infinity.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infinity.app.dto.BranchWithName;
//import com.infinity.app.dto.TerminalWithNames;
import com.infinity.app.model.BranchInfo;
//import com.infinity.app.model.Terminal;
import com.infinity.app.repo.BranchRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

@Service
public class BranchService {
	
	private final BranchRepo branchRepo;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public BranchService(BranchRepo branchRepo) {
		this.branchRepo = branchRepo;
	}
	
	@Transactional(readOnly = true)
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

	/*@Transactional(readOnly = true)
	public Boolean findSol(String sol) {
		return branchRepo.existsBySolId(sol);
	}*/
	
	@Transactional(readOnly = true)
    public Boolean findSol(String solId) {
        StoredProcedureQuery query = entityManager
            .createStoredProcedureQuery("ExistsBySolId")
            .registerStoredProcedureParameter("solId", String.class, ParameterMode.IN)
            .registerStoredProcedureParameter("bool", Boolean.class, ParameterMode.OUT)
            .setParameter("solId", solId);
        
        query.execute();
        
        Boolean result = (Boolean) query.getOutputParameterValue("bool");
        return result != null ? result : false;
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
