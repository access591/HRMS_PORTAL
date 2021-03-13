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

import com.hrms.model.Holiday;
import com.hrms.model.MenuModule;
import com.hrms.service.HolidayService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;

@Controller
public class HolidayController 
{
	int pageno=9;
	String reqPage="/HolidayMaster";
	
	@Autowired
	HolidayService holidayService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	PageMappingService pageMappingService;
	/**
	 * 
	 * Request mapping Holiday list data 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/HolidayMaster")	
	public String departmentMaster(Model model,HttpSession session) {
		List<Holiday>listHoliday = holidayService.getAllHolidays();
		model.addAttribute("listHoliday", listHoliday); 
		String userCode= (String)session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username",session.getAttribute("username"));
		return pageMappingService.PageRequestMapping(reqPage,pageno);
		}
	
	/**
	 * Request Mapping save Holiday Master
	 * @param holiday
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/saveHolidays")
	  public String saveHoliday(@ModelAttribute("holidays") Holiday holiday, Model model,HttpSession session) {
			if (holiday.getHolidayCode()==null) {
				holidayService.addHoliday(holiday); 
				List<Holiday>listHoliday = holidayService.getAllHolidays();
				model.addAttribute("listHoliday", listHoliday); 
				session.setAttribute("username",session.getAttribute("username"));
	 
	  }
			 return "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageno);
	  
	  }
	/**
	 * Request Mapping fetching  Id Base edit Holiday data 
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = {"/editHoliday/{id}"})
	public String editHoliday(@PathVariable("id")String id,  Model model,HttpSession session)
	 { 
		int editPageNo=10;
		String reqPageedit="/editHoliday";
	Holiday holidayedit = holidayService.findHolidayById(id);
	model.addAttribute("Holidayedit", holidayedit);
	session.setAttribute("username",session.getAttribute("username")); 
	 return pageMappingService.PageRequestMapping(reqPageedit,editPageNo);
	 }
	
	/**
	 * Request Mapping Update HOliday data 
	 * @param h
	 * @param model
	 * @return
	 */
	@PostMapping("/updateHoliday")
	public String updateHoliday(@ModelAttribute("holidayupdate") Holiday holiday, Model model) {

		  this.holidayService.updateHoliday(holiday);
	  	  
		  return "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageno);
	
	}
	
	/**
	 * 
	 * @param  Request Mapping  Delete By Id Holiday Data
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = {"/deleteHoliday/{id}"})
	public String deleteHoliday(@PathVariable("id")String id,  Model model,HttpSession session)
	 { 
	this.holidayService.removeHoliday(id);
	    session.setAttribute("username",session.getAttribute("username")); 
	    return "redirect:/"+pageMappingService.PageRequestMapping(reqPage,pageno);
	}

	
}
