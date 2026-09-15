package com.infinity.app.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public class ResumeRequestDto {

    @NotNull(message = "holdEndedAt is required")
    private LocalDateTime holdEndedAt;

    private String changedBy;
    private String notes;

    public LocalDateTime getHoldEndedAt() {
        return holdEndedAt;
    }

    public void setHoldEndedAt(LocalDateTime holdEndedAt) {
        this.holdEndedAt = holdEndedAt;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

	@Override
	public String toString() {
		return "ResumeRequestDto [holdEndedAt=" + holdEndedAt + ", changedBy=" + changedBy + ", notes=" + notes + "]";
	}
    
    
}
