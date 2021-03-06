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
import com.hrms.service.PageMappingService;
import com.hrms.service.CountryService;

@Controller
public class CountryController {
	
	int pageno=29;
	String reqPage="/countryMaster";
	@Autowired
	private ModuleService moduleService;
	@Autowired
	CountryService countryService;
	@Autowired
	PageMappingService pageMappingService;
	/**
	 * Request mapping country master page
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/countryMaster")
	public String countryMaster(Model model, HttpSession session) {

		String userCode = (String) session.getAttribute("username");

		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		List<Country> listCountry = countryService.getAllCountrys();
		model.addAttribute("listCountry", listCountry);


		return pageMappingService.PageRequestMapping(reqPage, pageno);
	}

	/**
	 * 
	 * @param save  country 
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/saveCountry")
	public String saveCountry(@ModelAttribute("country") Country country, Model model, HttpSession session) {
		
		String insertedBY = (String) session.getAttribute("userlogin");
		country.setInsBy(insertedBY);
		
		countryService.addCountry(country);
		List<Country> listCountry = countryService.getAllCountrys();
		model.addAttribute("listCountry", listCountry);
		

		 return "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageno);

	}
	/**
	 * 
	 * @param id edit record base on id 
	 * @param model
	 * @param session
	 * @return
	 */
	
	@GetMapping(value = { "/editCountry/{id}" })
	public String editCountry(@PathVariable("id") String id, Model model, HttpSession session) {

		int editPageNo = 30;
		String reqPageedit = "/editCountry";

		Country countryEdit = countryService.findCountryById(id);
		model.addAttribute("countryEdit", countryEdit);
	

		return pageMappingService.PageRequestMapping(reqPageedit, editPageNo);
	}

	/**
	 * 
	 * @param upadte record request mapping 
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/updateCountry")
	public String updateCountry(@ModelAttribute("country") Country country, Model model, HttpSession session) {

		String updatedBY = (String) session.getAttribute("userlogin");
		country.setUpdBy(updatedBY);
		this.countryService.updateCountry(country);

		return "redirect:/" + pageMappingService.PageRequestMapping(reqPage, pageno);
	}
	
	/**
	 * Delete record base on id 
	 * @param id 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/deleteCountry/{id}" })
	public String deleteCountry(@PathVariable("id") String id, Model model, HttpSession session) {
		this.countryService.removeCountry(id);
		return "redirect:/" + pageMappingService.PageRequestMapping(reqPage, pageno);
	}
}
