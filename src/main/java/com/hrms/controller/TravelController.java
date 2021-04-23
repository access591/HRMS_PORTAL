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
import org.springframework.web.servlet.ModelAndView;

import com.hrms.model.City;
import com.hrms.model.MenuModule;
import com.hrms.model.Travel;
import com.hrms.service.CityService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;
import com.hrms.service.TravelService;

@Controller
public class TravelController {
	int pageno = 41;
	String reqPage = "/travelDetails";
	
	@Autowired
	PageMappingService pageMappingService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	TravelService travelService;
	@Autowired
	CityService cityService;
/**
 * Request  Mapping Travel Details 
 * @param model
 * @param session
 * @return
 */
	@GetMapping("/travelDetails")
	public String travelDetailsMaster(Model model, HttpSession session) {
		
		List<Travel> listTravel = travelService.getAllTravels();
		model.addAttribute("listTravel", listTravel);

		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		 List<City> cityList =cityService.getAllCities();
		 model.addAttribute("cityMaster", cityList);

		session.setAttribute("username", session.getAttribute("username"));

		return pageMappingService.PageRequestMapping(reqPage, pageno);
	}
/**
 * 
 * @param  Save record  travel
 * @param model
 * @param session
 * @return
 */
	@PostMapping("/saveTravel")
	public String saveTravel(@ModelAttribute("travel") Travel travel, Model model, HttpSession session) {

		travelService.addTravel(travel);
		List<Travel> listTravel = travelService.getAllTravels();
		model.addAttribute("listTravel", listTravel);
		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/" + pageMappingService.PageRequestMapping(reqPage, pageno);

	}
	/**
	 * 
	 * @param id find record base on id 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = {"/editTravel/{id}"})
	public String editTravel(@PathVariable("id") String id, Model model, HttpSession session) {
		int editPageNo = 42;
		String reqPageedit = "/editTravel";
		
		Travel travelEdit = travelService.findTravelById(id);
		model.addAttribute("travelEdit",travelEdit);
		List<City> cityList =cityService.getAllCities();
		 model.addAttribute("cityMaster", cityList);

		session.setAttribute("username", session.getAttribute("username"));
		
		return pageMappingService.PageRequestMapping(reqPageedit, editPageNo);
	}
	/**
	 * 
	 * @param update record Travel Detail 
	 * @param model
	 * @return
	 */
	@PostMapping("/updateTravel")
	public String updateTravel(@ModelAttribute("travel")Travel c, Model model) {

		this.travelService.updateTravel(c);

		return "redirect:/" + pageMappingService.PageRequestMapping(reqPage, pageno);
	}
/**
 * 
 * @param id Delete Record Travel Details 
 * @param model
 * @param session
 * @return
 */
	@GetMapping(value = { "/deleteTravel/{id}" })
	public String deleteTravel(@PathVariable("id") String id, Model model, HttpSession session) {
		this.travelService.removeTravel(id);
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/" + pageMappingService.PageRequestMapping(reqPage, pageno);
	}

}
