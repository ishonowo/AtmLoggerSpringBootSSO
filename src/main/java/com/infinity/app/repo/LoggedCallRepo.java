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

import java.time.LocalDateTime;
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
		String getBranchLogger();
		String getLoggerPhone();
		Date getStartingDate();
		Date getDateCompleted();
		
		
		String getLoggerEmail();
		String getLoggerBrowserUsed();
		String getLoggerHostName();
		String getLoggerIP();
		
		String getCloserEmail();
		String getCloserBrowserUsed();
		String getCloserHostName();
		String getCloserIP();

		String getReopenerEmail();
		String getReopenerBrowserUsed();
		String getReopenerHostName();
		String getReopenerIP();
		
		
		String getStatusDesc();
		Long getStatusId();

		// --- SLA additions ---
		Date getHoldStart();
		Date getHoldEnd();
		Integer getAllowedHours();

	}
	
	// SLA note: allowedHours is derived per-message rather than per-fault
	// because EmailIssueService.convertToEmailIssue() splits Power faults
	// into their own Message/EmailIssue before a LoggedCall is ever created
	// for them (see the "Power" split logic there) — so every fault row
	// joined here for a single lc.id is guaranteed to share the same type.
	// MAX(...) is only needed to satisfy SQL Server's GROUP BY rules, not
	// because the value actually varies within a group.
	@Query(value = "EXEC dbo.GetLoggedCallInformation",
		nativeQuery = true)	public List<LoggedCallProjection> findAllLoggedIssueDtos();

	@Modifying
	@Transactional
	@Query(value = "EXEC dbo.InsertLoggedCall " +
            ":branchName, :subject, :vendorName, :messageId, :dateLogged, " +
            ":loggerEmail, :loggerIp, :loggerBrowser, :loggerHostname, :dateCompleted, :statusId",
    nativeQuery = true)
	void insertLoggedCall(@Param("subject") String subject, 
	             @Param("branchName") String branchName, 
	             @Param("vendorName") String vendorName, 
	             @Param("messageId") Long messageId, 
	             @Param("dateLogged") Date dateLogged, 
	             @Param("loggerEmail") String loggerEmail,
	             @Param("loggerIp") String loggerIp,
	             @Param("loggerBrowser") String loggerBrowser,
	             @Param("loggerHostname") String loggerHostname,
	             @Param("dateCompleted") Date dateCompleted, 
	             @Param("statusId") Long statusId);

    @Transactional
    @Modifying
    @Query("UPDATE LoggedCall lc SET lc.statusId = :statusId, lc.dateCompleted = :dateCompleted WHERE lc.id = :logId")
    int updateStatusAndDateCompleted(Long logId, Long statusId, Date dateCompleted);

    @Transactional
    @Modifying
    @Query("UPDATE LoggedCall lc SET lc.statusId = :statusId, lc.dateCompleted = :dateCompleted, "
    	+ " lc.closerEmail= :email, lc.closerIp= :ip, lc.closerHostname= :hostname, lc.closerBrowser= :browser "
    		+ " WHERE lc.id = :logId")
    int updateCloserStatusAndDateCompleted(Long logId, Long statusId, Date dateCompleted, String email, String ip,
    		String hostname, String browser);

    @Transactional
    @Modifying
    @Query("UPDATE LoggedCall lc SET lc.statusId = :statusId, lc.dateCompleted = :dateCompleted, "
    		+ " lc.reopenerEmail= :email, lc.reopenerIp= :ip, lc.reopenerHostname= :hostname, lc.reopenerBrowser= :browser "
    		+ " WHERE lc.id = :logId")
    int updateReopenerStatusAndDateCompleted(Long logId, Long statusId, Date dateCompleted, String email, String ip,
    		String hostname, String browser);


    // --- SLA additions: put on hold / resume, mirroring the hold logic
    // discussed for sla_log, but applied directly to logged_calls since
    // hold_start/hold_end already live there. ---

    @Transactional
    @Modifying
    @Query("UPDATE LoggedCall lc SET lc.holdStart = :holdStart WHERE lc.id = :logId")
    int putOnHold(@Param("logId") Long logId, @Param("holdStart") Date holdStart);

    @Transactional
    @Modifying
    @Query("UPDATE LoggedCall lc SET lc.holdEnd = :holdEnd WHERE lc.id = :logId")
    int resumeFromHold(@Param("logId") Long logId, @Param("holdEnd") Date holdEnd);

    //List<LoggedCall> findAllByOrderByLoggedAtDesc();

}
