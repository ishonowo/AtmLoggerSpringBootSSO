package com.infinity.app.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "sla_log")
public class SlaLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fault_id", nullable = false)
    private AtmFault fault;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sla_rule_id", nullable = false)
    private SlaRule slaRule;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SlaStatus status = SlaStatus.PENDING;

    @Column(name = "opened_at", nullable = false)
    private LocalDateTime openedAt;

    @Column(name = "closed_at")
    private LocalDateTime closedAt;

    @Column(name = "hold_started_at")
    private LocalDateTime holdStartedAt;

    @Column(name = "total_hold_seconds", nullable = false)
    private Long totalHoldSeconds = 0L;

    @Column(name = "reopened_count", nullable = false)
    private Integer reopenedCount = 0;

    @Column(name = "last_updated_at", nullable = false)
    private LocalDateTime lastUpdatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        if (openedAt == null) {
            openedAt = now;
        }
        lastUpdatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AtmFault getFault() {
        return fault;
    }

    public void setFault(AtmFault fault) {
        this.fault = fault;
    }

    public SlaRule getSlaRule() {
        return slaRule;
    }

    public void setSlaRule(SlaRule slaRule) {
        this.slaRule = slaRule;
    }

    public SlaStatus getStatus() {
        return status;
    }

    public void setStatus(SlaStatus status) {
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

	@Override
	public String toString() {
		return "SlaLog [id=" + id + ", fault=" + fault + ", slaRule=" + slaRule + ", status=" + status + ", openedAt="
				+ openedAt + ", closedAt=" + closedAt + ", holdStartedAt=" + holdStartedAt + ", totalHoldSeconds="
				+ totalHoldSeconds + ", reopenedCount=" + reopenedCount + ", lastUpdatedAt=" + lastUpdatedAt + "]";
	}
    
    
}
