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

import com.hrms.model.City;
import com.hrms.model.MenuModule;
import com.hrms.model.UrlDetail;
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
		List<City> listCity = cityService.getAllCities();
		model.addAttribute("listCity", listCity);
		
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
		List<City> listCity = cityService.getAllCities();
		model.addAttribute("listCity", listCity);
		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/cityMaster";

	}

	@GetMapping(value = { "/editCity/{id}" })
	public String editCity(@PathVariable("id") String id, Model model, HttpSession session) {

		City cityEdit = cityService.findCityById(id);
		model.addAttribute("cityEdit", cityEdit);
		session.setAttribute("username", session.getAttribute("username"));
		
		return "/editCity";
	}
	
	
	@PostMapping("/updateCity")
	public String updateCity(@ModelAttribute("city") City c, Model model) {

		this.cityService.updateCity(c);

		return "redirect:/cityMaster";
	}
	
	@GetMapping(value = { "/deleteCity/{id}" })
	public String deleteCity(@PathVariable("id") String id, Model model, HttpSession session) {
		this.cityService.removeCity(id);
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/cityMaster";
	}
	
}