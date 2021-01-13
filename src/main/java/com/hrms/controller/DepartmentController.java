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

import com.hrms.model.Department;
import com.hrms.model.MenuModule;
import com.hrms.model.Module;
import com.hrms.service.DepartmentService;
import com.hrms.service.ModuleService;


@Controller
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	@Autowired
	private ModuleService moduleService;
	
@GetMapping("/departmentMaster")
public String DepartmentMaster(Model model,HttpSession session) {
	
	List<Department> listDepartment = departmentService.getAllDepartments();
	model.addAttribute("listDepartment", listDepartment);
	String userCode= (String)session.getAttribute("username");
	List<MenuModule> modules = moduleService.getAllModulesList(userCode);
	if (modules != null) {
		model.addAttribute("modules", modules);
	}
	session.setAttribute("username",session.getAttribute("username"));
		return "departmentMaster";
	}

@PostMapping("/saveDepartment")
	public String SaveDepartment(@ModelAttribute("department") Department department, Model model,RedirectAttributes redirectAttributes,HttpSession session) {
	
	boolean isModuleExist = departmentService.checkDepartmentExists(department);	
	
	
	if (isModuleExist) {
	    redirectAttributes.addFlashAttribute("message", "Department Code Already exists !  ");
	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
	    return "redirect:/departmentMaster";

}
else 
{
	departmentService.addDepartment(department);
	List<Department> listDepartment = departmentService.getAllDepartments();
	model.addAttribute("listDepartment", listDepartment);
	session.setAttribute("username",session.getAttribute("username"));	
}
	return "redirect:/departmentMaster";
}	

@GetMapping(value = {"/editDepartment/{id}"})
public String editdepartment(@PathVariable("id")String id,  Model model,HttpSession session)
 { 
	  
	Department departmentEdit = departmentService.findDepartmentById(id);
	  model.addAttribute("departmentEdit", departmentEdit);

    session.setAttribute("username",session.getAttribute("username")); 
       return "/editDepartment"; 
}

@PostMapping("/updateDepartment")
public String updateDepartment(@ModelAttribute("deptupdate") Department d, Model model) {

	  this.departmentService.updateDepartment(d);
  	  
	  return "redirect:/departmentMaster";
}

@GetMapping(value = {"/deleteDepartment/{id}"})
public String deletedepartment(@PathVariable("id")String id,  Model model,HttpSession session)
 { 
	  this.departmentService.removeDepartment(id);
    session.setAttribute("username",session.getAttribute("username")); 
    return "redirect:/departmentMaster";
}

}