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
import com.hrms.model.UserEntity;
import com.hrms.reports.LeaveReport;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.LeaveDetailService;
import com.hrms.service.LeaveRequestService;
import com.hrms.service.LeaveService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;
import com.hrms.service.UserService;

@Controller
public class LeveReportController {

	int pageNoLeaveRegister = 55;
	String reqPageOfLeaveRegister = "/leaveRegister";

	int pageNoOfLeaveRequestDetailReport = 56;
	String reqPageOfLeaveRequestDetailReport = "/leaveRequestReport";

	int pageNoLeaveTransactionReport = 57;
	String reqpageOfleaveRequestTransactionReport = "/leaveTransactionReport";

	@Autowired
	private ModuleService moduleService;
	@Autowired
	PageMappingService pageMappingService;// leaveRegister.html
	@Autowired
	ReportUtil reportUtil;
	@Autowired
	LeaveDetailService leaveDetailService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	DesignationService designationService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	LeaveRequestService leaveRequestService;
	@Autowired
	LeaveService leaveService;
	@Autowired
	CommonUtil employeeLeaveRequest;
	@Autowired UserService userService;

	@Autowired
	LeaveReport leaveReport;

	@GetMapping("/leaveRegister")
	public String viewLeaveRegisterReport(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {

		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}

		List<Leave> listLeave = leaveService.getAllLeaves();

		if (listLeave != null) {
			model.addAttribute("listLeave", listLeave);
		}
		session.setAttribute("username", session.getAttribute("username"));

		return "leaveRegister";

	}

	@PostMapping("/leaveDetailPdf")
	public String leaveDetailPdf(@RequestParam("leaveType") String leaveType, Model model, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {

		System.out.println("leave type is : " + leaveType);

		List<LeaveDetail> listLeaveDetail = leaveDetailService.findLeaveDetailByLeaveType(leaveType);
		System.out.println("listLeaveDetail size : " + listLeaveDetail.size());
		reportUtil.leaveRegisterReport(request, response, listLeaveDetail);
		return null;
	}

	/* leave request report */

	@GetMapping("/leaveRequestReport")
	public String viewLeaveRequestDetailReport(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("leave request report - 1");

		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}

		List<Employee> listEmployee = employeeService.getAllEmployees();
		if (listEmployee != null) {
			model.addAttribute("listEmployee", listEmployee);
		}

		List<Department> departmentList = departmentService.getAllDepartments();
		System.out.println("department service======>" + departmentList.size());
		if (departmentList != null) {
			model.addAttribute("departmentList", departmentList);
		}
		session.setAttribute("username", session.getAttribute("username"));

		return "leaveRequestReport";

	}

	@PostMapping("/createleaveRequestReport")
	public String leaveRequestReport(@RequestParam("deptCode") String deptCode, @RequestParam("empCode") String empCode,
			@RequestParam("fromDate") Date fromDate, @RequestParam("toDate") Date toDate, Model model,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) {

		System.out.println("leave request report - 2");
		String reportFileName = "LeaveDetail";

		if (deptCode.equals("ALL")) {
			System.out.println("All record");
//			List<LeaveRequest> leaveRequestList = leaveRequestService.findAllApproveLeaveRequestBetweenDate(fromDate,
//					toDate);
			List<LeaveRequest> leaveRequestList  = leaveRequestService.findAllLeaveRequestBetweenDate(fromDate, toDate);
			leaveReport.leaveRequestReport(response, request, reportFileName, leaveRequestList, "ALL");
		} 
		
		else if (!deptCode.equals("ALL") && (empCode.equals(null) || empCode.equals(""))) {
			System.out.println("find data by department ");
			List<LeaveRequest> leaveRequestList = leaveRequestService.findAllLeaveRequestByDeptBetweenDate(fromDate, toDate, deptCode);
			leaveReport.leaveRequestReport(response, request, reportFileName, leaveRequestList, "ALL");
		} 
		
		else if (!deptCode.equals("ALL") && !empCode.equals(null) || !empCode.equals("")) {
			System.out.println("find data by emp ");
			List<LeaveRequest> leaveRequestList = leaveRequestService.findAllLeaveRequestbyEmpBetweenDate(fromDate, toDate, empCode);
			leaveReport.leaveRequestReport(response, request, reportFileName, leaveRequestList, "ALL");
		} 
		
		else {
			return "redirect:AttendanceRegMothlyReport";
		}

		List<LeaveRequest> listLeave = leaveRequestService.findAllByEmpCode(empCode);

		// System.out.println("list leave size : "+listLeave.size());

//		List<LeaveRequest> leaveDataSource = new ArrayList<LeaveRequest>();
//		LeaveRequest lv = listLeave.get(0);
//		leaveDataSource.add(lv);

		// leaveReport.leaveRequestReport(response,request,reportFileName,listLeave,empName);
		return null;
	}

