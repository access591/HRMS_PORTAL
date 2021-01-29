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

import com.hrms.model.MenuModule;
import com.hrms.model.Section;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;
import com.hrms.service.SectionService;
@Controller
public class SectionController {
	int pageno=17;
	String reqPage="/sectionMaster";
	@Autowired
	SectionService sectionService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	PageMappingService pageMappingService;
	/**
	 * 
	 * Request mapping  section list data
	 * 
	 * @param model
	 * @param session
	 * @return
	 */	
@GetMapping("/sectionMaster")
public String sectionMaster(Model model,HttpSession session) {
	
	List<Section> listSection = sectionService.getAllSections();
	model.addAttribute("listSection", listSection);
	String userCode= (String)session.getAttribute("username");
	List<MenuModule> modules = moduleService.getAllModulesList(userCode);
   	if (modules != null) {
   		model.addAttribute("modules", modules);
   	}
	session.setAttribute("username",session.getAttribute("username"));
		//return "sectionMaster";
	 return pageMappingService.PageRequestMapping(reqPage,pageno);
	}

/**
 * Request Mapping save section Master
 * 
 * @param holiday
 * @param model
 * @param session
 * @return
 */
@PostMapping("/saveSection")
	public String SaveSection(@ModelAttribute("section") Section section, Model model,RedirectAttributes redirectAttributes) {
	boolean isModuleExist = sectionService.checkSectionExists(section);	
	
	if (isModuleExist) {
		redirectAttributes.addFlashAttribute("message", "Section Code Already exists !  ");
	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
	    //return "redirect:/sectionMaster";
	    return "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageno);
	}
	
	else{
			sectionService.addSection(section);
			List<Section> listSection = sectionService.getAllSections();
			model.addAttribute("listSection", listSection);
		} 
		//return "redirect:/sectionMaster";
	  return "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageno);

	}	
/**
 * Request Mapping fetching Id Base edit Section data
 * 
 * @param id
 * @param model
 * @param session
 * @return
 */
@GetMapping(value = {"/editSection/{id}"})
public String editsection(@PathVariable("id")String id,  Model model,HttpSession session)
 { 
	 int editPageNo=18;
		String reqPageedit="/editSection";
	Section sectionEdit = sectionService.findSectionById(id);
	  model.addAttribute("sectionEdit", sectionEdit);

    session.setAttribute("username",session.getAttribute("username")); 
      // return "/editSection"; 
    return pageMappingService.PageRequestMapping(reqPageedit,editPageNo);
}
/**
 * Request Mapping Update Section data
 * 
 * @param
 * @param model
 * @return
 */
@PostMapping("/updateSection")
public String updateSection(@ModelAttribute("sectupdate") Section d, Model model) {

	  this.sectionService.updateSection(d);
  	  
	  //return "redirect:/sectionMaster";
	  return "redirect:/"+pageMappingService.PageRequestMapping(reqPage,pageno);
}
/**
 * 
 * @param Request Mapping Delete By Id Section Data
 * @param model
 * @param session
 * @return
 */
@GetMapping(value = {"/deleteSection/{id}"})
public String deletesection(@PathVariable("id")String id,  Model model,HttpSession session)
 { 
	
	  
	  this.sectionService.removeSection(id);

    session.setAttribute("username",session.getAttribute("username")); 
   // return "redirect:/sectionMaster";
    return "redirect:/"+pageMappingService.PageRequestMapping(reqPage,pageno);
}


}
