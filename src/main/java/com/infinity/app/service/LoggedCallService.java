package com.infinity.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.infinity.app.dto.EmailIssueMessageDto;
import com.infinity.app.dto.LoggedCallDto;
import com.infinity.app.dto.LoggedInfo;
import com.infinity.app.model.LoggedCall;
import com.infinity.app.repo.LoggedCallRepo;


@Service
public class LoggedCallService {

	private final LoggedCallRepo loggedCallRepo;
	
	public LoggedCallService(LoggedCallRepo loggedCallRepo) {
		this.loggedCallRepo=loggedCallRepo;
	}
	
	public List<LoggedCallDto> findAllLoggedIssueDtos() {
        return loggedCallRepo.findAllLoggedIssueDtos()
            .stream()
            .map(projection -> new LoggedCallDto(
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
                projection.getStatus()
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

}
