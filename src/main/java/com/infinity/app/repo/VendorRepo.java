package com.infinity.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infinity.app.model.Vendors;

@Repository
public interface VendorRepo extends JpaRepository<Vendors, Long> {
	// boolean existsById(long id);
}