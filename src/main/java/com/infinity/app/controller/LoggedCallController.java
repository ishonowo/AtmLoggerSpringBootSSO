package com.infinity.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infinity.app.dto.BranchWithName;
import com.infinity.app.dto.CallWithStatusDto;
import com.infinity.app.dto.LoggedCallDto;
import com.infinity.app.model.BranchInfo;
import com.infinity.app.model.LoggedCall;
import com.infinity.app.service.LoggedCallService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/calls")
public class LoggedCallController {
	
	private final LoggedCallService loggedService;
	
	public LoggedCallController(LoggedCallService loggedService) {
		this.loggedService=loggedService;
	}
	
	@GetMapping
	public ResponseEntity<List<LoggedCallDto>> getAllLoggedIssueDtos() {
        List<LoggedCallDto> loggedIssueDto = loggedService.findAllLoggedIssueDtos();
        System.out.println(loggedIssueDto);
        return ResponseEntity.ok(loggedIssueDto);
    }
	
	/*@GetMapping("/path")
	public ResponseEntity<List<CallWithStatusDto>> getAllCallWithStatusDtos() {
        List<CallWithStatusDto> callWithStatusDtos = loggedService.findAllCallWithStatusDtos();
        System.out.println(callWithStatusDtos);
        return ResponseEntity.ok(callWithStatusDtos);
    }*/
	
    @PostMapping
    public ResponseEntity<LoggedCall> createLoggedIssue(@Valid @RequestBody LoggedCall loggedCall) {
    	LoggedCall savedLoggedCall = loggedService.save(loggedCall);
        return new ResponseEntity<>(savedLoggedCall, HttpStatus.CREATED);
    }
	
	@PutMapping
    public ResponseEntity<Void> updateLoggedCall(@RequestBody LoggedCallDto updatedCall) {
        loggedService.updateCall(updatedCall);
        return ResponseEntity.noContent().build();
    }

}
