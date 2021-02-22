package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hrms.model.MenuModule;
import com.hrms.model.State;
import com.hrms.service.ModuleService;
import com.hrms.service.StateService;

@Controller
public class StateController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	StateService stateService;

	@GetMapping("/stateMaster")
	public String stateMaster(Model model, HttpSession session) {
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		List<State> listState =stateService.getAllStates();
		model.addAttribute("listState", listState);
		session.setAttribute("username", session.getAttribute("username"));
		return "stateMaster";
	}

	@PostMapping("/saveState")
	public String saveState(@ModelAttribute("state") State state, Model model, HttpSession session) {
		String insertedBY = (String) session.getAttribute("userlogin");
		state.setIns_by(insertedBY);
		stateService.addState(state);

		List<State> listState =stateService.getAllStates();
		model.addAttribute("listState", listState);
		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/stateMaster";

	}
	
	@GetMapping(value = { "/editState/{id}" })
	public String editState(@PathVariable("id") String id, Model model, HttpSession session) {

		State stateEdit = stateService.findStateById(id);
		model.addAttribute("stateEdit", stateEdit);
		session.setAttribute("username", session.getAttribute("username"));
		
		return "/editState";
	}
	
	
	@PostMapping("/updateState")
	public String updateState(@ModelAttribute("city") State c, Model model, HttpSession session) {
		String updatedBY = (String) session.getAttribute("userlogin");
		c.setUpd_by(updatedBY);
		this.stateService.updateState(c);

		return "redirect:/stateMaster";
	}
	
	@GetMapping(value = { "/deleteState/{id}" })
	public String deleteState(@PathVariable("id") String id, Model model, HttpSession session) {
		this.stateService.removeState(id);
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/stateMaster";
	}
}

