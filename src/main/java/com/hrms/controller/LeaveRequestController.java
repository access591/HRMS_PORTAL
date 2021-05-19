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

import com.hrms.model.Employee;
import com.hrms.model.Leave;
import com.hrms.model.LeaveDetail;
import com.hrms.model.LeaveGrant;
import com.hrms.model.LeaveRequest;
import com.hrms.model.MenuModule;
import com.hrms.model.UserEntity;
import com.hrms.service.EmployeeService;
import com.hrms.service.LeaveDetailService;
import com.hrms.service.LeaveGrantRegisterService;
import com.hrms.service.LeaveRequestService;
import com.hrms.service.LeaveService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;
import com.hrms.service.UserService;

@Controller
public class LeaveRequestController {
	
	int pageno = 49;
	private String reqPage = "/leaveRequest";
	
	@Autowired EmployeeService employeeService;
	@Autowired LeaveRequestService leaveRequestService;
	@Autowired PageMappingService pageMappingService;
	@Autowired LeaveService leaveService;
	@Autowired UserService userService;
	@Autowired LeaveGrantRegisterService leaveGrantService;
	@Autowired LeaveDetailService leaveDetailService;
	
	
	@Autowired
	private ModuleService moduleService;
	
	@GetMapping("/leaveRequest")
	public String empPayDetail(Model model, HttpSession session) {
		
		System.out.println("leave request controller");
		List<Employee> listEmployee = employeeService.getAllEmployees();
		
		System.out.println("testing employe exists : " + listEmployee.get(0).getEmpName());
		String userCode = (String) session.getAttribute("username");
		System.out.println("userCode  is : "+ userCode);
		
		List<Leave> listLeave = leaveService.getAllLeaves();
		if(listLeave != null) {
			model.addAttribute("listLeave", listLeave);
		}
		
		UserEntity userEntity = userService.findUserById(userCode); 
		
		
		
		List<LeaveRequest> listLeaveRequestByEmpCode =
		  leaveRequestService.findAllByEmpCode( userEntity.getEmpCode());
		 
		
		List<LeaveRequest> listLeaveRequest = leaveRequestService.getAllLeaves();
		
		System.out.println("leave request size : " + listLeaveRequest.size());
		
		if(listLeaveRequest != null) {
			model.addAttribute("listRequest",listLeaveRequest);
		}
		
		if(listEmployee != null)
		{
			model.addAttribute("listEmployee" , listEmployee);
		}
		
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}

		  System.out.println("joninig begin..");
		

		session.setAttribute("username" , userCode);
		return pageMappingService.PageRequestMapping(reqPage, pageno);
	}
	
	@PostMapping("/saveLeaveRequest")
	public String saveLeaveRequest(@ModelAttribute("leaveRequest")LeaveRequest leaveRequest,HttpSession session) {
		
		String insertedBY = (String) session.getAttribute("userlogin");
		System.out.println("inserted by :"+ insertedBY);
		
		System.out.println("leave from date : " + leaveRequest.getEmpCode());
		System.out.println("leave to date : " + leaveRequest.getLeaveCode());
		leaveRequestService.addLeave(leaveRequest);
		
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:" + pageMappingService.PageRequestMapping(reqPage, pageno);
	}
	
	
	
	
	@GetMapping(value = { "/viewLeaveRequest/{id}" })
	public String viewLeaveRequestByEmpId(@PathVariable("id")String leaveRequestId,
						Model model,HttpSession session) {
		
		
		
		
		//List<LeaveRequest> leaveRequest = this.leaveRequestService.findByEmpCodeAndApplyDate(empCode, applyDate);
		LeaveRequest leaveRequest = this.leaveRequestService.findLeaveRequestById(Long.parseLong(leaveRequestId));
		
		if(leaveRequest != null) {
			model.addAttribute("leaveDetail", leaveRequest);
		}
		List<MenuModule> modules = moduleService.getAllModulesList(session.getAttribute("username").toString());
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		model.addAttribute("header", "View Leave Request");
		model.addAttribute("myhref", "leaveRequest");
		return "viewLeaveRequest";
	}
	
	@GetMapping(value = { "/deleteLeaveRequest/{id}" })
	public String deleteActivity(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		this.leaveRequestService.removeLeaveRequest(id);
		return "redirect:/"+ pageMappingService.PageRequestMapping(reqPage, pageno);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
