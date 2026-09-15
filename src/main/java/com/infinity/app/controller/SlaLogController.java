package com.infinity.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infinity.app.dto.CreateSlaLogDto;
import com.infinity.app.dto.HoldRequestDto;
import com.infinity.app.dto.ResolveRequestDto;
import com.infinity.app.dto.ResumeRequestDto;
import com.infinity.app.dto.SlaLogResponseDto;
import com.infinity.app.service.SlaLogService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sla-logs")
@CrossOrigin(origins = "*")
public class SlaLogController {

    private final SlaLogService slaLogService;

    public SlaLogController(SlaLogService slaLogService) {
        this.slaLogService = slaLogService;
    }

    @PostMapping
    public ResponseEntity<SlaLogResponseDto> create(@Valid @RequestBody CreateSlaLogDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(slaLogService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<SlaLogResponseDto>> getAll() {
        return ResponseEntity.ok(slaLogService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlaLogResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(slaLogService.getById(id));
    }

    @PutMapping("/{id}/hold")
    public ResponseEntity<SlaLogResponseDto> putOnHold(@PathVariable Long id,
            @Valid @RequestBody HoldRequestDto dto) {
        return ResponseEntity.ok(slaLogService.putOnHold(id, dto));
    }

    @PutMapping("/{id}/resume")
    public ResponseEntity<SlaLogResponseDto> resume(@PathVariable Long id,
            @Valid @RequestBody ResumeRequestDto dto) {
        return ResponseEntity.ok(slaLogService.resumeFromHold(id, dto));
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<SlaLogResponseDto> resolve(@PathVariable Long id,
            @RequestBody(required = false) ResolveRequestDto dto) {
        return ResponseEntity.ok(slaLogService.resolve(id, dto != null ? dto : new ResolveRequestDto()));
    }

    @PutMapping("/{id}/reopen")
    public ResponseEntity<SlaLogResponseDto> reopen(@PathVariable Long id,
            @RequestBody(required = false) ResolveRequestDto dto) {
        return ResponseEntity.ok(slaLogService.reopen(id, dto));
    }
}
