/*package com.infinity.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.infinity.app.dto.TerminalObjects;

public interface TerminalObjectRepo extends JpaRepository<TerminalObjects, Long>{

	@Query("SELECT new com.infinity.app.model.TerminalObjects(v.vendorId,v.vendorName, t.id, t.terminalId, t.atmName, t.offsite) " +
	           "FROM Vendor v JOIN Terminals t ON v.id = t.vendorId")
	    List<TerminalObjects> findAllTerminalObjects();
}
*/