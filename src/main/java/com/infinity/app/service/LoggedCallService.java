package com.infinity.app.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;

import com.infinity.app.dto.EmailIssueMessageDto;
import com.infinity.app.dto.LoggedCallDto;
import com.infinity.app.model.LoggedCall;
import com.infinity.app.repo.LoggedCallRepo;
import com.infinity.app.repo.LoggedCallRepo.LoggedCallProjection;

import jakarta.servlet.http.HttpServletRequest;


@Service
public class LoggedCallService {

	private final LoggedCallRepo loggedCallRepo;
	
	public LoggedCallService(LoggedCallRepo loggedCallRepo) {
		this.loggedCallRepo=loggedCallRepo;
	}
	
	
	public List<LoggedCallDto> findAllLoggedIssueDtos(
			String terminalId, 
			Date startDateTimeLogged, Date endDateTimeLogged,
			Date startDateTimeCompleted, Date endDateTimeCompleted) {
		return 	fetchCalls(terminalId,startDateTimeLogged,endDateTimeLogged,startDateTimeCompleted,endDateTimeCompleted);
    }

	private List<LoggedCallDto> fetchCalls(
			String terminalId,
			Date startDateTimeLogged, Date endDateTimeLogged,
			Date startDateTimeCompleted, Date endDateTimeCompleted) {
	    final String term = terminalId == null ? "" : terminalId.trim();
	    final Date sLDate= startDateTimeLogged == null ? null : startDateTimeLogged;
	    final Date eLDate= endDateTimeLogged == null ? null : endDateTimeLogged;
	    final Date sCDate= startDateTimeCompleted == null ? null : startDateTimeCompleted;
	    final Date eCDate= endDateTimeCompleted == null ? null : endDateTimeCompleted;

	    return loggedCallRepo.findAllLoggedIssueDtos().stream()
	            // Terminal ID filter (skipped when empty)
	            .filter(p -> term.isEmpty()
	                    || (p.getTerminalId() != null
	                        && p.getTerminalId().contains(term)))
	            // Start filter: dateLogged >= sDate (skipped when sDate is null)
	            .filter(p -> sLDate == null
	                    || (p.getDateLogged() != null
	                        && !p.getDateLogged().before(sLDate)))
	            // End filter: dateLogged <= eDate (skipped when eDate is null)
	            .filter(p -> eLDate == null
	                    || (p.getDateLogged() != null
	                        && !p.getDateLogged().after(eLDate)))
	            // Start filter: dateLogged >= sDate (skipped when sDate is null)
	            .filter(p -> sCDate == null
	                    || (p.getDateCompleted() != null
	                        && !p.getDateCompleted().before(sCDate)))
	            // End filter: dateLogged <= eDate (skipped when eDate is null)
	            .filter(p -> eCDate == null
	                    || (p.getDateCompleted() != null
	                        && !p.getDateCompleted().after(eCDate)))
	            .map(this::toDto)
	            .collect(Collectors.toList());
	}
	
	public LoggedCall save(LoggedCall loggedCall) {
		return loggedCallRepo.save(loggedCall);
	}
	
	public void saveObj(EmailIssueMessageDto dto,Long messageId,String loggerIp,String loggerBrowser,String loggerHostname) {
		loggedCallRepo.insertLoggedCall(dto.getSubject(),
				  dto.getBranchName(),
				  dto.getVendorName(),
				  messageId,dto.getDateLogged(),dto.getFromEmail(),
				  loggerIp,loggerBrowser,loggerHostname,
				  null,1L);
		
		//return savedLoggedCall;
	}

	public void updateCall(LoggedCallDto updatedCall, String email, HttpServletRequest request) {
		ClientInfoExtractor info = new ClientInfoExtractor(request);
		String ip      = info.getIpAddress();
		String hostname    = info.getHostname();
		String browser = info.getBrowser();
		if("RESOLVED".equalsIgnoreCase(updatedCall.getStatusDesc())) {
			loggedCallRepo.updateCloserStatusAndDateCompleted(updatedCall.getLogId(), updatedCall.getStatusId(),
					updatedCall.getDateCompleted(),email,ip,hostname,browser);
			return;
		}
		else if("REOPENED".equalsIgnoreCase(updatedCall.getStatusDesc())) {
			loggedCallRepo.updateReopenerStatusAndDateCompleted(updatedCall.getLogId(), updatedCall.getStatusId(),
					updatedCall.getDateCompleted(),email,ip,hostname,browser);
			return;
		}
		else {
			loggedCallRepo.updateStatusAndDateCompleted(updatedCall.getLogId(), updatedCall.getStatusId(),
					updatedCall.getDateCompleted());
			return;
		}
    }

	// --- SLA additions ---

	public void putOnHold(Long logId, Date holdStart) {
		/*if (holdStart.after(new Date())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hold start time cannot be in the future");
		}*/
		loggedCallRepo.putOnHold(logId, holdStart);
	}

	public void resumeFromHold(Long logId, Date holdEnd) {
		loggedCallRepo.resumeFromHold(logId, holdEnd);
	}

