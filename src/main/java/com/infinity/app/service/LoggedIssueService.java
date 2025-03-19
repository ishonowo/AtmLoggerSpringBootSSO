package com.infinity.app.service;

import java.util.List;
import java.util.stream.Collectors;

import com.infinity.app.dto.LoggedIssueDto;
import com.infinity.app.repo.LoggedIssueRepo;

public class LoggedIssueService {

	private final LoggedIssueRepo loggedIssueRepo;
	
	public LoggedIssueService(LoggedIssueRepo loggedIssueRepo) {
		this.loggedIssueRepo=loggedIssueRepo;
	}
	
	public List<LoggedIssueDto> findAllLoggedIssueDtos() {
        return loggedIssueRepo.findAllLoggedIssueDtos()
            .stream()
            .map(projection -> new LoggedIssueDto(
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
