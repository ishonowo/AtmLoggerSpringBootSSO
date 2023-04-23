package com.infinity.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.CrossOrigin;



/*@SqlResultSetMappings(value = {
	    @SqlResultSetMapping(name = "AtmDetailResult",
	        classes = { @ConstructorResult(targetClass = com.infinity.app.dto.AtmDetail.class,
	        columns = { @ColumnResult(name = "branch_email", type = String.class),
	        		@ColumnResult(name = "branch_name", type = String.class),
	        		@ColumnResult(name = "terminal_id", type = String.class),
	        		@ColumnResult(name = "atm_name", type = String.class),
	        		@ColumnResult(name = "physical_address", type = String.class),
	        		@ColumnResult(name = "contact", type = String.class),
                    @ColumnResult(name = "vendor_name", type = String.class) }) })
 })

@NamedNativeQuery(name ="AtmDetail.getAtmDetail",
	        query = "select a.branch_email, a.branch_name, c.terminal_id, c.atm_name, a.physical_address, b.contact, b.vendor_name "
	    			+ " from branch_info a (nolock) join vendor_terminal c (nolock) on a.sol_id = SUBSTRING(c.terminal_id,5,3)"
	    			+ " join vendor_contact b (nolock) on b.vendor_name = c.vendor_name "
	    			+ " and c.terminal_id = ?1",
	        resultSetMapping = "AtmDetailResult")

*/


@Entity
@Lazy(false)
@CrossOrigin(origins="http://localhost:4200")
public class AtmDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name = "atmDetail", schema="dbo", sequenceName = "sq_atmDetail", allocationSize = 1)
	private Long id;
	
	@Email
	@NotNull
	private String branchEmail;
	
	@NotNull
	private String branchName;
	
	@NotNull
	@Size(min=8, max=8)
	private String terminalId;
	
	@NotNull
	private String atmName;
	
	private String physicalAddress;
	
	@NotNull
	private String contact;
	
	@NotNull
	private String vendorName;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getBranchEmail() {
		return branchEmail;
	}
	public void setBranchEmail(String branchEmail) {
		this.branchEmail = branchEmail;
	}
	
	public String getBranchName() {
		return branchName;
	}
	
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	
	public String getAtmName() {
		return atmName;
	}
	public void setAtmName(String atmName) {
		this.atmName = atmName;
	}
	
	public String getPhysicalAddress() {
		return physicalAddress;
	}
	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}
	
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	public AtmDetail(String branchEmail, String branchName, String terminalId, String atmName,
			String physicalAddress, String contact, String vendorName) {
		super();
		this.branchEmail = branchEmail;
		this.branchName = branchName;
		this.terminalId = terminalId;
		this.atmName = atmName;
		this.physicalAddress = physicalAddress;
		this.contact = contact;
		this.vendorName = vendorName;
	}
	
	public AtmDetail(Long id, String branchEmail, String branchName, String terminalId, String atmName,
			String physicalAddress, String contact, String vendorName) {
		super();
		this.id= id;
		this.branchEmail = branchEmail;
		this.branchName = branchName;
		this.terminalId = terminalId;
		this.atmName = atmName;
		this.physicalAddress = physicalAddress;
		this.contact = contact;
		this.vendorName = vendorName;
	}
	
	public AtmDetail() {
		super();
	}
	
	@Override
	public String toString() {
		return "AtmDetail [ id" + id + ", branchEmail=" + branchEmail + ", branchName=" + branchName + ", terminalId=" + terminalId
				+ ", atmName=" + atmName + ", physicalAddress=" + physicalAddress + ", contact=" + contact
				+ ", vendorName=" + vendorName + "]";
	}
	
}


