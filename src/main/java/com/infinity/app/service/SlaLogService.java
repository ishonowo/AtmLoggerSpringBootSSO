package com.infinity.app.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.infinity.app.dto.CreateSlaLogDto;
import com.infinity.app.dto.HoldRequestDto;
import com.infinity.app.dto.ResolveRequestDto;
import com.infinity.app.dto.ResumeRequestDto;
import com.infinity.app.dto.SlaLogResponseDto;
import com.infinity.app.model.AtmFault;
import com.infinity.app.model.SlaLog;
import com.infinity.app.model.SlaRule;
import com.infinity.app.model.SlaStatus;
import com.infinity.app.model.SlaStatusHistory;
import com.infinity.app.repo.AtmFaultRepo;
import com.infinity.app.repo.SlaLogRepository;
import com.infinity.app.repo.SlaRuleRepository;
import com.infinity.app.repo.SlaStatusHistoryRepository;

@Service
public class SlaLogService {

    private final SlaLogRepository slaLogRepo;
    private final SlaRuleRepository slaRuleRepo;
    private final SlaStatusHistoryRepository historyRepo;
    private final AtmFaultRepo atmFaultRepo;

    public SlaLogService(SlaLogRepository slaLogRepo, SlaRuleRepository slaRuleRepo,
            SlaStatusHistoryRepository historyRepo, AtmFaultRepo atmFaultRepo) {
        this.slaLogRepo = slaLogRepo;
        this.slaRuleRepo = slaRuleRepo;
        this.historyRepo = historyRepo;
        this.atmFaultRepo = atmFaultRepo;
    }

    public SlaLogResponseDto create(CreateSlaLogDto dto) {
        AtmFault fault = atmFaultRepo.findById(dto.getFaultId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fault not found"));

        SlaRule rule = slaRuleRepo.findByCategory(dto.getCategory())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Unknown SLA category: " + dto.getCategory()));

        SlaLog log = new SlaLog();
        log.setFault(fault);
        log.setSlaRule(rule);
        log.setStatus(SlaStatus.PENDING);
        log.setOpenedAt(LocalDateTime.now());

        SlaLog saved = slaLogRepo.save(log);
        recordHistory(saved, null, SlaStatus.PENDING.name(), "system", "Log opened");

        return toDto(saved);
    }

    public List<SlaLogResponseDto> getAll() {
        return slaLogRepo.findAll().stream().map(this::toDto).toList();
    }

    public SlaLogResponseDto getById(Long id) {
        return toDto(findOrThrow(id));
    }

    public SlaLogResponseDto putOnHold(Long id, HoldRequestDto dto) {
        SlaLog log = findOrThrow(id);

        if (log.getStatus() == SlaStatus.RESOLVED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot hold a resolved log");
        }
        if (dto.getHoldStartedAt().isAfter(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hold start time cannot be in the future");
        }
        if (dto.getHoldStartedAt().isBefore(log.getOpenedAt())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Hold start time cannot be before the log was opened");
        }

        String oldStatus = log.getStatus().name();
        log.setStatus(SlaStatus.ON_HOLD);
        log.setHoldStartedAt(dto.getHoldStartedAt());

        SlaLog saved = slaLogRepo.save(log);
        recordHistory(saved, oldStatus, SlaStatus.ON_HOLD.name(), dto.getChangedBy(), dto.getNotes());

        return toDto(saved);
    }

    public SlaLogResponseDto resumeFromHold(Long id, ResumeRequestDto dto) {
        SlaLog log = findOrThrow(id);

        if (log.getStatus() != SlaStatus.ON_HOLD) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Log is not currently on hold");
        }
        if (log.getHoldStartedAt() == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Log has no recorded hold start time");
        }
        if (dto.getHoldEndedAt().isBefore(log.getHoldStartedAt())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Hold end time cannot be before hold start time");
        }

        long secondsPaused = Duration.between(log.getHoldStartedAt(), dto.getHoldEndedAt()).getSeconds();
        log.setTotalHoldSeconds(log.getTotalHoldSeconds() + secondsPaused);
        log.setHoldStartedAt(null);

        String oldStatus = log.getStatus().name();
        log.setStatus(SlaStatus.PENDING);

        SlaLog saved = slaLogRepo.save(log);
        recordHistory(saved, oldStatus, SlaStatus.PENDING.name(), dto.getChangedBy(), dto.getNotes());

        return toDto(saved);
    }

    public SlaLogResponseDto resolve(Long id, ResolveRequestDto dto) {
        SlaLog log = findOrThrow(id);

        if (log.getStatus() == SlaStatus.RESOLVED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Log is already resolved");
        }
        if (log.getStatus() == SlaStatus.ON_HOLD) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Resume the log from hold before resolving it");
        }

        LocalDateTime closedAt = dto.getClosedAt() != null ? dto.getClosedAt() : LocalDateTime.now();
        if (closedAt.isBefore(log.getOpenedAt())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Closed time cannot be before the log was opened");
        }

        String oldStatus = log.getStatus().name();
        log.setStatus(SlaStatus.RESOLVED);
        log.setClosedAt(closedAt);

        SlaLog saved = slaLogRepo.save(log);
        recordHistory(saved, oldStatus, SlaStatus.RESOLVED.name(), dto.getChangedBy(), dto.getNotes());

        return toDto(saved);
    }

