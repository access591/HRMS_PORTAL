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
import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.model.LeaveDetail;
import com.hrms.model.MenuModule;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
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
	
	int pageNoLeaveTransactionReport = 57;
	String reqpageOfleaveRequestTransactionReport = "/leaveTransactionReport";
	
	@Autowired private ModuleService moduleService;
	@Autowired PageMappingService pageMappingService;//leaveRegister.html
	@Autowired ReportUtil reportUtil;
	@Autowired LeaveDetailService leaveDetailService;
	@Autowired EmployeeService employeeService;
	@Autowired DesignationService designationService; 
	@Autowired DepartmentService departmentService;
	
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
	
	
	
	/* leave request report */
	
	@GetMapping("/leaveRequestReport")
	public String viewLeaveRequestDetailReport(Model model,HttpSession session,HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("leave request report - 1");
		
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
	
	
	@PostMapping("/createleaveRequestReport")
	public String leaveRequestReport(@RequestParam("empName") String empName , Model model, 
			HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		
		
		System.out.println("leave request report - 2");
		
		System.out.println("leave type is : "+ empName);
		
		//List<LeaveDetail> listLeaveDetail = leaveDetailService.getAllLeaveDetails();
		List<Employee> listEmployee = employeeService.getAllEmployees();
		String reportFileName = "LeaveDetail";
		
		reportUtil.leaveRequestReport(response,request,reportFileName,listEmployee);
		return pageMappingService.PageRequestMapping(reqPageOfLeaveRequestDetailReport, pageNoOfLeaveRequestDetailReport);  
	}
	
	
	
	/* leave Transaction report */
	
	@GetMapping("/leaveTransactionReport")
	public String leaveTransactionReport(Model model,HttpSession session,HttpServletRequest request, HttpServletResponse response) {
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<Employee> listEmployee = employeeService.getAllEmployees();
		if(listEmployee != null) {
			model.addAttribute("listEmployee", listEmployee);
		}
		
		List<Department> listDepartment = departmentService.getAllDepartments();
		if(listDepartment != null) {
			model.addAttribute("listDepartment", listDepartment);
		}
		
		List<Designation> listDesignation = designationService.getAllDesignations();
		if(listDesignation != null) {
			model.addAttribute("listDesignation", listDesignation);
		}
		
//		List<Department> listDpartment = 
		session.setAttribute("username", session.getAttribute("username"));

		return pageMappingService.PageRequestMapping(reqpageOfleaveRequestTransactionReport, pageNoLeaveTransactionReport);  
	}
	
	
	
	 @PostMapping("createLeaveTransactionReport") 
	 public String createLeaveTransactionReport(@RequestParam("emp") String empCode) {
		 
		 System.out.println("creating Leave transaction Report");
		 
		 
		 return pageMappingService.PageRequestMapping(reqpageOfleaveRequestTransactionReport, pageNoLeaveTransactionReport);
	 }
	 
	

}
