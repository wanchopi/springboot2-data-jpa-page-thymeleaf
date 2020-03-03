package com.wanchopi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wanchopi.model.Player;
import com.wanchopi.model.Team;

/**
 * Player repository
 * 
 * @author Wanchopi
 *
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{
	
	@Query("SELECT p FROM Player p WHERE p.name like %:keyword%")
	public List<Player> searchPlayers(@Param("keyword") String keyword);
	
	@Query("SELECT name FROM Player WHERE name like %:keyword%")
	public List<String> search(@Param("keyword") String keyword);
	
	@Query("SELECT p FROM Player p WHERE p.team like :team")
	public List<Player> findByTeam(Team team);
	
}
