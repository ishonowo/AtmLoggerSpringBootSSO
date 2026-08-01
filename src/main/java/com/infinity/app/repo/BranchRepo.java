package com.infinity.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.ParameterMode;

import com.infinity.app.model.BranchInfo;

@Repository
public interface BranchRepo extends JpaRepository<BranchInfo, Long>{
	
	public interface BranchProjection {
		Long getId();
		Long getRegionId();
	    String getRegionName();
	    String getBranchEmail();
	    String getBranchName();
	    String getPhysicalAddress();
	    String getSolId();	    
	}
	
	@Procedure(procedureName  = "FindAllBranchesWithNames")
		public List<BranchProjection> findAllBranchesWithNames();
	
	/*@Procedure(procedureName  = "ExistsBySolId")
	@Transactional(readOnly = true)
	Boolean existsBySolId(
			@Param("solId") String solId,
			@Param(value = "bool", mode = ParameterMode.OUT) Boolean bool
			);*/


}
