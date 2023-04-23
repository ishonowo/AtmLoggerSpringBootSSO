package com.infinity.app.mail;

import com.infinity.app.model.EmailIssue;

public interface EmailIssueSender {
	void sendEmail(EmailIssue emailIssue);
}
