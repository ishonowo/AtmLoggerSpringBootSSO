package com.infinity.app.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public class HoldRequestDto {

    @NotNull(message = "holdStartedAt is required")
    private LocalDateTime holdStartedAt;

    private String changedBy;
    private String notes;

    public LocalDateTime getHoldStartedAt() {
        return holdStartedAt;
    }

    public void setHoldStartedAt(LocalDateTime holdStartedAt) {
        this.holdStartedAt = holdStartedAt;
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
		return "HoldRequestDto [holdStartedAt=" + holdStartedAt + ", changedBy=" + changedBy + ", notes=" + notes + "]";
	}
    
    
}
