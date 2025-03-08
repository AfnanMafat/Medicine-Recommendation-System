package com.meditrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meditrack.model.Medical_History;


@Repository
public interface Medical_History_Repository extends JpaRepository<Medical_History, Long>{

}
