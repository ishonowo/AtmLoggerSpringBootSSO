package com.infinity.app.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infinity.app.model.Terminals;

@Repository
public interface TerminalRepo extends JpaRepository<Terminals, Long> {
	
}