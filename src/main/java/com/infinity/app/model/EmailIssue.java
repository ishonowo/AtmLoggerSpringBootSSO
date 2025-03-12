package com.infinity.app.model;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



@Entity
	public class EmailIssue {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "emailIssues")
	@SequenceGenerator(name = "emailIssues", schema="dbo", sequenceName = "sq_emailIssues", allocationSize = 1)
	private Long id;
	
	@NotNull
	@Email
	private String fromEmail;
	
	@NotNull
	private String toEmail;
	
	@NotNull
	private String cc;
	
	@NotNull
	@Size(min = 10)
	private String subject;
	
	@NotNull
	private String mIntro;
	
	
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	private Message message;
	
	@NotNull
	private String  mEnd;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getmIntro() {
		return mIntro;
	}

	public void setmIntro(String mIntro) {
		this.mIntro = mIntro;
	}

	public Message getMessage() {
        return message;
    }
    
    public void setMessage(Message message) {
        this.message = message;
    }
    
	public String getmEnd() {
		return mEnd;
	}

	public void setmEnd(String mEnd) {
		this.mEnd = mEnd;
	}

	public EmailIssue() {}

	public EmailIssue(Long id, @NotNull @Email String fromEmail, @NotNull String toEmail, @NotNull String cc,
			@NotNull @Size(min = 10) String subject, @NotNull String mIntro, @NotNull Message message,
			@NotNull String mEnd) {
		super();
		this.id = id;
		this.fromEmail = fromEmail;
		this.toEmail = toEmail;
		this.cc = cc;
		this.subject = subject;
		this.mIntro = mIntro;
		this.message = message;
		this.mEnd = mEnd;
	}

	public EmailIssue(@NotNull @Email String fromEmail, @NotNull String toEmail, @NotNull String cc,
			@NotNull @Size(min = 10) String subject, @NotNull String mIntro, @NotNull Message message,
			@NotNull String mEnd) {
		super();
		this.fromEmail = fromEmail;
		this.toEmail = toEmail;
		this.cc = cc;
		this.subject = subject;
		this.mIntro = mIntro;
		this.message = message;
		this.mEnd = mEnd;
	}

	@Override
	public String toString() {
		return "EmailIssue [id=" + id + ", fromEmail=" + fromEmail + ", toEmail=" + toEmail + ", cc=" + cc
				+ ", subject=" + subject + ", mIntro=" + mIntro + ", message=" + message + ", mEnd=" + mEnd + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cc, fromEmail, id, mEnd, mIntro, message, subject, toEmail);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailIssue other = (EmailIssue) obj;
		return Objects.equals(cc, other.cc) && Objects.equals(fromEmail, other.fromEmail)
				&& Objects.equals(id, other.id) && Objects.equals(mEnd, other.mEnd)
				&& Objects.equals(mIntro, other.mIntro) && Objects.equals(message, other.message)
				&& Objects.equals(subject, other.subject) && Objects.equals(toEmail, other.toEmail);
	}

	

}
