package com.infinity.app.repo;

import org.springframework.data.repository.CrudRepository;

import com.infinity.app.model.EmailIssue;

public interface EmailIssueRepo extends CrudRepository<EmailIssue, Long> {

}
