package com.meditrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meditrack.model.Medicine_Category;


@Repository
public interface Medicine_Category_Repository extends JpaRepository<Medicine_Category, Long>{

}
