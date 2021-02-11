package com.qa.projecttwo.persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.projecttwo.persistence.domain.ToDoList;

@Repository
public interface ToDoListRepo extends JpaRepository<ToDoList, Long>{
	
	@Query(value = "SELECT * FROM TODOLIST WHERE NAME=?1", nativeQuery = true)
	List<ToDoList> findByName (String name);
	

}
