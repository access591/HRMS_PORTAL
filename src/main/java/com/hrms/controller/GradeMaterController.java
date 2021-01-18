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
import com.hrms.service.PageMappingService;

@Controller
public class GradeMaterController {
	@Autowired
	GradeMaterService gradeMaterService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	PageMappingService pageMappingService;
	
	int pageno=1;
	String reqPage="/gradeMaster";
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
	  return pageMappingService.PageRequestMapping(reqPage,pageno);
		}
	
@PostMapping("/saveGrade")
  public String SaveGrade(@ModelAttribute("grade") Grade grade, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
	boolean isGradeExist = gradeMaterService.checkGradeExists(grade);
	
	if (isGradeExist) {
	    redirectAttributes.addFlashAttribute("message", "Grade Code Already exists !  ");
	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
	    return "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageno);
	
	}
	    else {
			gradeMaterService.addGrade(grade); 
			List<Grade>listGrade = gradeMaterService.getAllGrades();
			model.addAttribute("listGrade", listGrade); 
			session.setAttribute("username",session.getAttribute("username"));
 
  }
	 return "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageno);
  
  }

@GetMapping(value = {"/editGrade/{id}"})
public String editGrade(@PathVariable("id")String id,  Model model,HttpSession session)
 { int editPageNo=2;
	String reqPageedit="/editGrade";
	
	
	Grade gradeEdit = gradeMaterService.findGradeById(id);
	  model.addAttribute("gradeEdit", gradeEdit);

    session.setAttribute("username",session.getAttribute("username")); 
       return pageMappingService.PageRequestMapping(reqPageedit,editPageNo);
}
@PostMapping("/updateGrade")
public String updateGradep(@ModelAttribute("gradeupdate") Grade g, Model model) {

	  this.gradeMaterService.updateGrade(g);
  	  
	  return "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageno);
}
@GetMapping(value = {"/deleteGarde/{id}"})
public String deletegrade(@PathVariable("id")String id,  Model model,HttpSession session)
 { 
		
		  int deletePageNo=3; 
		  String reqPagedelete="/deleteGarde";
		 
	
	  this.gradeMaterService.removeGrade(id);
    session.setAttribute("username",session.getAttribute("username")); 
    return "redirect:"+pageMappingService.PageRequestMapping(reqPagedelete,deletePageNo);
}
	
}
