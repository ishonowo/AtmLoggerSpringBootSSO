package com.infinity.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.infinity.app.dto.LoggedCallDto;
import com.infinity.app.repo.LoggedCallRepo;

@Service
public class LoggedCallService {

	private final LoggedCallRepo loggedIssueRepo;
	
	public LoggedCallService(LoggedCallRepo loggedIssueRepo) {
		this.loggedIssueRepo=loggedIssueRepo;
	}
	
	public List<LoggedCallDto> findAllLoggedIssueDtos() {
        return loggedIssueRepo.findAllLoggedIssueDtos()
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

}
