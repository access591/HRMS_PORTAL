package com.hrms.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hrms.model.Employee;
import com.hrms.model.MenuModule;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;

@Controller
public class EmployeeController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	EmployeeService employeeService;
	@GetMapping("/employeeMaster")
	public String employeeMaster(Model model,HttpSession session) {
		
		 List<Employee>listEmployee = employeeService.getAllEmployees();
		  model.addAttribute("listEmployee", listEmployee); 
		
		String userCode= (String)session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username",session.getAttribute("username"));
			return "empInfoMaster";
		}
	
@PostMapping("/saveEmployee")
	public String employeeMasterSave(@ModelAttribute("employees") Employee employee, Model model,HttpSession session,@RequestParam("file") MultipartFile multipartFile) {

	
	try {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		employee.setEmp_Img(fileName);
		
		String folderPath ="\\src\\main\\resources\\static\\img\\";
		String uploadDir = System.getProperty("user.dir")+folderPath;
        String path = Paths.get(uploadDir + multipartFile.getOriginalFilename()).toString();
        
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
		stream.write(multipartFile.getBytes());
		stream.close();
		
		employeeService.addEmployee(employee); 
		session.setAttribute("username",session.getAttribute("username"));
	} catch (IOException e) {
		
		e.printStackTrace();
	}

	return"redirect:/employeeMaster";

		}	

}
