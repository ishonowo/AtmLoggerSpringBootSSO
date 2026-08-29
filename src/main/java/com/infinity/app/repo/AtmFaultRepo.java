package com.infinity.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.infinity.app.model.AtmFault;

public interface AtmFaultRepo extends JpaRepository<AtmFault,Long>{
	   // Ordered so "Others" always renders last in the button list
    @Query("SELECT f FROM AtmFault f ORDER BY  f.natureOfFault ASC")
    List<AtmFault> findAllOrdered();
}
