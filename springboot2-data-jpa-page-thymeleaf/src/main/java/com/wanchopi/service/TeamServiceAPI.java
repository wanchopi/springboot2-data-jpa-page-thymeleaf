package com.wanchopi.service;

import java.util.List;

import com.wanchopi.model.Team;

/**
 * TeamServiceAPI, Team model's own interface
 * 
 * @author Wanchopi
 *
 */
public interface TeamServiceAPI extends GenericServiceAPI<Team, Long>{
	
	public List<String> findNameTeams(String keyword);

}
