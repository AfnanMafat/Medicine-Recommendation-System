package com.meditrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meditrack.model.Diet_Plan_Feedback;

@Repository
public interface Diet_Plan_Feedback_Repository extends JpaRepository<Diet_Plan_Feedback, Long>{

}
