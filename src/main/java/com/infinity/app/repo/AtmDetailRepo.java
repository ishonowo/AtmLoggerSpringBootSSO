package com.infinity.app.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infinity.app.model.AtmDetail;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import java.util.List;



@Repository
@Lazy(false)
public interface AtmDetailRepo extends CrudRepository<AtmDetail, Long> {

	@Query(value="select a.id, a.branch_email, a.branch_name, c.terminal_id, c.atm_name, a.physical_address,d.vendor_name"
			//+ " b.contact, "
			+ " from branch_info a (nolock) join terminals c (nolock) on a.sol_id = SUBSTRING(c.terminal_id,5,3)"
			+ " join vendors d (nolock) on d.id=c.vendor_id "
			+ " where c.terminal_id = ?1",nativeQuery=true)
	public AtmDetail getAtmDetail(String terminalId);

	@Query(value="select vc.contact from terminals t (nolock) join vendor_contacts vc (nolock) "
			+ " on t.vendor_id=vc.vendor_id where vc.[status]=1 and t.terminal_id = ?1",nativeQuery=true)
	public List<String> getActiveContacts(String terminalId);
	
	
}


