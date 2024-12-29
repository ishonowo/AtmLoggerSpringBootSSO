package com.infinity.app.repo;



import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infinity.app.model.Regions;

@Repository
public interface RegionRepo extends JpaRepository<Regions, Long> {
    //boolean existsById(long id);
}