package com.infinity.app.model;


//import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Message {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String atmLocation;
    
    @NotNull
    private String branchName;
    
    @NotNull
    private String vendorName;
    
    @NotNull
    private String issueDesc;
    
    @NotNull
    private String branchLogger;
    
    @NotNull
    private String loggerPhone;
    
    @NotNull
    private Date dateLogged;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAtmLocation() {
		return atmLocation;
	}
	public void setAtmLocation(String atmLocation) {
		this.atmLocation = atmLocation;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getIssueDesc() {
		return issueDesc;
	}
	public void setIssueDesc(String issueDesc) {
		this.issueDesc = issueDesc;
	}
	public String getBranchLogger() {
		return branchLogger;
	}
	public void setBranchLogger(String branchLogger) {
		this.branchLogger = branchLogger;
	}
	public String getLoggerPhone() {
		return loggerPhone;
	}
	public void setLoggerPhone(String loggerPhone) {
		this.loggerPhone = loggerPhone;
	}
	public Date getDateLogged() {
		return dateLogged;
	}
	public void setDateLogged(Date dateLogged) {
		this.dateLogged = dateLogged;
	}
	
	public Message(Long id, @NotNull String atmLocation, @NotNull String branchName, @NotNull String vendorName, 
			@NotNull String issueDesc,
			@NotNull String branchLogger, @NotNull String loggerPhone, @NotNull Date dateLogged) {
		this.id = id;
		this.atmLocation = atmLocation;
		this.branchName = branchName;
		this.vendorName = vendorName;
		this.issueDesc = issueDesc;
		this.branchLogger = branchLogger;
		this.loggerPhone = loggerPhone;
		this.dateLogged = dateLogged;
	}
	
	public Message(@NotNull String atmLocation, @NotNull String branchName, @NotNull String vendorName, 
			@NotNull String issueDesc, @NotNull String branchLogger,
			@NotNull String loggerPhone, @NotNull Date dateLogged) {
		this.atmLocation = atmLocation;
		this.branchName = branchName;
		this.vendorName = vendorName;
		this.issueDesc = issueDesc;
		this.branchLogger = branchLogger;
		this.loggerPhone = loggerPhone;
		this.dateLogged = dateLogged;
	}
	
	public Message() {}
	@Override
	public String toString() {
		return "Message [id=" + id + ", atmLocation=" + atmLocation + ", branchName=" + branchName + ", vendorName="
				+ vendorName + ", issueDesc=" + issueDesc + ", branchLogger=" + branchLogger + ", loggerPhone="
				+ loggerPhone + ", dateLogged=" + dateLogged + "]";
	}
    
	
    
}
