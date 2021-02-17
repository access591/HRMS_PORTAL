package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hrms.model.City;
import com.hrms.model.MenuModule;
import com.hrms.service.CityService;
import com.hrms.service.ModuleService;

@Controller
public class CityController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	CityService cityService;

	@GetMapping("/cityMaster")
	public String cityMaster(Model model, HttpSession session) {
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username", session.getAttribute("username"));

		return "cityMaster";
	}

	@PostMapping("/saveCity")
	public String saveCity(@ModelAttribute("city") City city, Model model, HttpSession session) {

		cityService.addCity(city);
		;
		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/cityMaster";

	}
}
