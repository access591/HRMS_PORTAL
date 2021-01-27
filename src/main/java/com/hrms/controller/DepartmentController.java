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

import com.hrms.service.DepartmentService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;


@Controller
public class DepartmentController {
	int pageno=4;
	String reqPage="/departmentMaster";
	@Autowired
	DepartmentService departmentService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	PageMappingService pageMappingService;
	/**
	 * Method to get Department Result 	
	 * @param model
	 * @param session
	 * @return
	 */	
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
	  return pageMappingService.PageRequestMapping(reqPage,pageno);
	}
/**
 * Method to Save Department Details	
 * @param model
 * @param session
 * @return
 */	
@PostMapping("/saveDepartment")
	public String SaveDepartment(@ModelAttribute("department") Department department, Model model,RedirectAttributes redirectAttributes,HttpSession session) {
	
	boolean isModuleExist = departmentService.checkDepartmentExists(department);	
	
	
	if (isModuleExist) {
	    redirectAttributes.addFlashAttribute("message", "Department Code Already exists !  ");
	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
	    return "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageno);

}
else 
{
	departmentService.addDepartment(department);
	List<Department> listDepartment = departmentService.getAllDepartments();
	model.addAttribute("listDepartment", listDepartment);
	session.setAttribute("username",session.getAttribute("username"));	
}
	  return "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageno);
}	
/**
 * Method to Edit Department Details	
 * @param model
 * @param session
 * @return
 */	
@GetMapping(value = {"/editDepartment/{id}"})
public String editdepartment(@PathVariable("id")String id,  Model model,HttpSession session)
 { 
	int editPageNo=5;
	String reqPageedit="/editDepartment";
	Department departmentEdit = departmentService.findDepartmentById(id);
	  model.addAttribute("departmentEdit", departmentEdit);

    session.setAttribute("username",session.getAttribute("username")); 
    return pageMappingService.PageRequestMapping(reqPageedit,editPageNo);
}
/**
 * Method to Update Department Details	
 * @param model
 * @param session
 * @return
 */	
@PostMapping("/updateDepartment")
public String updateDepartment(@ModelAttribute("deptupdate") Department d, Model model) {

	  this.departmentService.updateDepartment(d);
  	  
	  return "redirect:/"+pageMappingService.PageRequestMapping(reqPage,pageno);
}

/**
 * Method to Delete  Department Details	
 * @param model
 * @param session
 * @return
 */	
@GetMapping(value = {"/deleteDepartment/{id}"})
public String deletedepartment(@PathVariable("id")String id,  Model model,HttpSession session)
 { 
	  this.departmentService.removeDepartment(id);
    session.setAttribute("username",session.getAttribute("username")); 
    return "redirect:/"+pageMappingService.PageRequestMapping(reqPage,pageno);
}

}