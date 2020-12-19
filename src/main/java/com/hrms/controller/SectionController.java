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

import com.hrms.model.Section;
import com.hrms.service.SectionService;


@Controller
public class SectionController {

	@Autowired
	SectionService sectionService;
	
	
@GetMapping("/sectionMaster")
public String sectionMaster(Model model,HttpSession session) {
	
	List<Section> listSection = sectionService.getAllSections();
	model.addAttribute("listSection", listSection);
	session.setAttribute("username",session.getAttribute("username"));
		return "sectionMaster";
	}


@PostMapping("/saveSection")
	public String SaveSection(@ModelAttribute("section") Section section, Model model) {
		if (section.getSect_Code() != "") {
			sectionService.addSection(section);
			
			List<Section> listSection = sectionService.getAllSections();
			model.addAttribute("listSection", listSection);
		} 
		return "redirect:/sectionMaster";

	}	

@GetMapping(value = {"/editSection/{id}"})
public String editsection(@PathVariable("id")String id,  Model model,HttpSession session)
 { 
	  
	Section sectionEdit = sectionService.findSectionById(id);
	  model.addAttribute("sectionEdit", sectionEdit);

    session.setAttribute("username",session.getAttribute("username")); 
       return "/editSection"; 
}

@PostMapping("/updateSection")
public String updateSection(@ModelAttribute("sectupdate") Section d, Model model) {

	  this.sectionService.updateSection(d);
  	  
	  return "redirect:/sectionMaster";
}

@GetMapping(value = {"/deleteSection/{id}"})
public String deletesection(@PathVariable("id")String id,  Model model,HttpSession session)
 { 
	
	  
	  this.sectionService.removeSection(id);

    session.setAttribute("username",session.getAttribute("username")); 
    return "redirect:/sectionMaster";
}


	


}
