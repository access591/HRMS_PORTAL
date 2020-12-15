package com.hrms.controller;

import java.util.List;
import java.util.Map;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.hrms.model.SubModule;
import com.hrms.service.SubModuleService;

@Controller
public class SubModuleController {
	@Autowired
	SubModuleService subModuleService;

@GetMapping("/submodulepage")
	public String SubmodulePage(Model model) {
		List<SubModule> listSubModule = subModuleService.getAllSubModules();
		model.addAttribute("listSubModule", listSubModule);

		return "subModule";
	}

@PostMapping("/saveSubModule")
	public String SaveSubModule(@ModelAttribute("submodule") SubModule subModule, Model model) {
		if (subModule.getSubModuleCode() != "") {
			subModuleService.addSubModule(subModule);
			List<SubModule> listSubModule = subModuleService.getAllSubModules();
			model.addAttribute("listSubModule", listSubModule);
		} else {
			subModuleService.updateSubModule(subModule);

		}
		return "redirect:/submodulepage";

	}
	/*
	 * @GetMapping(value = {"/subModule/{subModuleId}/edit"}) public String
	 * editModule(@PathVariable String subModuleId, Model model,HttpSession session)
	 * { SubModule subModuleEdit = subModuleService.findSubModuleById(subModuleId);
	 * model.addAttribute("subModule", subModuleEdit);
	 * session.setAttribute("username",session.getAttribute("username")); return
	 * "subModule_edit"; }
	 * 
	 * 
	 */
@PostMapping(value = {"/updatesub_module"})
public ResponseEntity<?> editSubModule(@RequestBody Map<String, String> map, Model model, HttpSession session) {
	System.out.println("yes more==kk=============================================================================");
	subModuleService.update(map);
	session.setAttribute("username", session.getAttribute("username"));
	return new ResponseEntity<String>("success", HttpStatus.OK);
}


  @GetMapping("/subModules") public ResponseEntity<?> modules(Model model, HttpSession session) 
  {
	  List<SubModule> modulesss = subModuleService.getAllSubModules();
  model.addAttribute("modules", modulesss); session.setAttribute("username",
  session.getAttribute("username")); return new
  ResponseEntity<List<SubModule>>(modulesss, HttpStatus.OK); }
  
 
  @PostMapping(value = {"/deteteSub_module"})
  public ResponseEntity<?> SubModule(@RequestBody Map<String, String> map, Model model, HttpSession session) {
  	System.out.println("yes more==kk===============================================xxxxxxxxx==============================");
  	subModuleService.delete(map);
  	session.setAttribute("username", session.getAttribute("username"));
  	return new ResponseEntity<String>("success", HttpStatus.OK);
  }



}
