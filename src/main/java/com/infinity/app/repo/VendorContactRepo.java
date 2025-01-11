package com.infinity.app.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infinity.app.model.VendorContact;

@Repository
public interface VendorContactRepo extends JpaRepository<VendorContact, Long> {
	public interface VendorContactProjection {
	    String getVendorName();
	    String getContact();
	    Boolean getStatus();
	}

	@Query(value = "SELECT v.vendor_name as vendorName, c.contact as contact, c.status as status " +
	       "FROM vendors v " +
	       "JOIN vendor_contacts c ON v.id = c.vendor_id",
	       nativeQuery = true)
	public List<VendorContactProjection> findAllVendorContactObject();
}