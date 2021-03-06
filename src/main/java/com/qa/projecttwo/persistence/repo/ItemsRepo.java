package com.qa.projecttwo.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.projecttwo.persistence.domain.Items;

@Repository
public interface ItemsRepo extends JpaRepository<Items, Long>{

}
