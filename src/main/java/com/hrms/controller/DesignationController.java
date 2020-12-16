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

import com.hrms.model.Designation;
import com.hrms.service.DesignationService;


@Controller
public class DesignationController {

	@Autowired
	DesignationService designationService;
	
	
@GetMapping("/designationMaster")
public String DesignationMaster(Model model,HttpSession session) {
	
	List<Designation> listDesignation = designationService.getAllDesignations();
	model.addAttribute("listDesignation", listDesignation);
	session.setAttribute("username",session.getAttribute("username"));
		return "designationMaster";
	}


@PostMapping("/saveDesignation")
	public String SaveDesignation(@ModelAttribute("designation") Designation designation, Model model) {
		if (designation.getDesg_code() != "") {
			designationService.addDesignation(designation);
			
			List<Designation> listDesignation = designationService.getAllDesignations();
			model.addAttribute("listDesignation", listDesignation);
		} 
		return "redirect:/designationMaster";

	}	


  @GetMapping(value = {"/editDesignation/{id}"})
  public String editdesignation(@PathVariable("id")String id,  Model model,HttpSession session)
   { 
	  
	  Designation designationEdit = designationService.findDesignationById(id);
	  model.addAttribute("designationEdit", designationEdit);

      session.setAttribute("username",session.getAttribute("username")); 
         return "/editDesignation"; 
  }
  

  
  @PostMapping("/updateDesignation/{id}")
  public String updateDesignation(@PathVariable("id") String id, Designation designation,Model model) {

    	  designation.setDesg_code(id);
    	
    	  //designationService.updateDesignation(designation);
    	  return "surendra";
  }


}
