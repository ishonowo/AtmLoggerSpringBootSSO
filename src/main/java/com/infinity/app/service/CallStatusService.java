package com.infinity.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.infinity.app.model.CallStatus;
import com.infinity.app.repo.CallStatusRepo;



@Service
public class CallStatusService {
	
	private final CallStatusRepo repo;
	
	public CallStatusService(CallStatusRepo repo) {
		this.repo=repo;
	}

	public Long getIdByDesc(String desc) {
        return repo.findIdByDesc(desc);
    }

	public List<CallStatus> findAllLogStatus() {
		
		return repo.findAll();
	}	
	
}
