package com.infinity.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "log_status")
public class LogStatus {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String statusDesc;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public LogStatus(Long id, String statusDesc) {
		this.id = id;
		this.statusDesc = statusDesc;
	}

	public LogStatus() {}

	@Override
	public String toString() {
		return "LogStatus [id=" + id + ", statusDesc=" + statusDesc + "]";
	}
	
	
	
}
