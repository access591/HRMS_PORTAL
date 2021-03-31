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
import com.hrms.model.LeaveRequest;
import com.hrms.model.MenuModule;
import com.hrms.model.UserEntity;
import com.hrms.service.EmployeeService;
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
	
	@Autowired
	private ModuleService moduleService;
	
	@GetMapping("/leaveRequest")
	public String empPayDetail(Model model, HttpSession session) {
		
		System.out.println("leave request controller");
		List<Employee> listEmployee = employeeService.getAllEmployees();
		
		
		String userCode = (String) session.getAttribute("username");
		System.out.println("userCode  is : "+ userCode);
		UserEntity userEntity = userService.findUserById(userCode);
		
		List<LeaveRequest> listLeaveRequestByEmpCode = leaveRequestService.findAllByEmpCode(
													userEntity.getEmpCode());
		model.addAttribute("leaveRequestByEmp", listLeaveRequestByEmpCode);
		
		
		
		if(listEmployee != null)
		{
			model.addAttribute("listEmployee" , listEmployee);
		}
		
		
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		
		
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
	
	
	
//	@GetMapping(value = { "/viewLeaveRequest/{empCode}/{applyDate}" })
//	public String viewLeaveRequestByEmpId(@PathVariable("empCode")String empCode, @PathVariable("applyDate") String applyDate,
//						Model model,HttpSession httpSession) {
//		
//		int pagenoView = 61;
//		String reqPageView = "/viewLeaveRequest";
//		
//		System.out.println("emp code : "+ empCode);
//		
//		List<LeaveRequest> listLeaveRequestByEmpCode = leaveRequestService.findAllByEmpCode(reqPageView);
//		model.addAttribute("listLeaveRequestByEmpCode", listLeaveRequestByEmpCode);
//		
//		
//		for(int i=0;i<listLeaveRequestByEmpCode.size();i++) {
//			LeaveRequest request = listLeaveRequestByEmpCode.get(i);
//			
//			if(request.getApplyDate().equals(applyDate)){
//				if(request != null) {
//					model.addAttribute("leaveDetail",request);
//				}
//			}
//		}
//		
//		String userCode = (String) httpSession.getAttribute("userlogin");
//		
//		httpSession.setAttribute("username", httpSession.getAttribute("username"));
//		
//		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
//		if (modules != null) {
//			model.addAttribute("modules", modules);
//		}
//		httpSession.setAttribute("userlogin", userCode);
//		return pageMappingService.PageRequestMapping(reqPageView, pagenoView);
//	}
//	
	
	
	@GetMapping(value = { "/viewLeaveRequest/{empCode}/{applyDate}" })
	public String viewLeaveRequestByEmpId(@PathVariable("empCode")String empCode, @PathVariable("applyDate") String applyDate,
						Model model,HttpSession httpSession) {
		int pagenoView = 61;
		String reqPageView = "/viewLeaveRequest";
		List<LeaveRequest> leaveRequest = this.leaveRequestService.findByEmpCodeAndApplyDate(empCode, applyDate);
		if(leaveRequest != null) {
			model.addAttribute("leaveDetail", leaveRequest.get(0));
		}
		return pageMappingService.PageRequestMapping(reqPageView, pagenoView);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
