package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hrms.ReportUtil;
import com.hrms.model.Employee;
import com.hrms.model.LeaveDetail;
import com.hrms.model.MenuModule;
import com.hrms.service.EmployeeService;
import com.hrms.service.LeaveDetailService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;

@Controller
public class LeveReportController {
	
	int pageNoLeaveRegister = 55;  
	String reqPageOfLeaveRegister = "/leaveRegister";
	int pageNoOfLeaveRequestDetailReport = 56;
	String reqPageOfLeaveRequestDetailReport =  "/leaveRequestReport";
	
	@Autowired private ModuleService moduleService;
	@Autowired PageMappingService pageMappingService;//leaveRegister.html
	@Autowired ReportUtil reportUtil;
	@Autowired LeaveDetailService leaveDetailService;
	@Autowired EmployeeService employeeService;
	
	@GetMapping("/leaveRegister")
	public String viewLeaveRegisterReport(Model model,HttpSession session,HttpServletRequest request, HttpServletResponse response) {
		
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<LeaveDetail> listLeaveDetail = leaveDetailService.getAllLeaveDetails();
		if(listLeaveDetail != null) {
			model.addAttribute("listLeaveDetail" , listLeaveDetail);
		}
		session.setAttribute("username", session.getAttribute("username"));

		return pageMappingService.PageRequestMapping(reqPageOfLeaveRegister, pageNoLeaveRegister);  
		
	}
	
	
	
	@PostMapping("/leaveDetailPdf")
	public String leaveDetailPdf(@RequestParam("leaveType") String leaveType , Model model, 
			HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		
		System.out.println("leave type is : "+ leaveType);
		
		//List<LeaveDetail> listLeaveDetail = leaveDetailService.getAllLeaveDetails();
		
		reportUtil.leaveRegisterReport(request,response);
		return null;
	}
	
	
	
	@GetMapping("/leaveRequestReport")
	public String viewLeaveRequestDetailReport(Model model,HttpSession session,HttpServletRequest request, HttpServletResponse response) {
		
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<Employee> listEmployee = employeeService.getAllEmployees();
		if(listEmployee != null) {
			model.addAttribute("listEmployee", listEmployee);
		}
		session.setAttribute("username", session.getAttribute("username"));

		return pageMappingService.PageRequestMapping(reqPageOfLeaveRequestDetailReport, pageNoOfLeaveRequestDetailReport);  
		
	}
	
	
	@PostMapping("/leaveRequestReport")
	public String leaveRequestReport(@RequestParam("empName") String empName , Model model, 
			HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		
		System.out.println("leave type is : "+ empName);
		
		//List<LeaveDetail> listLeaveDetail = leaveDetailService.getAllLeaveDetails();
		List<Employee> listEmployee = employeeService.getAllEmployees();
		String reportFileName = "LeaveDetail";
		
		reportUtil.leaveRequestReport(response,request,reportFileName,listEmployee);
		return null;
	}
	
	
	
	

}
