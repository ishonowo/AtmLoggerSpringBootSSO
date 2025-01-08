package com.infinity.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infinity.app.model.Vendors;
import com.infinity.app.dto.VendorNameDto;

@Repository
public interface VendorRepo extends JpaRepository<Vendors, Long> {
	@Query("SELECT new com.infinity.app.dto.VendorNameDto(v.id, v.vendorName) FROM Vendors v ORDER BY v.vendorName")
    List<VendorNameDto> findAllNames();
}