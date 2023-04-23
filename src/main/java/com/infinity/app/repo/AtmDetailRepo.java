package com.infinity.app.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infinity.app.model.AtmDetail;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;




@Repository
@Lazy(false)
public interface AtmDetailRepo extends CrudRepository<AtmDetail, Long> {

	@Query(value="select a.id, a.branchEmail, a.branchName, c.terminalId, c.atmName, a.physicalAddress, b.contact, b.vendorName "
			+ " from BranchInfo a (nolock) join VendorTerminal c (nolock) on a.solId = SUBSTRING(c.terminalId,5,3)"
			+ " join VendorContact b (nolock) on b.vendorName = c.vendorName "
			+ " where c.terminalId = ?1",nativeQuery=true)
	public AtmDetail getAtmDetail(String terminalId);
	
	 //AtmDetail getAtmDetail(String terminalId);
	
}


