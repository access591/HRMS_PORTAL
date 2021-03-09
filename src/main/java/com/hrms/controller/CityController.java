package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hrms.model.City;
import com.hrms.model.MenuModule;
import com.hrms.service.CityService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;

@Controller
public class CityController {
	int pageno=25;
	String reqPage="/cityMaster";
	@Autowired
	private ModuleService moduleService;
	@Autowired
	CityService cityService;
	@Autowired
	PageMappingService pageMappingService;
/**
 *  Request mapping City Master 
 * @param model
 * @param session
 * @return
 */
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

		  return pageMappingService.PageRequestMapping(reqPage,pageno);
	}
	
	/**
	 * save Cities  request mapping city master 
	 * @param city
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/saveCity")
	public String saveCity(@ModelAttribute("city") City city, Model model, HttpSession session) {
		
		String insertedBY = (String) session.getAttribute("userlogin");
		city.setInsBy(insertedBY);
		
		cityService.addCity(city);
		List<City> listCity = cityService.getAllCities();
		model.addAttribute("listCity", listCity);
		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:" + pageMappingService.PageRequestMapping(reqPage, pageno);

	}

	/**
	 * Get Single Record And Edit Record 
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/editCity/{id}" })
	public String editCity(@PathVariable("id") String id, Model model, HttpSession session) {
		int editPageNo = 26;
		String reqPageedit = "/editCity";
		City cityEdit = cityService.findCityById(id);
		model.addAttribute("cityEdit", cityEdit);
		session.setAttribute("username", session.getAttribute("username"));

		return pageMappingService.PageRequestMapping(reqPageedit, editPageNo);
	}
	/**
	 * Request Mapping  update City 
	 * @param c
	 * @param model
	 * @param session
	 * @return
	 */
	
	@PostMapping("/updateCity")
	public String updateCity(@ModelAttribute("city") City city, Model model, HttpSession session) {
		
		String updatedBY = (String) session.getAttribute("userlogin");
		city.setUpdBy(updatedBY);
		this.cityService.updateCity(city);

		return "redirect:/" + pageMappingService.PageRequestMapping(reqPage, pageno);
	}
	
	/**
	 * Delete City record 
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/deleteCity/{id}" })
	public String deleteCity(@PathVariable("id") String id, Model model, HttpSession session) {
		
		this.cityService.removeCity(id);
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/" + pageMappingService.PageRequestMapping(reqPage, pageno);
	}
	

@GetMapping("/page/{pageNo}")
public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir, Model model) {
		
	int pageSize = 5;
		Page<City> page = cityService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<City> listOfCities = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listOfCities", listOfCities);
		 return pageMappingService.PageRequestMapping(reqPage,pageno);
	}
}
