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

import com.hrms.ImageUtil;
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
	/**
	 * Request mapping list data grade master 
	 * @param model
	 * @param session
	 * @return
	 */
@GetMapping("/gradeMaster")
	public String gradeMaster(Model model,HttpSession session) {
	if (session.getAttribute("username") == null) {
		return "redirect:" + "./";
	}
	 List<Grade>listGrade = gradeMaterService.getAllGrades();
	  model.addAttribute("listGrade", listGrade); 
		String userCode= (String)session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("imgUtil", new ImageUtil());
	  session.setAttribute("username",session.getAttribute("username"));
	  return pageMappingService.PageRequestMapping(reqPage,pageno);
		}
/**
 * request mapping Save  grade data 
 * @param grade
 * @param model
 * @param session
 * @param redirectAttributes
 * @return
 */
	
@PostMapping("/saveGrade")
  public String saveGrade(@ModelAttribute("grade") Grade grade, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
	boolean isGradeExist = gradeMaterService.checkGradeExists(grade);
	if (session.getAttribute("username") == null) {
		return "redirect:" + "./";
	}
	if (isGradeExist) {
	    redirectAttributes.addFlashAttribute("message", "Grade Name Already exists !  ");
	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
	    return "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageno);
	
	}
	    else {
			gradeMaterService.addGrade(grade); 
			  redirectAttributes.addFlashAttribute("message", "Grade Added successfully !!  ");
			  redirectAttributes.addFlashAttribute("alertClass", "alert-success");
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
	if (session.getAttribute("username") == null) {
		return "redirect:" + "./";
	}
	
	Grade gradeEdit = gradeMaterService.findGradeById(id);
	  model.addAttribute("gradeEdit", gradeEdit);

    session.setAttribute("username",session.getAttribute("username")); 
       return pageMappingService.PageRequestMapping(reqPageedit,editPageNo);
}
/**
 * 
 * @param  Request mapping Update grade data 
 * @param model
 * @return
 */
@PostMapping("/updateGrade")
public String updateGradep(@ModelAttribute("gradeupdate") Grade g, Model model,RedirectAttributes redirectAttributes) {

	  this.gradeMaterService.updateGrade(g);
	  redirectAttributes.addFlashAttribute("message", "Grade Update successfully !!  ");
	  redirectAttributes.addFlashAttribute("alertClass", "alert-success");
	  return "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageno);
}
/**
 * Request Mapping delete grade data 
 * @param id
 * @param model
 * @param session
 * @return
 */
@GetMapping(value = {"/deleteGarde/{id}"})
public String deletegrade(@PathVariable("id")String id,  Model model,HttpSession session,RedirectAttributes redirectAttributes)
 { 
	if (session.getAttribute("username") == null) {
		return "redirect:" + "./";
	}
		 
	
	  this.gradeMaterService.removeGrade(id);

	  redirectAttributes.addFlashAttribute("mes", "Grade Delete  successfully! ");
	    redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
    session.setAttribute("username",session.getAttribute("username")); 
    return "redirect:/"+pageMappingService.PageRequestMapping(reqPage,pageno);
}
	
}
