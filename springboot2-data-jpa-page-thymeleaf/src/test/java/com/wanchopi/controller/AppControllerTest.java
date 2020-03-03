package com.wanchopi.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.wanchopi.controller.AppController;
import com.wanchopi.repository.PlayerRepository;
import com.wanchopi.repository.TeamRepository;
import com.wanchopi.service.PlayerServiceAPI;
import com.wanchopi.service.TeamServiceAPI;

@RunWith(SpringRunner.class)
@WebMvcTest(AppController.class)
@AutoConfigureMockMvc
public class AppControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private AppController controller;
	
	@MockBean
	private TeamRepository teamRepository;
	
	@MockBean
	private PlayerRepository playerRepository;
	
	@MockBean
	private PlayerServiceAPI playerService;
	
	@MockBean
	private TeamServiceAPI teamService;
	
	@Test
	public void contexLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	
	@Test
	public void testHome() throws Exception {
		this.mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(view().name("index"));
	}

}
