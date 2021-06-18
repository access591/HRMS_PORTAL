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
import com.hrms.model.MenuModule;
import com.hrms.model.Module;
import com.hrms.model.Program;
import com.hrms.model.Program1;
import com.hrms.model.SubModule;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;
import com.hrms.service.ProgramService;
import com.hrms.service.SubModuleService;

@Controller
public class ProgramController {
	int pageno=23;
	String reqPage="/program";
	@Autowired
	ProgramService programService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	SubModuleService subModuleService;
	@Autowired
	PageMappingService pageMappingService;
	
	/**
	 * Method to get Program  Result 	
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/program")
	public String program(@ModelAttribute Module module, Model model, HttpSession session) {
		String userCode = (String) session.getAttribute("username");
		
		
		
		if (userCode!=null) {
			List<Program> listpPrograms = programService.getAllPrograms();
			model.addAttribute("listpPrograms", listpPrograms);
			List<Module> modulesList = moduleService.getActiveModules();
			model.addAttribute("modulesList", modulesList);
			List<SubModule> subModulesList = subModuleService.getActiveSubModules();
			model.addAttribute("subModulesList", subModulesList);
			session.setAttribute("imgUtil", new ImageUtil());
			List<MenuModule> modules = moduleService.getAllModulesList(userCode);
			if (modules != null) {
				model.addAttribute("modules", modules);
			}
			session.setAttribute("username", session.getAttribute("username"));
			return pageMappingService.PageRequestMapping(reqPage, pageno);
		}
		else
		{
		return "redirect:" + "./";	
		}
	}
	/**
	 * Method to Save Program 	
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/saveProgram")
	public String saveProgram1(@ModelAttribute("program1") Program program, Model model,
			RedirectAttributes redirectAttributes, HttpSession session) {

		boolean isSubModuleExist = programService.checkProgramExists(program);
		if (isSubModuleExist) {
			redirectAttributes.addFlashAttribute("message", " Program Already exists !");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		
			return "redirect:" + pageMappingService.PageRequestMapping(reqPage, pageno);
		}

		else {

			programService.addProgram(program);
			List<Program> listpPrograms = programService.getAllPrograms();

			model.addAttribute("listpPrograms", listpPrograms);
			session.setAttribute("username", session.getAttribute("username"));
		}
	
		return "redirect:" + pageMappingService.PageRequestMapping(reqPage, pageno);

	}
	/**
	 * Method to Edit Program 	
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/editProgram/{id}" })
	public String editProgramdata(@PathVariable("id") String id, Model model, HttpSession session) {
		int editPageNo=24;
		String reqPageedit="/editProgram";
		session.setAttribute("imgUtil", new ImageUtil());
		Program programEdit = programService.findProgramById(id);
		model.addAttribute("programEdit", programEdit);
		
		List<Module> modulesList = moduleService.getActiveModules();
		model.addAttribute("modulesList", modulesList);
		
		List<SubModule>subModulesList=subModuleService.getActiveSubModules();
		model.addAttribute("subModulesList", subModulesList);

		session.setAttribute("username", session.getAttribute("username"));
		
		return pageMappingService.PageRequestMapping(reqPageedit,editPageNo);
	}
	/**
	 * Method to update Program 	
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/updateProgram")
	public String updateProgram(@ModelAttribute("programupdate") Program program, Model model) {
		this.programService.updateProgram(program);
		return "redirect:/" + pageMappingService.PageRequestMapping(reqPage, pageno);
	}

	/**
	 * Method to Delete Program
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
@GetMapping(value = {"/deleteProgram/{id}"})
public String deleteprogram(@PathVariable("id")String id,  Model model,HttpSession session)
{ 
	  this.programService.removeProgram(id);
   session.setAttribute("username",session.getAttribute("username")); 
 
   return "redirect:/"+pageMappingService.PageRequestMapping(reqPage,pageno);

}


}
