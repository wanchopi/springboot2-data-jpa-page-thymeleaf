package com.wanchopi.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.wanchopi.model.Player;
import com.wanchopi.model.Team;
import com.wanchopi.repository.PlayerRepository;

@SpringBootTest
public class PlayerServiceTest {
	
	@InjectMocks
	private PlayerServiceImpl playerService;
	
	@Mock
	private PlayerRepository playerRepository;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testFindNamePlayer() {
		List<Player> players = new ArrayList<Player>();
		Player player1 = new Player(1L, "yo", "yo@gmail.com", new Team());
		Player player2 = new Player(2L, "tu", "tu@gmail.com", new Team());
		players.add(player1);
		players.add(player2);

		when(playerRepository.searchPlayers("gasol")).thenReturn(players);
		assertEquals(players.size(), playerService.findByName("gasol").size());
	}
	
}






















