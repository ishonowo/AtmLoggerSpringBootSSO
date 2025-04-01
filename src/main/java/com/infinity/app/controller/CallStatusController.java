package com.infinity.app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infinity.app.model.CallStatus;
import com.infinity.app.service.CallStatusService;
import com.infinity.app.service.LoggedCallService;

@RestController
@RequestMapping("/api/status")
public class CallStatusController {

private final CallStatusService logService;
	
	public CallStatusController(CallStatusService logService) {
		this.logService=logService;
	}
	
	@GetMapping
	public ResponseEntity<List<CallStatus>> getAllCallWithStatusDtos() {
        List<CallStatus> logStatus = logService.findAllLogStatus();
        System.out.println(logStatus);
        return ResponseEntity.ok(logStatus);
    }
}
