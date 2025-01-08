package com.infinity.app.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infinity.app.model.VendorContact;

@Repository
public interface VendorContactRepo extends JpaRepository<VendorContact,Long>{

}
