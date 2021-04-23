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

import com.hrms.service.LeaveDetailService;



import org.springframework.web.bind.annotation.ResponseBody;

import com.hrms.ReportUtil;
import com.hrms.model.Employee;
import com.hrms.service.EmployeeService;
import com.hrms.service.LeaveGrantRegisterService;
import com.hrms.service.LeaveRequestService;
import com.hrms.service.LeaveService;

@Controller
public class ReportCommonController {

	@Autowired LeaveRequestService leaveRequestService;
	@Autowired LeaveGrantRegisterService leaveGrantService;
	@Autowired EmployeeService employeeService;
	@Autowired LeaveService leaveService;
	@Autowired LeaveDetailService leaveDetailService;
	

	@Autowired
	ReportUtil reportUtil;

	@GetMapping(value = { "/reportEmployee" })
	public String reportEmployee(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		List<Employee> dataList = employeeService.getAllEmployees();

		String reportFileName = "";

		String val = null;
		if (request.getParameter("_ex") != null) {
			val = request.getParameter("_ex");
		}

		if (val.equals("P")) {
			System.out.println("heloo0000000000" + val);

			reportFileName = "leavedetail_pdf";
			//leaveDetailService.leaveReportGenratepdf(request, response, reportFileName, dataList);
		} else if (val.equals("E")) {
			reportFileName = "bankwisereport_XLS";
			String filename = "bankwisereport";

		}

		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" + val);
		/*
		 * if (val.equals("P")) {
		 * 
		 * 
		 * reportFileName = "employee_report";
		 * employeeService.employeeReportGenratepdf(request, response, reportFileName,
		 * dataList); } else if (val.equals("E")) { reportFileName =
		 * "bankwisereport_XLS"; String filename = "bankwisereport";
		 * 
		 * }
		 */
		reportFileName = "employee_report";
		//employeeService.employeeReportGenratepdf(request, response, reportFileName, dataList);

		session.setAttribute("username", session.getAttribute("username"));
		return null;
	}

	// leave management report
	@ResponseBody
	@GetMapping("/testing")
	public List<?> testing(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("in testing block ");
		String reportFileName = "LeaveDetail";

		List<Employee> arrayList = employeeService.getAllEmployees();

		System.out.println("last index of leave request" +arrayList.size());

		return reportUtil.leaveRequestReport(response, request, reportFileName, arrayList);
	}

	// leave detail report

	@ResponseBody
	@GetMapping("/leaveDetailReport")
	public void leaveDetailReport(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("leave detail report");
		//reportUtil.leavedetailReport(request, response);
	}
	
	
	@ResponseBody
	@PostMapping("/employeereport")
	public void employeeReport(HttpServletRequest request,HttpServletResponse response) {
		
		System.out.println("  employee report ");
		String reportName = "EmployeeReport";
		
		List<Employee> listEmployee = employeeService.getAllEmployees();
		//System.out.println("employee batch number : "+ listEmployee.get(0).getBatchYear());
		
		System.out.println("list employee : "+ listEmployee);
		reportUtil.allEmployeeReport(request, response, reportName, listEmployee);
		
	}
	
	
	

}
