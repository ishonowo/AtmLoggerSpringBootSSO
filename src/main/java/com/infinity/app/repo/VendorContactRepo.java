package com.infinity.app.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infinity.app.model.VendorContact;

@Repository
public interface VendorContactRepo extends JpaRepository<VendorContact, Long> {
	public interface VendorContactProjection {
		Long getVendorId();
	    String getContact();
	    Boolean getStatus();
	    Long getId();
		String getVendorName();
	}

	@Query(value = "SELECT v.id as vendorId, c.contact as contact,"
			+ " c.status as status,c.id as id, v.vendor_name as vendorName " +
	       "FROM vendors v " +
	       "JOIN vendor_contacts c ON v.id = c.vendor_id;",
	       nativeQuery = true)
	public List<VendorContactProjection> findAllVendorContactObject();
}