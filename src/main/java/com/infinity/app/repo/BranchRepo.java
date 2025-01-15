package com.infinity.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infinity.app.model.BranchInfo;

@Repository
public interface BranchRepo extends JpaRepository<BranchInfo, Long>{
	
	public interface BranchProjection {
	    String getRegionName();
	    String getBranchEmail();
	    String getBranchName();
	    String getPhysicalAddress();
	    String getSolId();	    
	}
	
	@Query(value = "SELECT r.region_name as regionName,b.branch_email as branchEmail,b.branch_name as branchName,"
			+ " b.physical_address as physicalAddress,b.sol_id  as solId " +
		       "FROM branch_info b JOIN regions r ON r.id = b.region_id",
		       nativeQuery = true)
		public List<BranchProjection> findAllBranchObject();
	
	@Query(value = "SELECT CASE WHEN COUNT(b.sol_id) > 0 THEN 'TRUE' ELSE 'FALSE' END " +
	           "FROM branch_info b WHERE b.sol_id = :sol", 
	           nativeQuery = true)
	    Boolean existsBySolId(@Param("sol") String sol);

}
