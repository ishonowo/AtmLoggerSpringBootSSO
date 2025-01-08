package com.infinity.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infinity.app.model.VendorContactObject;

@Repository
public interface VendorContactObjectRepo  extends JpaRepository<VendorContactObject,Long>{
	@Query(value="SELECT  c.id,v.vendor_name, c.contact, c.status " +
		       "FROM vendors v JOIN vendor_contacts c ON v.id=c.vendor_id",nativeQuery=true)
		List<VendorContactObject> findAllVendorContactObject();


}
