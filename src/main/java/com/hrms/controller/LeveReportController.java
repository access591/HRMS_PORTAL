package com.hrms.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrms.ReportUtil;
import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.model.CommonUtil;
import com.hrms.model.Leave;
import com.hrms.model.LeaveDetail;
import com.hrms.model.LeaveRequest;
import com.hrms.model.MenuModule;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.LeaveDetailService;
import com.hrms.service.LeaveRequestService;
import com.hrms.service.LeaveService;
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
	@Autowired LeaveRequestService leaveRequestService;
	@Autowired LeaveService leaveService;
	@Autowired CommonUtil employeeLeaveRequest;
	
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
	
	
	
	/* leave Transaction detail report */
	
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
	
	
	
	 @PostMapping("createleaveTransactionReport") 
	 public String createLeaveTransactionReport(@RequestParam("employeeName") String employeeName,@RequestParam("fromDate") Date fromDate,
			 						@RequestParam("toDate")Date toDate , Model model,HttpServletRequest req,HttpServletResponse res) {
		 
		 System.out.println("creating Leave transaction Report");
		 
		 System.out.println("employee : "+ employeeName);
		 System.out.println("to date : "+toDate+" From Date : "+ fromDate);
		 List<Employee> listEmployee = employeeService.getAllEmployees();
		 if(listEmployee != null) {
				model.addAttribute("listEmployee", listEmployee);
		 }
		 
		 
		 List<LeaveRequest> listLeaveRequest = leaveRequestService.findByEmpBetweenDate(employeeName, toDate, fromDate);
		 //LeaveRequest listLeave = leaveRequestService.findByToDate(toDate);
		 List<CommonUtil> empLeaveRequest = new ArrayList<CommonUtil>();
		 //List<MenuModule> empLeaveRequest = null;
		 CommonUtil empLvRe;
		 System.out.println("return for block"  + listLeaveRequest.size());
		for(int i = 0;i<listLeaveRequest.size();i++) 
		 {
			
			 Leave leave = leaveService.findLeaveById(listLeaveRequest.get(i).getLeaveCode());
			 Employee employee = employeeService.findEmployeeById(listLeaveRequest.get(i).getEmpCode());
			 Department department = departmentService.findDepartmentById(listLeaveRequest.get(i).getDeptCode());
			 
			 empLvRe = new CommonUtil(employee.getEmpName(),department.getDeptName(),
					 leave.getLevType(),listLeaveRequest.get(i).getToDate().toString(),listLeaveRequest.get(i).getFromDate().toString(),
					 listLeaveRequest.get(i).getApplyDate().toString(),listLeaveRequest.get(i).getApproevedBy(),listLeaveRequest.get(i).getReason(),
					 listLeaveRequest.get(i).getLeaveFor());
			 
			 empLeaveRequest.add(empLvRe);
			 System.out.println("return for block i value : "+ i);
		} 
		System.out.println("return for block"  + empLeaveRequest.size());
		 String reportName = "LeaveTransaction";  
		 reportUtil.leaveTransactionPdfReportByEmp(req, res, reportName, empLeaveRequest);
		 System.out.println("size : " +listLeaveRequest.get(0).getDeptCode());
		 return pageMappingService.PageRequestMapping(reqpageOfleaveRequestTransactionReport, pageNoLeaveTransactionReport);
	 }
	 
	

	@ResponseBody
	 @GetMapping("getDepartmentByEmpCode/{empCode}")
	 public Department getDepartmentByEmpCode(@PathVariable("empCode") String empCode) {
		 System.out.println("Get Department By Emp Code / LeaveTransactionController");
		 System.out.println("emp code is : " + empCode);
		 Employee employee = employeeService.findEmployeeById(empCode);
		 
		 Department department =departmentService.findDepartmentById(employee.getDepartmentCode());
		 System.out.println("dePARTMent Service : " + department.getDeptName());
		 return department;
	 }
	 
	

}
