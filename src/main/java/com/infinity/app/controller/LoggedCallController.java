package com.infinity.app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infinity.app.dto.LoggedCallDto;
import com.infinity.app.service.LoggedCallService;

@RestController
@RequestMapping("/api/logs")
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

}
