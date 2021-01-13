package com.hrms.controller;

import java.util.List;
import java.util.Map;



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
import com.hrms.model.SubModule;
import com.hrms.model.Module;
import com.hrms.service.ModuleService;
import com.hrms.service.SubModuleService;

@Controller
public class SubModuleController {
	@Autowired
	SubModuleService subModuleService;
	@Autowired
	private ModuleService moduleService;
@GetMapping("/submodulepage")
	public String SubmodulePage(Model model,HttpSession session) {
		List<SubModule> listSubModule = subModuleService.getAllSubModules();
		model.addAttribute("listSubModule", listSubModule);
		List<Module> modulesList = moduleService.getActiveModules();
		model.addAttribute("modulesList", modulesList);
		String userCode= (String)session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesss(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username", session.getAttribute("username"));
		return "subModule";
	}

	@PostMapping("/saveSubModule")
	public String SaveSubModule(@ModelAttribute("submodule") SubModule subModule, Model model,
			RedirectAttributes redirectAttributes, HttpSession session) {

		boolean isSubModuleExist = subModuleService.checkSubModuleExists(subModule);
		if (isSubModuleExist) {
			redirectAttributes.addFlashAttribute("message", " Sub Module Already exists !  ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			return "redirect:/submodulepage";
		}

		else {
			subModuleService.addSubModule(subModule);
			List<SubModule> listSubModule = subModuleService.getAllSubModules();
			model.addAttribute("listSubModule", listSubModule);
			session.setAttribute("username", session.getAttribute("username"));
		}

		return "redirect:/submodulepage";

	}
	

	@GetMapping(value = { "/editSubModule/{id}" })
	public String editsubmodule(@PathVariable("id") String id, Model model, HttpSession session) {
		SubModule subModuleEdit = subModuleService.findSubModuleById(id);
		model.addAttribute("subModuleEdit", subModuleEdit);

		List<Module> modulesList = moduleService.getActiveModules();
		model.addAttribute("modulesList", modulesList);

		session.setAttribute("username", session.getAttribute("username"));
		return "/editSubModule";
	}

	@PostMapping("/updateSubModule")
	public String updatesubmodule(@ModelAttribute("submoduleupdate") SubModule subModule, Model model) {

		this.subModuleService.updateSubModule(subModule);

		return "redirect:/submodulepage";
	}

	@GetMapping(value = { "/deleteSubModule/{id}" })
	public String deletesubmodule(@PathVariable("id") String id, Model model, HttpSession session) {
		this.subModuleService.removeSubModule(id);
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/submodulepage";
	}

}