	// Converts a projection row into a DTO, filling in the SLA fields the
	// projection alone doesn't carry: the deadline and whether it's been
	// (or currently is) breached. Hold time is excluded from the SLA clock —
	// if the call is currently on hold (holdStart set, holdEnd not yet set),
	// the elapsed hold time up to "now" is still added so the deadline
	// reflects the live pause rather than jumping once resumed.
	private LoggedCallDto toDto(LoggedCallProjection p) {
		LoggedCallDto dto = new LoggedCallDto(
				p.getLogId(),
				p.getBranchName(),
				p.getTerminalId(),
				p.getTerminalName(),
				p.getVendorName(),
				p.getIssueDesc(),
				p.getDateLogged(),
				p.getBranchLogger(),
				p.getLoggerPhone(),
				p.getStartingDate(),
				p.getDateCompleted(),
				
				p.getLoggerEmail(),
				p.getLoggerBrowserUsed(),
				p.getLoggerHostName(),
				p.getLoggerIP(),
				
				p.getCloserEmail(),
				p.getCloserBrowserUsed(),
				p.getCloserHostName(),
				p.getCloserIP(),
				
				p.getReopenerEmail(),
				p.getReopenerBrowserUsed(),
				p.getReopenerHostName(),
				p.getReopenerIP(),
				
				
				p.getStatusDesc(),
				p.getStatusId(), null, null, null, null, false);

		dto.setHoldStart(p.getHoldStart());
		dto.setHoldEnd(p.getHoldEnd());
		dto.setAllowedHours(p.getAllowedHours());

		int allowedHours = p.getAllowedHours() != null ? p.getAllowedHours() : 72;
		long allowedMillis = allowedHours * 3_600_000L;

		long holdMillis = 0L;
		Date holdStart = p.getHoldStart();
		if (holdStart != null) {
			Date effectiveHoldEnd = p.getHoldEnd() != null ? p.getHoldEnd() : new Date();
			holdMillis = Math.max(0L, effectiveHoldEnd.getTime() - holdStart.getTime());
		}

		Date deadline = new Date(p.getDateLogged().getTime() + allowedMillis + holdMillis);
		dto.setSlaDeadline(deadline);

		boolean breached = p.getDateCompleted() != null
				? p.getDateCompleted().after(deadline)
				: new Date().after(deadline);
		dto.setSlaBreached(breached);

		return dto;
	}


	public ByteArrayInputStream exportToExcel(
			String terminalId,
			Date startDateTimeLogged, Date endDateTimeLogged,
			Date startDateTimeCompleted, Date endDateTimeCompleted)  throws IOException {
		List<LoggedCallDto> calls = fetchCalls(terminalId,startDateTimeLogged,endDateTimeLogged,startDateTimeCompleted,endDateTimeCompleted);

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Logged Calls");
            String[] headers = { "S/N", "Branch Name", "Terminal ID", "Terminal Name", "Vendor", "Fault/Date and Time Logged",
            					"Contact Person","Starting Date","Date Completed","Status","SLA Deadline","SLA Breached",
            					"Backend Staff Email",
            					"Backend Staff Browser","Backend Staff Hostname","Backend Staff IP"};
            

            // Header row styling
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Data rows
            SimpleDateFormat fmtTime = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
            //SimpleDateFormat fmtDate = new SimpleDateFormat("dd MMM yyyy");
            int rowIdx = 1;
            for (LoggedCallDto call : calls) {
                Row row = sheet.createRow(rowIdx);
                row.createCell(0).setCellValue(rowIdx);
                row.createCell(1).setCellValue(call.getBranchName());
                row.createCell(2).setCellValue(call.getTerminalId());
                row.createCell(3).setCellValue(call.getTerminalName());
                row.createCell(4).setCellValue(call.getVendorName());
                row.createCell(5).setCellValue(
                    call.getIssueDesc() + " " + (fmtTime.format(call.getDateLogged()))
                );
                row.createCell(6).setCellValue(call.getBranchLogger()+" "+call.getLoggerPhone());
                row.createCell(7).setCellValue(
                		fmtTime.format(call.getStartingDate()));
                row.createCell(8).setCellValue(
                		call.getDateCompleted()!=null ? fmtTime.format(call.getDateCompleted()) : "Not Closed");
                row.createCell(9).setCellValue(call.getStatusDesc());
                row.createCell(10).setCellValue(
                		call.getSlaDeadline() != null ? fmtTime.format(call.getSlaDeadline()) : "");
                row.createCell(11).setCellValue(call.isSlaBreached() ? "Yes" : "No");
                row.createCell(12).setCellValue(call.getLoggerEmail());
                row.createCell(13).setCellValue(call.getLoggerBrowserUsed());
                row.createCell(14).setCellValue(call.getLoggerHostName());
                row.createCell(15).setCellValue(call.getLoggerIP());
                
                rowIdx++;
            }

            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            	//sheet.setColumnWidth(i, 20 * 256);
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
	}



	

}
