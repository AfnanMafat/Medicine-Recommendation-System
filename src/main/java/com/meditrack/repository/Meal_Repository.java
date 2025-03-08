package com.meditrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meditrack.model.Meal;


@Repository
public interface Meal_Repository extends JpaRepository<Meal, Long>{

}
