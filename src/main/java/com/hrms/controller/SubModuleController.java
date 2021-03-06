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
import com.hrms.model.SubModule;
import com.hrms.model.SubModule1;
import com.hrms.model.Module;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;
import com.hrms.service.SubModuleService;

@Controller
public class SubModuleController {
	int pageno=21;
	String reqPage="/subModule";
	
	@Autowired
	SubModuleService subModuleService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	PageMappingService pageMappingService;
	/**
	 * Method to get SubModule Result 	
	 * @param model
	 * @param session
	 * @return
	 */
@GetMapping("/subModule")
	public String submodulePage(Model model,HttpSession session) {
		
	String userCode= (String)session.getAttribute("username");
	if (userCode!=null) {
	  List<SubModule> listSubModule = subModuleService.getAllSubModules();
		model.addAttribute("listSubModule", listSubModule);
		List<Module> modulesList = moduleService.getActiveModules();
		model.addAttribute("modulesList", modulesList);
	
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));
		 return pageMappingService.PageRequestMapping(reqPage,pageno);
	}
	else 
	{
		  return "redirect:" + "./";
	}
	}


/**
 * Method to Save  SubModule 	
 * @param model
 * @param session
 * @return
 */
	@PostMapping("/saveSubModule")
	public String saveSubModule(@ModelAttribute("SubModule1") SubModule subModule, Model model,
			RedirectAttributes redirectAttributes, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		String insertedBY = (String) session.getAttribute("USER_NAME");
		
		
		subModule.setInsertedBySubModule(insertedBY);
		boolean isSubModuleExist = subModuleService.checkSubModuleExists(subModule);
		 
		  if (isSubModuleExist)
		  { redirectAttributes.addFlashAttribute("message"," Sub Module Already exists !  ");
		  redirectAttributes.addFlashAttribute("alertClass", "alert-success"); return
		  "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageno); }
		  
		  else {
			  boolean isSeqExist =subModuleService.checkSubModuleSeqExists(subModule);
		  
		  if(isSeqExist) 
		  {
			  redirectAttributes.addFlashAttribute("message2", "Seq No Already exists !");
		      redirectAttributes.addFlashAttribute("alertClass", "alert-success"); 
		      return "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageno);
		  }else {
			subModuleService.addSubModule(subModule);
			List<SubModule> listSubModule = subModuleService.getAllSubModules();
			model.addAttribute("listSubModule", listSubModule);
			session.setAttribute("username", session.getAttribute("username"));
	
		 return "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageno);
			 } 

			 } 
	}
	
	/**
	 * Method to Edit  SubModule 	
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/editSubModule/{id}" })
	public String editsubmodule(@PathVariable("id") String id, Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}  
		int editPageNo=22;
			String reqPageedit="/editSubModule";
		SubModule subModuleEdit = subModuleService.findSubModuleById(id);
		model.addAttribute("subModuleEdit", subModuleEdit);

		List<Module> modulesList = moduleService.getActiveModules();
		model.addAttribute("modulesList", modulesList);
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));
		return pageMappingService.PageRequestMapping(reqPageedit,editPageNo);
	}

	/**
	 * Method to update  SubModule 	
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/updateSubModule")
	public String updatesubmodule(@ModelAttribute("submoduleupdate") SubModule subModule, Model model, HttpSession session) {
		
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		this.subModuleService.updateSubModule(subModule);

		return "redirect:/" + pageMappingService.PageRequestMapping(reqPage, pageno);

	}

	/**
	 * Method to Delete  SubModule 	
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/deleteSubModule/{id}" })
	public String deletesubmodule(@PathVariable("id") String id, Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		this.subModuleService.removeSubModule(id);
		session.setAttribute("username", session.getAttribute("username"));
		 return "redirect:/"+pageMappingService.PageRequestMapping(reqPage,pageno);

	}

}
