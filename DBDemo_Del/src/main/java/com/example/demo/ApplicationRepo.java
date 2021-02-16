package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepo  extends JpaRepository<Application, Integer>{    // DAO Layer
	
	List<Application> findByName(String name);   // select * from Application where name = ""; 
	
	List<Application> findByRollNoGreaterThan(int rollNo); // select * from Application where rollNo > "" and name = "";
	
}
