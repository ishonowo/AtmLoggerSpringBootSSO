package com.infinity.app.service;

import java.util.List;
import java.util.stream.Collectors;

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
                //projection.getLoggerEmail(),
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

}
