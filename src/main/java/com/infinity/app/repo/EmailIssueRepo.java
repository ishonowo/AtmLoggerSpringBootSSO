package com.infinity.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infinity.app.model.EmailIssue;

public interface EmailIssueRepo extends JpaRepository<EmailIssue, Long> {

}
