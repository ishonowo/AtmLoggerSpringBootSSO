package com.infinity.app.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.infinity.app.dto.LoggedCallDto;
//import com.infinity.app.dto.LoggedInfo;
import com.infinity.app.model.LoggedCall;

@Repository
public interface LoggedCallRepo extends JpaRepository<LoggedCall, Long>{

	public interface LoggedIssueProjection {
		String getBranchName();
		String getTerminalId();
		String getTerminalName();
		String getVendorName();
		String getIssueDesc();
		Date getDateLogged();
		String getBranchLogger();
		String getLoggerPhone();
		Date getStartingDate();
		Date getDateCompleted();
		String getStatus();

	}
	
	@Query(value = "SELECT bi.[branch_name],t.terminal_id, t.atm_name,v.vendor_name, m.issue_desc,lc.date_logged,"
			+ "		m.branch_logger,m.logger_phone,lc.starting_date,lc.date_completed, ls.[status_desc]"
			+ "		FROM [logged_calls] lc (nolock) JOIN [branch_info] bi (nolock)"
			+ "		ON lc.branch_id=bi.id"
			+ "		JOIN [terminals] t (nolock)"
			+ "		ON lc.t_id=t.id"
			+ "		JOIN [vendors] v (nolock)"
			+ "		ON lc.vendor_id=v.id"
			+ "		JOIN [message] m (nolock)"
			+ "		ON lc.message_id=m.id"
			+ "		JOIN [log_status] ls (nolock)"
			+ "		ON lc.status_id=ls.id;",
       nativeQuery = true)
	public List<LoggedCallDto> findAllLoggedIssueDtos();

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO logged_calls (branch_id, t_id, vendor_id, message_id, date_logged, starting_date, date_completed, status_id) " +
	               "VALUES ((SELECT id FROM branch_info WHERE branch_name=:branchName), " +
	               "(SELECT id FROM terminals WHERE terminal_id=SUBSTRING(:subject,16,8)), " +
	               "(SELECT id FROM vendors WHERE vendor_name=:vendorName), " +
	               ":messageId, :dateLogged, " +
	               "CASE WHEN CAST(:dateLogged AS TIME) <= '17:00:00.000000' THEN CAST(:dateLogged AS DATE) ELSE CAST(DATEADD(DAY,1,:dateLogged) AS DATE) END, " +
	               ":dateCompleted, :statusId)", 
	       nativeQuery = true)
	void saveObj(@Param("subject") String subject, 
	             @Param("branchName") String branchName, 
	             @Param("vendorName") String vendorName, 
	             @Param("messageId") Long messageId, 
	             @Param("dateLogged") Date dateLogged, 
	             @Param("dateCompleted") Date dateCompleted, 
	             @Param("statusId") Long statusId);

	
}
