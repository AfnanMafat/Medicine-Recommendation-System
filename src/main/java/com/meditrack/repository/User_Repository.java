package com.meditrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meditrack.model.User;


@Repository
public interface User_Repository extends JpaRepository<User, Long>{
	User findByUsername(String username);
    User findByEmail(String email);
}
