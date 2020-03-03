package com.wanchopi.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wanchopi.model.Player;
import com.wanchopi.model.Team;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PlayerRepositoryTest {
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private TeamRepository teamRespository;
		
	@Test
	public void testSearchPlayers() {
		String keyword = "gasol";
		List<Player> players = playerRepository.searchPlayers(keyword);
		// not null
		assertNotNull(players);
		// two players
		assertEquals(2, players.size());
		keyword = "lll";
		List<Player> players2 = playerRepository.searchPlayers(keyword);
		// not null
		assertNotNull(players2);
		// zero players
		assertEquals(0, players2.size());
	}
	
	@Test
	public void testFindByTeam() {
		List<Team> teams = teamRespository.findAll();
		Team team = teams.get(0); 
		List<Player> players = playerRepository.findByTeam(team);
		// three players
		assertEquals(3, players.size());
	}

}
