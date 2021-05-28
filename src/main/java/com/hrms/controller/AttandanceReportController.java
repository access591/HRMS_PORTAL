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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrms.model.AttendenceRegister;
import com.hrms.model.Department;
import com.hrms.model.Employee;
import com.hrms.model.MenuModule;
import com.hrms.model.Module;
import com.hrms.reports.AttendenceReport;
import com.hrms.service.AttendenceRegisterService;
import com.hrms.service.DepartmentService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;


@Controller
public class AttandanceReportController {

	@Autowired DepartmentService departmentService;
	@Autowired private ModuleService moduleService;
	
	@Autowired EmployeeService employeeService;
	@Autowired AttendenceReport attendenceReport;
	@Autowired AttendenceRegisterService attendenceRegisterService;

// Attendance Register Mothly Report 

	@GetMapping("attendanceRegMothlyReport")
	public String attendanceRegisterMonthly(Model model,HttpSession session) {

		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<Department> departmentList = departmentService.getAllDepartments();
		if (departmentList != null) {
			model.addAttribute("departmentList", departmentList);
		}
		
		session.setAttribute("username", session.getAttribute("username"));
		return "AttendanceRegMothlyReport";
	}
	
	
	@PostMapping("createAttendenceMonthly")
	public String createAttendenceMonthly(@RequestParam("deptCode") String deptCode,
			@RequestParam("empCode") String empCode,HttpServletRequest request,HttpServletResponse response) {
		
		System.out.println("department code is : " + deptCode);
		System.out.println("employee code is : " + empCode);
		
		//get record behalf of deptcode and empCode
		
		//call attendence report method
		attendenceReport.attendenceMontlyReport(response, request, new ArrayList());
		
		
		return "redirect:AttendanceRegMothlyReport";
	}
	
	
	
	
	

	// Attendance Register Date wise Report

	@GetMapping("attendanceRegDateReport")
	public String attendanceRegisterDate(Model model , HttpSession session) {

		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		List<Department> listDepartment = departmentService.getAllDepartments();
		if(listDepartment != null) {
			model.addAttribute("listDepartment", listDepartment);
		}
		
		return "AttendanceRegDayReport";
	}
	
	
	@PostMapping("createAttendenceDatewise")
	 
	public String createAttendenceRegDateWiseReport(@RequestParam("empCode") String empCode,@RequestParam("deptCode") String deptCode,
			@RequestParam("fromDate") Date fromDate,
			@RequestParam("toDate") Date toDate,
			HttpServletResponse response,HttpServletRequest request) {
		
		System.out.println("empCode : "+ empCode);
		System.out.println("from date : "+ fromDate);
		
		if(deptCode.equals("") && empCode.equals("")) {
			System.out.println("All record : ");
			List<AttendenceRegister> listAttendenceRegister = attendenceRegisterService
					.findAllAttendenceBetweenDate(fromDate, toDate);
			attendenceReport.createAttendenceReportDatewise(response, request, listAttendenceRegister);
		}
		
		else if(!deptCode.equals("") && empCode.equals("")) {
			System.out.println("all record by department");
			List<AttendenceRegister> listAttendenceRegister = 
					attendenceRegisterService.findAttendenceByDeptBetweenDate(deptCode, fromDate, toDate);
			attendenceReport.createAttendenceReportDatewise(response, request, listAttendenceRegister);
			
		}
		else {
			System.out.println("find by employee");
			List<AttendenceRegister> listAttendenceRegister = attendenceRegisterService
									.findAttendenceByEmpCodeBetweenDate
									(empCode,fromDate,toDate);
			attendenceReport.createAttendenceReportDatewise(response, request, listAttendenceRegister);
		}
		
		
		return null;
	}
	
	
	
	
	
	

	// Overtime Register Mothly Report

	@GetMapping("overtimeRegMonthlyReport")
	public String overtimeRegisterMonthly(Model model,HttpSession session) {

		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		session.setAttribute("username", session.getAttribute("username"));
		return "OvertimeRegMothlyReport";
	}

	
	@PostMapping("createOvertimeMonthly")
	public String createOvertimeMonthlyReport(Model model,HttpSession session , HttpServletRequest request,
				HttpServletResponse response) {
		
		attendenceReport.createOvertimeRegDateReport(response, request, new ArrayList<String>());
		return "";
	}
	
	
	
	
	
	
	// Overtime Register Date wise Report

	@GetMapping("overtimeRegDayReport")
	public String overtimeRegisterDate(Model model,HttpSession session) {

		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		session.setAttribute("username", session.getAttribute("username"));
		return "OvertimeRegDateReport";
	}
	
	@PostMapping("createOvertimeRegDate")
	public String createOvertimeRegDateReport(Model model,HttpServletRequest request,HttpServletResponse response) {
		
		attendenceReport.createOvertimeRegDateReport(response, request, new ArrayList<>());
		return "redirect:/overtimeRegDayReport";
	}
	
	
	
	
	@ResponseBody
	@GetMapping("getEmployeeByDeptCode/{deptCode}")
	public List<Employee> getEmployeeByDeptCode(@PathVariable("deptCode") String deptCode) {
		
		System.out.println("dept code : " + deptCode);
		try {
			List<Employee> employeeList = employeeService.findByDepartmentCode(deptCode);
			return employeeList;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
