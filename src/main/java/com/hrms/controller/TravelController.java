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
import com.hrms.model.Travel;
import com.hrms.service.ModuleService;
import com.hrms.service.TravelService;

@Controller
public class TravelController {

	@Autowired
	private ModuleService moduleService;
	@Autowired
	TravelService travelService;

	@GetMapping("/travelDetails")
	public String travelDetailsMaster(Model model, HttpSession session) {
		List<Travel> listTravel = travelService.getAllTravels();
		model.addAttribute("listTravel", listTravel);

		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}

		session.setAttribute("username", session.getAttribute("username"));

		return "travelDetails";
	}

	@PostMapping("/saveTravel")
	public String saveTravel(@ModelAttribute("travel") Travel travel, Model model, HttpSession session) {

		travelService.addTravel(travel);
		List<Travel> listTravel = travelService.getAllTravels();
		model.addAttribute("listTravel", listTravel);
		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/travelDetails";

	}

	@GetMapping(value = { "/deleteTravel/{id}" })
	public String deleteTravel(@PathVariable("id") String id, Model model, HttpSession session) {
		this.travelService.removeTravel(id);
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/travelDetails";
	}

}
