package com.wanchopi.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.wanchopi.model.Player;
import com.wanchopi.model.Team;

/**
 * PlayerServiceImpl, Player model's own interface
 * 
 * @author Wanchopi
 *
 */
public interface PlayerServiceAPI extends GenericServiceAPI<Player, Long>{
	
	public List<Player> findPlayersByTeam(Team team);
	
	public List<String> findNamePlayer(String keyword);
	
	public List<Player> findByName(String keyword);
	
	public Page<Player> findAll(PageRequest pageRequest);

}
