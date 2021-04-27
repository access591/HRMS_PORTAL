package com.hrms.controller;


import java.io.IOException;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hrms.ImageUtil;
import com.hrms.model.Category;
import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.model.MenuModule;
import com.hrms.service.CategoryService;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;

@Controller
public class EmployeeController {
	int pageno = 43;
	String reqPage = "/employeeMaster";
	@Autowired
	DepartmentService departmentService;
	@Autowired
	DesignationService designationService;
	@Autowired
	PageMappingService pageMappingService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	CategoryService categoryService;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
/**
 * get All employee Details page 
 * @param model
 * @param session
 * @return
 */
	@GetMapping("/employeeMaster")
	public ModelAndView employeeMaster(Model model, HttpSession session) {

		List<Employee> listEmployee = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", listEmployee);
		
		List<Department> listDepartment = departmentService.getAllDepartments();
		model.addAttribute("listDepartment", listDepartment);
		
		List<Category> listCategory = categoryService.getAllCategory();
		model.addAttribute("listCategory" ,listCategory);
		
		List<Designation> listDesignation = designationService.getAllDesignations();
		model.addAttribute("listDesignation", listDesignation);
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username", session.getAttribute("username"));
		ModelAndView modelAndView = new ModelAndView(pageMappingService.PageRequestMapping(reqPage, pageno));
	   
		modelAndView.addObject("imgUtil", new ImageUtil());
	    modelAndView.addObject("listEmployee", listEmployee);
		
	    return modelAndView;
	
	}
/**
 * Save Employee Page 
 * @param employee
 * @param model
 * @param session
 * @param multipartFile
 * @return
 */
	@PostMapping("/saveEmployee")
	public String employeeMasterSave(@ModelAttribute("employees") Employee employee, Model model, HttpSession session,
			@RequestParam("image") MultipartFile file,HttpServletRequest request) {

		try {
			byte[] imageData = file.getBytes();
			employee.setImageProfile(imageData);
			employeeService.addEmployee(employee);
			log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
			session.setAttribute("username", session.getAttribute("username"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		model.addAttribute("imgUtil", new ImageUtil());
		return "redirect:/" + pageMappingService.PageRequestMapping(reqPage, pageno);

	}

	/**
	 * Edit Employee 
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/editEmployee/{id}" })
	public String editEmployee(@PathVariable("id") String id, Model model, HttpSession session) {
		int editPageNo = 44;
		String reqPageedit = "/editEmployee";
		List<Department> listDepartment = departmentService.getAllDepartments();
		model.addAttribute("listDepartment", listDepartment);
		List<Designation> listDesignation = designationService.getAllDesignations();
		model.addAttribute("listDesignation", listDesignation);
		Employee employeeEdit = employeeService.findEmployeeById(id);
		model.addAttribute("employeeEdit", employeeEdit);
		model.addAttribute("imgUtil", new ImageUtil());
		session.setAttribute("imgUtilSession",employeeEdit.getImageProfile());
		session.setAttribute("username", session.getAttribute("username"));
		return pageMappingService.PageRequestMapping(reqPageedit, editPageNo);
	}
/**
 * update employee
 * @param e
 * @param model
 * @param multipartFile
 * @return
 */
	@PostMapping("/updateEmployee")
	public String updatePageUrl(@ModelAttribute("employees") Employee employee, HttpSession session, Model model,@RequestParam("file") MultipartFile multipartFile) {
		byte[] imgUtilSession = (byte[]) session.getAttribute("imgUtilSession");

		try {

			byte[] imageData = multipartFile.getBytes();
			if(imageData.length==0)
			{
				employee.setImageProfile(imgUtilSession);
			}
			else
			{
			employee.setImageProfile(imageData);
			}
			
			this.employeeService.updateEmployee(employee);

		} catch (Exception e2) {
			e2.printStackTrace();
		}
		this.employeeService.updateEmployee(employee);
		return "redirect:/" + pageMappingService.PageRequestMapping(reqPage, pageno);
	}
	/**
	 * 
	 * @param id delete employee 
	 * @param Emp_Img
	 * @param model
	 * @param session
	 * @return
	 */

	@GetMapping(value = { "/deleteEmployee/{id}" })
	public String deleteEmployee(@PathVariable("id") String id,  Model model,
			HttpSession session) {
		try {
			this.employeeService.removeEmployeet(id);
			session.setAttribute("username", session.getAttribute("username"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/" + pageMappingService.PageRequestMapping(reqPage, pageno);
	}

}

