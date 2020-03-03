package com.wanchopi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.wanchopi.model.Team;
import com.wanchopi.repository.TeamRepository;

/**
 * TeamServiceImpl, implements the TeamServiceAPI interface
 * 
 * @author Wanchopi
 *
 */
@Service
public class TeamServiceImpl extends GenericServiceImpl<Team, Long> implements TeamServiceAPI {
	
	@Autowired
	private TeamRepository teamRepository;

	@Override
	public List<String> findNameTeams(String keyword) {
		List<String> teams = teamRepository.search(keyword);
		return teams;
	}

	@Override
	public JpaRepository<Team, Long> getRepository() {
		return teamRepository;
	}

}
