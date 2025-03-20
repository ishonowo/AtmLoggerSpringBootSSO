package com.infinity.app.model;

import org.springframework.context.annotation.Lazy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Lazy(false)
@Table(name="log_status")
public class CallStatus {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull 
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

	public CallStatus(Long id, @NotNull String statusDesc) {
		this.id = id;
		this.statusDesc = statusDesc;
	}

	public CallStatus(@NotNull String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public CallStatus() {}

	@Override
	public String toString() {
		return "LogStatus [id=" + id + ", statusDesc=" + statusDesc + "]";
	}
	
	
	
}
