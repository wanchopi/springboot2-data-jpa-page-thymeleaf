package com.wanchopi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.wanchopi.model.Player;
import com.wanchopi.model.Team;
import com.wanchopi.repository.PlayerRepository;

/**
 * PlayerServiceImpl, implements the PLayerServiceAPI interface
 * 
 * @author Wanchopi
 *
 */
@Service
public class PlayerServiceImpl extends GenericServiceImpl<Player, Long> implements PlayerServiceAPI {
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Override
	public JpaRepository<Player, Long> getRepository() {
		return playerRepository;
	}

	@Override
	public Page<Player> findAll(PageRequest pageable) {	
		return playerRepository.findAll(pageable);
	}

	@Override
	public List<String> findNamePlayer(String keyword) {
		List<String> players = playerRepository.search(keyword);
		return players;
	}

	@Override
	public List<Player> findByName(String keyword) {
		List<Player> players = playerRepository.searchPlayers(keyword);
		return players;
	}

	@Override
	public List<Player> findPlayersByTeam(Team team) {
		List<Player> players = playerRepository.findByTeam(team);
		return players;
	}

}
