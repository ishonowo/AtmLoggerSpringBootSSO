package com.infinity.app.controller;
import java.io.IOException;
//import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.io.ByteArrayInputStream;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.infinity.app.dto.LoggedCallDto;
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

	// --- SLA additions ---

	@PutMapping("/{id}/hold")
	public ResponseEntity<Void> putOnHold(@PathVariable Long id, @RequestBody HoldRequest request) {
		loggedService.putOnHold(id, request.holdStart());
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}/resume")
	public ResponseEntity<Void> resumeFromHold(@PathVariable Long id, @RequestBody ResumeRequest request) {
		loggedService.resumeFromHold(id, request.holdEnd());
		return ResponseEntity.noContent().build();
	}

	// Small request payloads, kept local to this controller since they're
	// only used here. Move to their own files under dto/ if reused elsewhere.
	public record HoldRequest(Date holdStart) {}
	public record ResumeRequest(Date holdEnd) {}

	@GetMapping("/export")
	public ResponseEntity<InputStreamResource> exportLoggedCalls() throws IOException {
	    ByteArrayInputStream stream = loggedService.exportToExcel();
	    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
	    String filename = "logged-calls-" + timestamp + ".xlsx";
	    ContentDisposition disposition = ContentDisposition.attachment()
	            .filename(filename)
	            .build();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentDisposition(disposition);
	    headers.setCacheControl("no-store, no-cache, must-revalidate, max-age=0");
	    headers.setPragma("no-cache");
	    return ResponseEntity.ok()
	            .headers(headers)
	            .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
	            .body(new InputStreamResource(stream));
	}
}
