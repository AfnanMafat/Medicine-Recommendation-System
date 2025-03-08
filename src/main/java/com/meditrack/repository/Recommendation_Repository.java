package com.meditrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meditrack.model.Recommendations;

@Repository
public interface Recommendation_Repository extends JpaRepository<Recommendations, Long>{

}