	/* leave Transaction detail report */

	@GetMapping("/leaveTransactionReport")
	public String leaveTransactionReport(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {

		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}

		List<Employee> listEmployee = employeeService.getAllEmployees();
		if (listEmployee != null) {
			model.addAttribute("listEmployee", listEmployee);
		}

		List<Department> departmentList = departmentService.getAllDepartments();
		if (departmentList != null) {
			model.addAttribute("departmentList", departmentList);
		}

		List<Designation> listDesignation = designationService.getAllDesignations();
		if (listDesignation != null) {
			model.addAttribute("listDesignation", listDesignation);
		}

//		List<Department> listDpartment = 
		session.setAttribute("username", session.getAttribute("username"));

		return "leaveTransactionReport";
	}

	@PostMapping("createleaveTransactionReport")
	public String createLeaveTransactionReport(@RequestParam("deptCode") String deptCode,
			@RequestParam("empCode") String empCode, @RequestParam("fromDate") Date fromDate,
			@RequestParam("toDate") Date toDate, Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {


		String userCode = (String) session.getAttribute("username");
		UserEntity user = userService.findUserById(userCode);
		String activeUser = user.getUserName();
		

		if (deptCode.equals("ALL")) {
			System.out.println("All record");
			List<LeaveRequest> leaveRequestList = leaveRequestService.findAllApproveLeaveRequestBetweenDate(fromDate,
					toDate);
			leaveReport.leaveTransactionPdfReportByEmp(request, response,leaveRequestList,activeUser);
			
		} 
		
		else if (!deptCode.equals("ALL") && (empCode.equals(null) || empCode.equals(""))) {
			System.out.println("find data by department ");
			List<LeaveRequest> leaveRequestList = leaveRequestService.findAllApproveLeaveRequestByDeptBetweenDate(fromDate, toDate, deptCode);
			leaveReport.leaveTransactionPdfReportByEmp(request, response,leaveRequestList,activeUser);
		}
		
		else if (!deptCode.equals("ALL") && !empCode.equals(null) || empCode.equals("")) {
			System.out.println("find data by emp ");
			List<LeaveRequest> leaveRequestList = leaveRequestService.findApproveLeaveRequestByEmpBetweenDate(fromDate, toDate, empCode);
			leaveReport.leaveTransactionPdfReportByEmp(request, response,leaveRequestList,activeUser);
		} 
		
		else {
			//return "redirect:AttendanceRegMothlyReport";
		}
		
		session.setAttribute("username", userCode);
		// System.out.println("size : " +listLeaveRequest.get(0).getDeptCode());
		return pageMappingService.PageRequestMapping(reqpageOfleaveRequestTransactionReport,
				pageNoLeaveTransactionReport);
	}

	@ResponseBody
	@GetMapping("getDepartmentByEmpCode/{empCode}")
	public Department getDepartmentByEmpCode(@PathVariable("empCode") String empCode) {
		System.out.println("Get Department By Emp Code / LeaveTransactionController");
		System.out.println("emp code is : " + empCode);
		Employee employee = employeeService.findEmployeeById(empCode);

		Department department = departmentService.findDepartmentById(employee.getDepartmentCode());
		System.out.println("dePARTMent Service : " + department.getDeptName());
		return department;
	}

}
