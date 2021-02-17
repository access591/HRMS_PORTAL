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

import com.hrms.model.Country;
import com.hrms.model.MenuModule;
import com.hrms.service.ModuleService;
import com.hrms.service.CountryService;

@Controller
public class CountryController {

	@Autowired
	private ModuleService moduleService;
	@Autowired
	CountryService countryService;

	@GetMapping("/countryMaster")
	public String countryMaster(Model model, HttpSession session) {
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		List<Country> listCountry = countryService.getAllCountrys();
		model.addAttribute("listCountry", listCountry);
		session.setAttribute("username", session.getAttribute("username"));

		return "countryMaster";
	}

	@PostMapping("/saveCountry")
	public String saveCountry(@ModelAttribute("country") Country country, Model model, HttpSession session) {

		countryService.addCountry(country);
		List<Country> listCountry = countryService.getAllCountrys();
		model.addAttribute("listCountry", listCountry);
		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/countryMaster";

	}
	
	@GetMapping(value = { "/deleteCountry/{id}" })
	public String deleteCountry(@PathVariable("id") String id, Model model, HttpSession session) {
		this.countryService.removeCountry(id);
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/countryMaster";
	}
}
