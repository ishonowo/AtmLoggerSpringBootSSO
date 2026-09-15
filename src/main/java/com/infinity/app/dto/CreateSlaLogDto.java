package com.infinity.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateSlaLogDto {

    @NotNull(message = "faultId is required")
    private Long faultId;

    @NotBlank(message = "category is required")
    private String category; // must match an sla_rule.category, e.g. STANDARD / CRITICAL

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

	@Override
	public String toString() {
		return "CreateSlaLogDto [faultId=" + faultId + ", category=" + category + "]";
	}
    
    
}
