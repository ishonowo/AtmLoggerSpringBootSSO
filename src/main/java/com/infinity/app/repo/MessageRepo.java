package com.infinity.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infinity.app.model.Message;

public interface MessageRepo extends JpaRepository<Message, Long>{

}
