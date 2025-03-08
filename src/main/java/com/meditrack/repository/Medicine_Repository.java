package com.meditrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meditrack.model.Medicine;


@Repository
public interface Medicine_Repository extends JpaRepository<Medicine, Long>{

}
