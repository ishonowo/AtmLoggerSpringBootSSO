package com.infinity.app.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infinity.app.dto.LoggedCallDto;
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
	
	@Query(value = "SELECT b.[branch_name] AS [Branch Name],t.terminal_id AS [Terminal ID], t.atm_name AS [Terminal Name],"
			+ "	v.[vendor_name] AS [Vendor], m.issue_desc+' '+CAST(CAST(m.date_logged AS DATE) AS varchar(255)) AS [Fault],"
			+ "	m.branch_logger+' '+m.logger_phone AS [Contact Person], "
			+ "	CASE WHEN CAST(m.date_logged AS TIME)<= '17:00:00.000000' THEN CAST(m.date_logged AS DATE) "
			+ "	ELSE CAST(DATEADD(DAY,1,m.date_logged) AS DATE) END  AS [Starting Date], "
			+ "	l.date_completed AS [Date Completed], ls.status_desc AS [Status] "
			+ "FROM [dbo].[branch_info] b (nolock) "
			+ "JOIN [dbo].[terminals] t (nolock) "
			+ "ON b.[sol_id]=SUBSTRING(t.terminal_id,5,3) "
			+ "JOIN [dbo].[vendors] v (nolock) "
			+ "ON t.vendor_id=v.id "
			+ "JOIN [dbo].[message] m (nolock) "
			+ "ON LEFT(m.[atm_location],8)=t.terminal_id "
			+ "JOIN [dbo].[email_issue] e (nolock) "
			+ "ON e.message_id=m.id "
			+ "JOIN [dbo].[logged_issue] l (nolock) "
			+ "ON l.message_id= m.id "
			+ "JOIN [dbo].[log_status] ls (nolock) "
			+ "ON l.status_id=l.id;",
       nativeQuery = true)
	public List<LoggedCallDto> findAllLoggedIssueDtos();
	
}
