package com.infinity.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infinity.app.model.Vendor;
import com.infinity.app.dto.VendorNameDto;

@Repository
public interface VendorRepo extends JpaRepository<Vendor, Long> {
	@Query("SELECT new com.infinity.app.dto.VendorNameDto(v.id, v.vendorName) FROM Vendor v ORDER BY v.vendorName")
    List<VendorNameDto> findAllNames();
}