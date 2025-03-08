package com.meditrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meditrack.model.Admin;

@Repository
public interface Admin_Repository extends JpaRepository<Admin, Long>{

}
