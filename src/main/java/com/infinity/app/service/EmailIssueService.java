package com.infinity.app.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import com.infinity.app.mail.EmailIssueSender;
import com.infinity.app.model.EmailIssue;
import com.infinity.app.repo.EmailIssueRepo;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class EmailIssueService implements EmailIssueSender {

	private final JavaMailSenderImpl mailSender;

	private final EmailIssueRepo emailRepo;
	
	private final Configuration freemarkerConfig;

	
	
	public EmailIssueService(Environment environment, JavaMailSenderImpl mailSender, EmailIssueRepo emailRepo,
							Configuration freemarkerConfig) {
		this.freemarkerConfig= freemarkerConfig;
		this.mailSender= mailSender;
		this.emailRepo=emailRepo;
		mailSender = new JavaMailSenderImpl();

		mailSender.setHost(environment.getProperty("spring.mail.host"));
		mailSender.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));
		mailSender.setUsername(environment.getProperty("spring.mail.username"));
		mailSender.setPassword(environment.getProperty("spring.mail.password"));
	}

	@Override
    public void sendEmail(EmailIssue emailGen){
		EmailIssue emailIssue=emailRepo.save(emailGen);
		try {
		
		MimeMessage msg = mailSender.createMimeMessage();
    	//MimeMessageHelper helper = new MimeMessageHelper(msg, true);
    	MimeMessageHelper helper = new MimeMessageHelper(msg,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);
    	
    	Map<String,EmailIssue> emailIssueModel= new HashMap<String, EmailIssue>();
    	emailIssueModel.put("emailIssue",emailIssue);
    	helper.setFrom(emailIssue.getFromEmail());
    	helper.setTo(emailFormatter(emailIssue.getToEmail()));
    	helper.setCc(emailFormatter(emailIssue.getCc()));
        helper.setSubject(emailIssue.getSubject());

    	Template t;
    	String htmlBody="";
		try {
			t = freemarkerConfig.getTemplate("email-template.ftl");
			htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(t, emailIssueModel);
		} catch (IOException  | TemplateException e) {
			// TODO Auto-generated   block
			e.printStackTrace();
		}
		System.out.println("The template is" + htmlBody);
		
        helper.setText(htmlBody, true);
        //helper.setText(emailIssue.getBody(),true);
        mailSender.send(msg);
    	} catch(MessagingException ex){
    		Logger.getLogger(EmailIssueService.class.getName()).log(Level.SEVERE, null, ex);
    	}
    }

	private String[] emailFormatter(String emailString) {

		String[] emailsArray = emailString.split(";");
        
        // Trim whitespace from each email address
        for (int i = 0; i < emailsArray.length; i++) {
            emailsArray[i] = emailsArray[i].trim();
        }
        
        return emailsArray;
	}

	

}
