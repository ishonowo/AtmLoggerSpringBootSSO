package com.infinity.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infinity.app.model.AtmIssue;
import com.infinity.app.repo.AtmIssueRepo;


@Service
public class AtmIssueService {
	@Autowired
	private AtmIssueRepo issueRepo;

	public AtmIssue save(AtmIssue atmIssueGen) {
		return issueRepo.save(atmIssueGen);
	}

	public void deleteById(Long id) {
		// delete the issue logged because the user wants to restart
		issueRepo.deleteById(id);
		
	}

	
}
