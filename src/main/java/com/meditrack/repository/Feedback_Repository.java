package com.meditrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meditrack.model.Feedback;

@Repository
public interface Feedback_Repository extends JpaRepository<Feedback, Long>{

}
