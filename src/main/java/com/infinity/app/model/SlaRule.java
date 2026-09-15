package com.infinity.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sla_rule")
public class SlaRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String category;

    @Column(name = "allowed_hours", nullable = false)
    private Integer allowedHours;

    @Column(length = 255)
    private String description;

    public SlaRule() {
    }

    public SlaRule(String category, Integer allowedHours, String description) {
        this.category = category;
        this.allowedHours = allowedHours;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
