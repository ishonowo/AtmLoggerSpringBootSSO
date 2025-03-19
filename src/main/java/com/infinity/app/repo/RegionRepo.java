package com.infinity.app.repo;



import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infinity.app.model.Region;

@Repository
public interface RegionRepo extends JpaRepository<Region, Long> {
    //boolean existsById(long id);
}