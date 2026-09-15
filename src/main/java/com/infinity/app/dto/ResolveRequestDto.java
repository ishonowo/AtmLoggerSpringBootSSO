package com.infinity.app.dto;

import java.time.LocalDateTime;

public class ResolveRequestDto {

    // Optional: caller can backdate the resolution time; defaults to "now" in
    // the service layer if omitted.
    private LocalDateTime closedAt;

    private String changedBy;
    private String notes;

    public LocalDateTime getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(LocalDateTime closedAt) {
        this.closedAt = closedAt;
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
		return "ResolveRequestDto [closedAt=" + closedAt + ", changedBy=" + changedBy + ", notes=" + notes + "]";
	}
    
    
}
