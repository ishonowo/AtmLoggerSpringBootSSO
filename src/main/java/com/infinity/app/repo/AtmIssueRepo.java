package com.infinity.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infinity.app.model.AtmIssue;

public interface AtmIssueRepo extends JpaRepository<AtmIssue, Long> {

}
