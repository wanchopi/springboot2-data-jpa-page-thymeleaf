package com.wanchopi.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanchopi.model.Player;
import com.wanchopi.model.Team;
import com.wanchopi.service.PlayerServiceAPI;
import com.wanchopi.service.TeamServiceAPI;

/**
 * 
 * @author Wanchopi
 *
 */
@Controller
public class AppController {
	
	@Autowired
	private PlayerServiceAPI playerService;
	@Autowired
	private TeamServiceAPI teamService;
	
	@InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
	
	@RequestMapping(value = "/")
	public String home(@RequestParam Map<String, Object> param, Model model) {
		int page = param.get("page") != null ? (Integer.valueOf(param.get("page").toString()) -1) : 0;
		PageRequest pageRequest = PageRequest.of(page, 5);
		Page<Player> pagePlayer = playerService.findAll(pageRequest);
		int totalPage = pagePlayer.getTotalPages();
		if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute("pages", pages);
		}
		model.addAttribute("players", pagePlayer.getContent());
		model.addAttribute("current", page + 1);
		model.addAttribute("next", page + 2);
		model.addAttribute("prev", page);
		model.addAttribute("last", totalPage);
		return "index";
	}
	
	@RequestMapping(value = "/players/{id}")
	public String findByTeam(@PathVariable("id") long id, Model model) {
		Team team = teamService.get(id);
		List<Player> players = playerService.findPlayersByTeam(team);
		model.addAttribute("players", players);
		return "/players-team";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody 
	public List<String> search(HttpServletRequest request) {
		return playerService.findNamePlayer(request.getParameter("term"));
	}
	
	@RequestMapping(value = "/search")
	public String searchPlayerByName(@RequestParam String keyword, Model model) {
		List<Player> players = playerService.findByName(keyword);
		model.addAttribute("players", players);
		return "/player";
	}
	
	@RequestMapping(value = "/players/search")
	public String searchPlayerByNameFromPlayers(@RequestParam String keyword, Model model) {
		List<Player> players = playerService.findByName(keyword);
		model.addAttribute("players", players);
		return "/player";
	}
	
	@RequestMapping(value = "/edit/search")
	public String searchPlayerByNameReRe(@RequestParam String keyword, Model model) {
		return "redirect:/?q= " + keyword;
	}
	
	
	@GetMapping(value = "/create")
	public String showForm(ModelMap mp) {
		mp.put("player", new Player());
		mp.put("teams", teamService.getAll());
		return "/player-form";
	}
	
	@PostMapping(value = "/save")
	public String save(@Valid Player player, BindingResult br, ModelMap mp) {
		if (br.hasErrors()) {
			mp.put("teams", teamService.getAll());
			return "/player-form";
		} else {
			playerService.save(player);
			return "redirect:/";
		}	
	}
	
	
	@GetMapping(value = "/edit/{id}")
	public String showEditForm(@PathVariable("id") long id, ModelMap mp) {
		Player player = playerService.get(id);
		mp.put("player", player);
		mp.put("teams", teamService.getAll());
		return "/update-form";
	}
	
	@PostMapping(value = "/update")
	public String update(@Valid Player player, BindingResult br) {
		if (br.hasErrors()) {
			return "/update-form";
		} else {
			playerService.save(player);
			return "redirect:/";
		}
	}
	
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") long id) {
		playerService.delete(id);
		return "redirect:/";
	}

}














