package com.infinity.app.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.infinity.app.dto.EmailIssueMessageDto;
import com.infinity.app.dto.LoggedCallDto;
import com.infinity.app.model.LoggedCall;
import com.infinity.app.repo.LoggedCallRepo;


@Service
public class LoggedCallService {

	private final LoggedCallRepo loggedCallRepo;
	
	public LoggedCallService(LoggedCallRepo loggedCallRepo) {
		this.loggedCallRepo=loggedCallRepo;
	}
	
	
	public List<LoggedCallDto> findAllLoggedIssueDtos() {
        return 
        	loggedCallRepo.findAllLoggedIssueDtos()
            .stream()
            .map(projection -> new LoggedCallDto(
            	projection.getLogId(),
                projection.getBranchName(),
                projection.getTerminalId(),
                projection.getTerminalName(),
                projection.getVendorName(),
                projection.getIssueDesc(),
                projection.getDateLogged(),
                projection.getFromEmail(),
                projection.getBranchLogger(),
                projection.getLoggerPhone(),
                projection.getStartingDate(),
                projection.getDateCompleted(),
                projection.getBrowserUsed(),
                projection.getHostName(),
                projection.getLoggerIP(),
                projection.getStatusDesc(),
                projection.getStatusId()
                ))
            .collect(Collectors.toList());
    }

	public LoggedCall save(LoggedCall loggedCall) {
		return loggedCallRepo.save(loggedCall);
	}
	
	public void saveObj(EmailIssueMessageDto dto,Long messageId,String ip,String browser,String hostname) {
		loggedCallRepo.saveObj(dto.getSubject(),
				  dto.getBranchName(),
				  dto.getVendorName(),
				  messageId,dto.getDateLogged(),dto.getFromEmail(),
				  ip,browser,hostname,
				  null,1L);
		
		//return savedLoggedCall;
	}

	public void updateCall(LoggedCallDto updatedCall) {
        loggedCallRepo.updateStatusAndDateCompleted(updatedCall.getLogId(), updatedCall.getStatusId(),
        											updatedCall.getDateCompleted());
    }


	public ByteArrayInputStream exportToExcel()  throws IOException {
        List<LoggedCallDto> calls = loggedCallRepo.findAllLoggedIssueDtos().stream().map(projection -> new LoggedCallDto(
                    	projection.getLogId(),
                        projection.getBranchName(),
                        projection.getTerminalId(),
                        projection.getTerminalName(),
                        projection.getVendorName(),
                        projection.getIssueDesc(),
                        projection.getDateLogged(),
                        projection.getFromEmail(),
                        projection.getBranchLogger(),
                        projection.getLoggerPhone(),
                        projection.getStartingDate(),
                        projection.getDateCompleted(),
                        projection.getBrowserUsed(),
                        projection.getHostName(),
                        projection.getLoggerIP(),
                        projection.getStatusDesc(),
                        projection.getStatusId()
                        ))
                    .collect(Collectors.toList());

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Logged Calls");
            String[] headers = { "S/N", "Branch Name", "Terminal ID", "Terminal Name", "Vendor", "Fault/Date and Time Logged",
            					"Contact Person","Starting Date","Date Completed","Status","Backend Staff Email",
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
                row.createCell(10).setCellValue(call.getFromEmail());
                row.createCell(11).setCellValue(call.getBrowserUsed());
                row.createCell(12).setCellValue(call.getHostName());
                row.createCell(13).setCellValue(call.getLoggerIP());
                
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
