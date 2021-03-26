package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hrms.model.Employee;
import com.hrms.model.Leave;
import com.hrms.model.LeaveRequest;
import com.hrms.model.MenuModule;
import com.hrms.service.EmployeeService;
import com.hrms.service.LeaveRequestService;
import com.hrms.service.LeaveService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;

@Controller
public class LeaveRequestController {
	
	int pageno = 49;
	private String reqPage = "/leaveRequest";
	
	@Autowired EmployeeService employeeService;
	@Autowired LeaveRequestService leaveRequestService;
	@Autowired PageMappingService pageMappingService;
	@Autowired LeaveService leaveService;
	
	@Autowired
	private ModuleService moduleService;
	
	@GetMapping("/leaveRequest")
	public String empPayDetail(Model model, HttpSession session) {
		
		List<Employee> listEmployee = employeeService.getAllEmployees();
		List<Leave> leaveMaster = leaveService.getAllLeaves(); 
		
		if(listEmployee != null)
		{
			model.addAttribute("listEmployee" , listEmployee);
		}
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		model.addAttribute("leaveMaster" , leaveMaster);
		
		for(Leave leave : leaveMaster) {
			String type = leave.getLevType();
			//String toatlLeave = leave.getTotalLev();
			
			if(type.equals("casual")) {
				String casualTotal = leave.getTotalLev();
				model.addAttribute("casualTotal" , casualTotal);
			}
			if(type.equals("Sick")) {
				String sickTotal = leave.getTotalLev();
				model.addAttribute("sickTotal" , sickTotal);
			}
			if(type.equals("earned")) {
				String earnedTotal = leave.getTotalLev();
				model.addAttribute("earnedTotal" , earnedTotal);
			}
				
		}
		
		
		
		System.out.println("leave Request Controller");
		//leaveRequestService.findAllByName("EMP-0046");
		return pageMappingService.PageRequestMapping(reqPage, pageno);
	}
	
	@PostMapping("/saveLeaveRequest")
	public String saveLeaveRequest(@ModelAttribute("leaveRequest")LeaveRequest leaveRequest,HttpSession session) {
		
		String insertedBY = (String) session.getAttribute("userlogin");
		System.out.println("inserted by :"+ insertedBY);
		
		leaveRequestService.addLeave(leaveRequest);
		
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:" + pageMappingService.PageRequestMapping(reqPage, pageno);
	}
}
