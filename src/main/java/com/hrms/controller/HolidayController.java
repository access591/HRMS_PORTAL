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

import com.hrms.model.Grade;
import com.hrms.model.Holiday;
import com.hrms.model.MenuModule;
import com.hrms.service.HolidayService;
import com.hrms.service.ModuleService;

@Controller
public class HolidayController 
{
	@Autowired
	HolidayService holidayService;
	@Autowired
	private ModuleService moduleService;
	@GetMapping("/HolidayMaster")	
	public String DepartmentMaster(Model model,HttpSession session) {
		List<Holiday>listHoliday = holidayService.getAllHolidays();
		model.addAttribute("listHoliday", listHoliday); 
		List<MenuModule> modules = moduleService.getAllModules();
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username",session.getAttribute("username"));
			return "HolidayMaster";
		}
	
	
	@PostMapping("/saveHolidays")
	  public String SaveHoliday(@ModelAttribute("holidays") Holiday holiday, Model model,HttpSession session) {
			if (holiday.getHoliday_Code() != "") {
				holidayService.addHoliday(holiday); 
				List<Holiday>listHoliday = holidayService.getAllHolidays();
				model.addAttribute("listHoliday", listHoliday); 
				session.setAttribute("username",session.getAttribute("username"));
	 
	  }
		return"redirect:/HolidayMaster";
	  
	  }
	@GetMapping(value = {"/editHoliday/{id}"})
	public String editHoliday(@PathVariable("id")String id,  Model model,HttpSession session)
	 { 
	Holiday Holidayedit = holidayService.findHolidayById(id);
	model.addAttribute("Holidayedit", Holidayedit);
	session.setAttribute("username",session.getAttribute("username")); 
	       return "/editHoliday"; 
	 }
	
	@PostMapping("/updateHoliday")
	public String updateHoliday(@ModelAttribute("holidayupdate") Holiday h, Model model) {

		  this.holidayService.updateHoliday(h);
	  	  
		  return "redirect:/HolidayMaster";
	
	}
	@GetMapping(value = {"/deleteHoliday/{id}"})
	public String deleteHoliday(@PathVariable("id")String id,  Model model,HttpSession session)
	 { 
	this.holidayService.removeHoliday(id);
	    session.setAttribute("username",session.getAttribute("username")); 
	    return "redirect:/HolidayMaster";
	}

	
}
