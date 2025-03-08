package com.meditrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meditrack.model.Symptoms;


@Repository
public interface Symptoms_Repository extends JpaRepository<Symptoms, Long>{

}
