package com.infinity.app.dto;

import java.time.LocalDateTime;

public class SlaLogResponseDto {

    private Long id;
    private Long faultId;
    private String category;
    private Integer allowedHours;
    private String status;
    private LocalDateTime openedAt;
    private LocalDateTime closedAt;
    private LocalDateTime holdStartedAt;
    private Long totalHoldSeconds;
    private Integer reopenedCount;
    private LocalDateTime lastUpdatedAt;
    private LocalDateTime deadline;
    private boolean breached;

    public SlaLogResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFaultId() {
        return faultId;
    }

    public void setFaultId(Long faultId) {
        this.faultId = faultId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getAllowedHours() {
        return allowedHours;
    }

    public void setAllowedHours(Integer allowedHours) {
        this.allowedHours = allowedHours;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getOpenedAt() {
        return openedAt;
    }

    public void setOpenedAt(LocalDateTime openedAt) {
        this.openedAt = openedAt;
    }

    public LocalDateTime getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(LocalDateTime closedAt) {
        this.closedAt = closedAt;
    }

    public LocalDateTime getHoldStartedAt() {
        return holdStartedAt;
    }

    public void setHoldStartedAt(LocalDateTime holdStartedAt) {
        this.holdStartedAt = holdStartedAt;
    }

    public Long getTotalHoldSeconds() {
        return totalHoldSeconds;
    }

    public void setTotalHoldSeconds(Long totalHoldSeconds) {
        this.totalHoldSeconds = totalHoldSeconds;
    }

    public Integer getReopenedCount() {
        return reopenedCount;
    }

    public void setReopenedCount(Integer reopenedCount) {
        this.reopenedCount = reopenedCount;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public boolean isBreached() {
        return breached;
    }

    public void setBreached(boolean breached) {
        this.breached = breached;
    }
}
