package com.infinity.app.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.infinity.app.dto.CallWithStatusDto;
import com.infinity.app.dto.EmailIssueMessageDto;
import com.infinity.app.dto.LoggedCallDto;
import com.infinity.app.dto.LoggedInfo;
import com.infinity.app.model.LoggedCall;
import com.infinity.app.repo.CallStatusRepo;
import com.infinity.app.repo.LoggedCallRepo;


@Service
public class LoggedCallService {

	private final LoggedCallRepo loggedCallRepo;
	private final CallStatusRepo logStatusRepo;
	
	public LoggedCallService(LoggedCallRepo loggedCallRepo, CallStatusRepo logStatusRepo) {
		this.loggedCallRepo=loggedCallRepo;
		this.logStatusRepo=logStatusRepo;
	}
	
	
	public List<LoggedCallDto> findAllLoggedIssueDtos() {
        return loggedCallRepo.findAllLoggedIssueDtos()
            .stream()
            .map(projection -> new LoggedCallDto(
            	projection.getLogId(),
                projection.getBranchName(),
                projection.getTerminalId(),
                projection.getTerminalName(),
                projection.getVendorName(),
                projection.getIssueDesc(),
                projection.getDateLogged(),
                projection.getBranchLogger(),
                projection.getLoggerPhone(),
                projection.getStartingDate(),
                projection.getDateCompleted(),
                projection.getStatusDesc(),
                projection.getStatusId()
                ))
            .collect(Collectors.toList());
    }

	public LoggedCall save(LoggedCall loggedCall) {
		return loggedCallRepo.save(loggedCall);
	}
	
	public void saveObj(EmailIssueMessageDto dto,Long messageId) {
		loggedCallRepo.saveObj(dto.getSubject(),
				  dto.getBranchName(),
				  dto.getVendorName(),
				  messageId,
				  dto.getDateLogged(),null,1L);
		
		//return savedLoggedCall;
	}

	public void updateCall(LoggedCallDto updatedCall) {
		Long logId = updatedCall.getLogId();
		Long statusId= logStatusRepo.findIdByDesc(updatedCall.getStatusDesc());
		Date dateCompleted = new Date();
        loggedCallRepo.updateStatusAndDateCompleted(logId, statusId, dateCompleted);
    }

}
