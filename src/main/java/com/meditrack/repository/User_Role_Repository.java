package com.meditrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meditrack.model.User_Role;

@Repository
public interface User_Role_Repository extends JpaRepository<User_Role, Long>{

}
