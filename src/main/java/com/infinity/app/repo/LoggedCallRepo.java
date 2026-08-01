package com.infinity.app.repo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//import com.infinity.app.dto.LoggedCallDto;
//import com.infinity.app.dto.LoggedInfo;
import com.infinity.app.model.LoggedCall;

//import jakarta.validation.constraints.NotNull;

@Repository
public interface LoggedCallRepo extends JpaRepository<LoggedCall, Long>{
	
    // Find calls by status ID
    Optional<LoggedCall> findById(Long id);

    // Custom query to update a logged call
    @Query("UPDATE LoggedCall lc SET lc.statusId = :statusId, lc.dateCompleted = :dateCompleted WHERE lc.id = :id")
    int updateLoggedCallStatus(@Param("id") Long id, @Param("statusId") Long statusId, @Param("dateCompleted") 
    							Date dateCompleted);

	public interface LoggedCallProjection {
		Long getLogId();
		String getBranchName();
		String getTerminalId();
		String getTerminalName();
		String getVendorName();
		String getIssueDesc();
		Date getDateLogged();
		String getFromEmail();
		String getBranchLogger();
		String getLoggerPhone();
		Date getStartingDate();
		Date getDateCompleted();
		String getBrowserUsed();
		String getHostName();
		String getLoggerIP();
		String getStatusDesc();
		Long getStatusId();

	}
	
	@Query(value = "SELECT lc.id as logId,bi.branch_name as branchName,t.terminal_id as terminalId,"
			+ " t.atm_name as terminalName,v.vendor_name as vendorName, m.issue_desc as issueDesc,lc.date_logged as dateLogged,"
			+ "	lc.from_email as fromEmail,m.branch_logger as branchLogger,m.logger_phone as loggerPhone,lc.starting_date as startingDate,"
			+ " lc.date_completed as dateCompleted,"
			+ " lc.browser as [browserUsed],lc.hostname as loggerHostName,lc.[ip] as loggerIP,"
			+ "	ls.status_desc as statusDesc,ls.id as statusId"
			+ "	FROM [logged_calls] lc (NOLOCK) JOIN [branch_info] bi (NOLOCK)"
			+ "		ON lc.branch_id=bi.id"
			+ "		JOIN [terminals] t (NOLOCK)"
			+ "		ON lc.t_id=t.id"
			+ "		JOIN [vendors] v (NOLOCK)"
			+ "		ON lc.vendor_id=v.id"
			+ "		JOIN [dbo].[message] m (NOLOCK)"
			+ "		ON lc.message_id=m.id"	 		
			+ "		JOIN [log_status] ls (NOLOCK)"
			+ "		ON lc.status_id=ls.id "
			+ "		JOIN [email_issue] ei (NOLOCK)"
			+ "		ON m.id=ei.message_id"
			+ "		order by lc.id desc;",
       nativeQuery = true)
	public List<LoggedCallProjection> findAllLoggedIssueDtos();

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO logged_calls (branch_id, t_id, vendor_id, message_id, date_logged, starting_date,from_email,"
				 + " ip,browser,hostname,date_completed, status_id) " +
	               "VALUES ((SELECT id FROM branch_info WHERE branch_name=:branchName), " +
	               "(SELECT id FROM terminals WHERE terminal_id=SUBSTRING(:subject,16,8)), " +
	               "(SELECT id FROM vendors WHERE vendor_name=:vendorName), " +
	               ":messageId, :dateLogged, " +
	               "CASE WHEN CAST(:dateLogged AS TIME) <= '17:00:00.000000' THEN CAST(:dateLogged AS DATE) ELSE CAST(DATEADD(DAY,1,:dateLogged) AS DATE) END, " +
	               " :fromEmail,:ip, :browser, :hostname, :dateCompleted, :statusId)", 
	       nativeQuery = true)
	void saveObj(@Param("subject") String subject, 
	             @Param("branchName") String branchName, 
	             @Param("vendorName") String vendorName, 
	             @Param("messageId") Long messageId, 
	             @Param("dateLogged") Date dateLogged, 
	             @Param("fromEmail") String fromEmail,
	             @Param("ip") String ip,
	             @Param("browser") String browser,
	             @Param("hostname") String hostname,
	             @Param("dateCompleted") Date dateCompleted, 
	             @Param("statusId") Long statusId);

    @Transactional
    @Modifying
    @Query("UPDATE LoggedCall lc SET lc.statusId = :statusId, lc.dateCompleted = :dateCompleted WHERE lc.id = :logId")
    int updateStatusAndDateCompleted(Long logId, Long statusId, Date dateCompleted);

	//void saveObj(String subject, String branchName, String vendorName, Long messageId, String ip, String browser,
		//	String hostname, Date dateLogged, Object object, long l);

}
