package com.infinity.app.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infinity.app.model.AtmDetail;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;




@Repository
@Lazy(false)
public interface AtmDetailRepo extends CrudRepository<AtmDetail, Long> {

	@Query(value="select a.id, a.branch_email, a.branch_name, c.terminal_id, c.atm_name, a.physical_address, b.contact, b.vendor_name "
			+ " from branch_info a (nolock) join vendor_terminal c (nolock) on a.sol_id = SUBSTRING(c.terminal_id,5,3)"
			+ " join vendor_contact b (nolock) on b.vendor_name = c.vendor_name "
			+ " where c.terminal_id = ?1",nativeQuery=true)
	public AtmDetail getAtmDetail(String terminalId);
	
	 //AtmDetail getAtmDetail(String terminalId);
	
}


