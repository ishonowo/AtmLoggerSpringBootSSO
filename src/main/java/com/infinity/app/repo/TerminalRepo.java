package com.infinity.app.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infinity.app.model.Terminals;

@Repository
public interface TerminalRepo extends JpaRepository<Terminals, Long> {
	public interface TerminalProjection {
	    Long vendorId();
	    String vendorName();
	    Long id();
	    String terminalId();
	    String atmName();
	    Boolean offsite();
	}
	
	@Query(value = "SELECT t.vendor_id,v.vendor_name, t.id, t.terminal_id, t.atm_name, t.offsite"
					+ " FROM Terminals t JOIN Vendors v ON v.id = t.vendor_id",
		       nativeQuery = true)
		public List<TerminalProjection> findAllTerminalsWNames();
}