    public SlaLogResponseDto reopen(Long id, ResolveRequestDto dto) {
        SlaLog log = findOrThrow(id);

        if (log.getStatus() != SlaStatus.RESOLVED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only a resolved log can be reopened");
        }

        String oldStatus = log.getStatus().name();
        log.setStatus(SlaStatus.REOPENED);
        log.setClosedAt(null);
        log.setReopenedCount(log.getReopenedCount() + 1);

        SlaLog saved = slaLogRepo.save(log);
        recordHistory(saved, oldStatus, SlaStatus.REOPENED.name(), dto == null ? null : dto.getChangedBy(),
                dto == null ? null : dto.getNotes());

        return toDto(saved);
    }

    private SlaLog findOrThrow(Long id) {
        return slaLogRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SLA log not found: " + id));
    }

    private void recordHistory(SlaLog log, String oldStatus, String newStatus, String changedBy, String notes) {
        historyRepo.save(new SlaStatusHistory(log, oldStatus, newStatus, changedBy, notes));
    }

    // Converts the entity to a response DTO, computing the live deadline and
    // breach flag. Any hold time still in progress (status == ON_HOLD) is
    // added on top of totalHoldSeconds so the deadline reflects the pause
    // even before the hold is resumed.
    private SlaLogResponseDto toDto(SlaLog log) {
        SlaLogResponseDto dto = new SlaLogResponseDto();
        dto.setId(log.getId());
        dto.setFaultId(log.getFault().getId());
        dto.setCategory(log.getSlaRule().getCategory());
        dto.setAllowedHours(log.getSlaRule().getAllowedHours());
        dto.setStatus(log.getStatus().name());
        dto.setOpenedAt(log.getOpenedAt());
        dto.setClosedAt(log.getClosedAt());
        dto.setHoldStartedAt(log.getHoldStartedAt());
        dto.setTotalHoldSeconds(log.getTotalHoldSeconds());
        dto.setReopenedCount(log.getReopenedCount());
        dto.setLastUpdatedAt(log.getLastUpdatedAt());

        long effectiveHoldSeconds = log.getTotalHoldSeconds();
        if (log.getStatus() == SlaStatus.ON_HOLD && log.getHoldStartedAt() != null) {
            effectiveHoldSeconds += Duration.between(log.getHoldStartedAt(), LocalDateTime.now()).getSeconds();
        }

        LocalDateTime deadline = log.getOpenedAt()
                .plusHours(log.getSlaRule().getAllowedHours())
                .plusSeconds(effectiveHoldSeconds);
        dto.setDeadline(deadline);

        boolean breached;
        if (log.getStatus() == SlaStatus.RESOLVED) {
            breached = log.getClosedAt() != null && log.getClosedAt().isAfter(deadline);
        } else {
            breached = LocalDateTime.now().isAfter(deadline);
        }
        dto.setBreached(breached);

        return dto;
    }
}
