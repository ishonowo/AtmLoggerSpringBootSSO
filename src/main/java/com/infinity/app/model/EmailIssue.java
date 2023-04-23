package com.infinity.app.model;

//import java.util.Arrays;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.bind.annotation.CrossOrigin;

//import org.springframework.beans.factory.annotation.Autowired;

@Entity
@CrossOrigin(origins="http://localhost:4200")
public class EmailIssue {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name = "emailIssues", schema="dbo", sequenceName = "sq_emailIssues", allocationSize = 1)
	private Long id;
	
	@NotNull
	@Email
	private String fromEmail;
	
	@NotNull
	@ElementCollection
	private List<String> toEmail;
	
	@NotNull
	@ElementCollection
	private List<String> cc;
	
	@NotNull
	@Size(min = 10)
	private String subject;
	
	@NotNull
	private String mIntro;
	
	@NotNull
	@ElementCollection
	private List<String> mHeader;
	
	@NotNull
	@ElementCollection
	private List<String> mBody;
	
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

	public List<String> getToEmail() {
		return toEmail;
	}

	public void setToEmail(List<String> toEmail) {
		this.toEmail = toEmail;
	}

	public List<String> getCc() {
		return cc;
	}

	public void setCc(List<String> cc) {
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

	public List<String> getmHeader() {
		return mHeader;
	}

	public void setmHeader(List<String> mHeader) {
		this.mHeader = mHeader;
	}

	public List<String> getmBody() {
		return mBody;
	}

	public void setmBody(List<String> mBody) {
		this.mBody = mBody;
	}

	public String getmEnd() {
		return mEnd;
	}

	public void setmEnd(String mEnd) {
		this.mEnd = mEnd;
	}

	public EmailIssue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmailIssue(Long id, @NotNull @Email String fromEmail, @NotNull List<String> toEmail,
			@NotNull List<String> cc, @NotNull @Size(min = 10) String subject, @NotNull String mIntro,
			@NotNull List<String> mHeader, @NotNull List<String> mBody, @NotNull String mEnd) {
		super();
		this.id = id;
		this.fromEmail = fromEmail;
		this.toEmail = toEmail;
		this.cc = cc;
		this.subject = subject;
		this.mIntro = mIntro;
		this.mHeader = mHeader;
		this.mBody = mBody;
		this.mEnd = mEnd;
	}


	public EmailIssue(@NotNull @Email String fromEmail, @NotNull List<String> toEmail,
			@NotNull List<String> cc, @NotNull @Size(min = 10) String subject, @NotNull String mIntro,
			@NotNull List<String> mHeader, @NotNull List<String> mBody, @NotNull String mEnd) {
		super();
		this.fromEmail = fromEmail;
		this.toEmail = toEmail;
		this.cc = cc;
		this.subject = subject;
		this.mIntro = mIntro;
		this.mHeader = mHeader;
		this.mBody = mBody;
		this.mEnd = mEnd;
	}

	@Override
	public String toString() {
		return "EmailIssue [id=" + id + ", fromEmail=" + fromEmail + ", toEmail=" + toEmail + ", cc=" + cc
				+ ", subject=" + subject + ", mIntro=" + mIntro + ", mHeader=" + mHeader + ", mBody=" + mBody
				+ ", mEnd=" + mEnd + "]";
	}

	public String[] getToEmailArray() {
		String[] toEmailArray=new String[toEmail.size()];
		return toEmail.toArray(toEmailArray);
	}	
	
	
	public String[] getCcArray() {
		String[] ccArray=new String[cc.size()];
		return cc.toArray(ccArray);
	}	
	
	
	public String getBody() {
		
		String tableHead=""; 
		for(String h : mHeader) { 
			tableHead+= "<th>"+h+"</th>";
		}
		String tableBody="";
		for(String b :mBody) {
			tableBody+="<th>"+b+"</th>";
		}
		return "<p>"+ mIntro+"</p><table><thead><tr>"+ tableHead
		        +"</tr></thead><tbody><tr>"+tableBody +"</tbody></table><br/><p>"+mEnd+"</p>";
	}
	

}
