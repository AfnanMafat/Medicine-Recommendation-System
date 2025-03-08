package com.meditrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meditrack.model.User_Diet_Plans;

@Repository
public interface User_Diet_Plan_Repository extends JpaRepository<User_Diet_Plans, Long>{

}
