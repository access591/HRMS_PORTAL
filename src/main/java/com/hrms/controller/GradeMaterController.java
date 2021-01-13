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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.model.Grade;
import com.hrms.model.MenuModule;
import com.hrms.service.GradeMaterService;
import com.hrms.service.ModuleService;

@Controller
public class GradeMaterController {
	@Autowired
	GradeMaterService gradeMaterService;
	@Autowired
	private ModuleService moduleService;
	
@GetMapping("/gradeMaster")
	public String DesignationMaster(Model model,HttpSession session) {
		
	 List<Grade>listGrade = gradeMaterService.getAllGrades();
	  model.addAttribute("listGrade", listGrade); 
		String userCode= (String)session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
	  
	  session.setAttribute("username",session.getAttribute("username"));
			return "/gradeMaster";
		}
	
@PostMapping("/saveGrade")
  public String SaveGrade(@ModelAttribute("grade") Grade grade, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
	boolean isGradeExist = gradeMaterService.checkGradeExists(grade);
	
	if (isGradeExist) {
	    redirectAttributes.addFlashAttribute("message", "Grade Code Already exists !  ");
	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
	    return "redirect:/gradeMaster";
	
	}
	    else {
			gradeMaterService.addGrade(grade); 
			List<Grade>listGrade = gradeMaterService.getAllGrades();
			model.addAttribute("listGrade", listGrade); 
			session.setAttribute("username",session.getAttribute("username"));
 
  }
	return"redirect:/gradeMaster";
  
  }

@GetMapping(value = {"/editGrade/{id}"})
public String editdesignation(@PathVariable("id")String id,  Model model,HttpSession session)
 { 
	Grade gradeEdit = gradeMaterService.findGradeById(id);
	  model.addAttribute("gradeEdit", gradeEdit);

    session.setAttribute("username",session.getAttribute("username")); 
       return "/editGrade"; 
}
@PostMapping("/updateGrade")
public String updateDesignation(@ModelAttribute("gradeupdate") Grade g, Model model) {

	  this.gradeMaterService.updateGrade(g);
  	  
	  return "redirect:/gradeMaster";
}
@GetMapping(value = {"/deleteGarde/{id}"})
public String deletedesignation(@PathVariable("id")String id,  Model model,HttpSession session)
 { 
	  this.gradeMaterService.removeGrade(id);
    session.setAttribute("username",session.getAttribute("username")); 
    return "redirect:/gradeMaster";
}
	
}
