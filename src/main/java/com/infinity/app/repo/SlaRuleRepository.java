package com.infinity.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infinity.app.model.SlaRule;

public interface SlaRuleRepository extends JpaRepository<SlaRule, Integer> {
    Optional<SlaRule> findByCategory(String category);
}
