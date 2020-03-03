package com.wanchopi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wanchopi.model.Team;

/**
 * Team repository
 * 
 * @author Wanchopi
 *
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long>{
	
	@Query("SELECT name FROM Team where name like %:keyword%")
	public List<String> search(@Param("keyword") String keyword);

}
