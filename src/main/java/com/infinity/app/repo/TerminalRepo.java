package com.infinity.app.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infinity.app.model.Terminal;

@Repository
public interface TerminalRepo extends JpaRepository<Terminal, Long> {
	public interface TerminalProjection {
	    Long getVendorId();
	    String getVendorName();
	    Long getId();
	    String getTerminalId();
	    String getAtmName();
	    Boolean getOffsite();
	}
	
	@Query(value = "SELECT t.vendor_id as vendorId,v.vendor_name as vendorName, t.id as id,"
					+ " t.terminal_id as terminalID, t.atm_name as atmName, t.offsite as offsite"
					+ " FROM Terminals t INNER JOIN Vendors v ON v.id = t.vendor_id;",
		       nativeQuery = true)
		public List<TerminalProjection> findAllTerminalsWithNames();
}