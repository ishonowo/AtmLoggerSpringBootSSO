package com.infinity.app.service;

import java.util.List;
import java.util.stream.Collectors;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infinity.app.model.AtmIssue;
import com.infinity.app.repo.AtmIssueRepo;


@Service
public class AtmIssueService {
	
	private final AtmIssueRepo issueRepo;
	private final AtmDetailService atmService;
	
	public AtmIssueService(AtmIssueRepo issueRepo,AtmDetailService atmService) {
		this.issueRepo=issueRepo;
		this.atmService=atmService;
	}

	public AtmIssue save(AtmIssue atmIssueGen) {
		return issueRepo.save(atmIssueGen);
	}
	
	public String getContacts(String terminal_id){
		List<String> results=atmService.getActiveContacts(terminal_id);
		String atmContacts= results.stream().collect(Collectors.joining(";"));
		return atmContacts;
	}

	public void deleteById(Long id) {
		// delete the issue logged because the user wants to restart
		issueRepo.deleteById(id);
		
	}

	
}
