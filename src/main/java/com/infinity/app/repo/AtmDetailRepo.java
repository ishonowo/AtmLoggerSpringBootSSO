package com.infinity.app.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.infinity.app.model.AtmDetail;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;



@Repository
@Lazy(false)
public interface AtmDetailRepo extends CrudRepository<AtmDetail, Long> {

	@Procedure(procedureName  = "GetTerminalDetails")
	AtmDetail getAtmDetail(@Param("terminalId") String terminalId);

	@Procedure(procedureName  = "GetActiveContacts")
	public List<String> getActiveContacts(String terminalId);
	
	
}


