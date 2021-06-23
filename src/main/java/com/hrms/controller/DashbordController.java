package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hrms.ImageUtil;
import com.hrms.model.AttendenceRegister;
import com.hrms.model.Employee;
import com.hrms.model.InterviewMaster;
import com.hrms.model.MenuModule;
import com.hrms.service.AttendenceRegisterService;
import com.hrms.service.EmployeeService;
import com.hrms.service.InterviewMasterService;
import com.hrms.service.ModuleService;

@Controller
public class DashbordController {

	@Autowired
	private ModuleService moduleService;
	
	@Autowired EmployeeService employeeService;
	
	@Autowired InterviewMasterService interviewMasterService;
	@Autowired AttendenceRegisterService attendenceRegisterService;

	@GetMapping("/getDashBoardData")
	public String getDashBoardData(Model model,HttpSession session) {
		String userCode= (String)session.getAttribute("username");
		session.setAttribute("imgUtil", new ImageUtil());
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		return "redirect:/";

	}
	
	@GetMapping("home")
	public String homePage(Model model , HttpSession session) {
		
		if(session.getAttribute("username") == null) {
			return "";
		}
		
		try {
			List<Employee> listEmployee = employeeService.getAllEmployees();
			model.addAttribute("employeeList", listEmployee.size());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		List<AttendenceRegister> listAttendenceRegister = attendenceRegisterService
				.findTodayAttendenceList();
		if(listAttendenceRegister != null) {
			model.addAttribute("listAttendenceRegister", listAttendenceRegister.size());
		}
		
		List<AttendenceRegister> findTodayLeave = attendenceRegisterService.findTodayLeaveEmployee();
		if(findTodayLeave != null) {
			model.addAttribute("findTodayLeave", findTodayLeave.size());
		}
		
		List<InterviewMaster> listInterviewMaster = interviewMasterService.getFinalSelection();
		
		if(listInterviewMaster != null) {
			model.addAttribute("finalSelection", listInterviewMaster.size());
		}
		
		List<MenuModule> modules = moduleService.getAllModulesList(
				session.getAttribute("username").toString());
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		return "dashboard";
	}

}
