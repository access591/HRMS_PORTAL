package com.hrms.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hrms.service.LeaveDetailService;



import org.springframework.web.bind.annotation.ResponseBody;

import com.hrms.ReportUtil;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.model.TourPlan;
import com.hrms.reports.TourClaimReport;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.LeaveGrantRegisterService;
import com.hrms.service.LeaveRequestService;
import com.hrms.service.LeaveService;
import com.hrms.service.TourPlanService;
import com.hrms.util.TourClaimReportUtil;

@Controller
public class ReportCommonController {

	@Autowired LeaveRequestService leaveRequestService;
	@Autowired LeaveGrantRegisterService leaveGrantService;
	@Autowired EmployeeService employeeService;
	@Autowired LeaveService leaveService;
	@Autowired LeaveDetailService leaveDetailService;
	@Autowired DesignationService designationService;
	
	@Autowired TourPlanService tourPlanService;
	
	@Autowired TourClaimReport tourClaimReport;
	

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
	
//TOUR CLAIM REPORT	
	@GetMapping("tourclaimPage")
	public String tourClaimReport(Model model,HttpSession session) {
		
		List<Employee> listEmployee = employeeService.getAllEmployees();
		if(listEmployee != null) {
			model.addAttribute("listEmployee", listEmployee);
		}
		model.addAttribute("object" , new TourPlan());
		return "tourClaimReports"; //tourClaimReports.html
	}
	
	@PostMapping("generateTourClaim")
	public String genrateTourClaim(@ModelAttribute("object") TourPlan tourPlan,
			@RequestParam("empName") String empCode,HttpServletRequest req,HttpServletResponse res) {
		
		System.out.println("employee type/name : "+ empCode);
		if(!empCode.equals("ALL"))
		{
			List<TourClaimReportUtil> ltr = new ArrayList<TourClaimReportUtil>();
			
			try {
				
				List<TourPlan> listTourPlan = tourPlanService.findTourPlanByEmpCode(empCode);
				
				List<TourPlan> listTourPlan1 = new ArrayList<TourPlan>();
				
				
				
				if(listTourPlan.size()>=1) {
					listTourPlan1.add(listTourPlan.get(0));
					
					
					
					for(int i=0;i<listTourPlan1.get(0).getTourPlanDetail().size();i++) {
						
						Designation desig = null;
						TourClaimReportUtil tr = new TourClaimReportUtil();
						
						System.out.println("claim id : "+listTourPlan.get(i).getTourPlanDetail().get(i).getEndPlace());
						tr.setTourPlanId(listTourPlan1.get(0).getTourPlanId());
						tr.setEmpCode(listTourPlan1.get(0).getEmpCode().getEmpCode());
						tr.setEmpName(listTourPlan1.get(0).getEmpCode().getEmpName());
						
						try {
							desig = designationService.findDesignationById(listTourPlan1.get(0).getDesgCode().getDesgCode());
							tr.setDesigName(desig.getDesgName());
							
						}catch(Exception e) {
							System.out.println("generate tour claim");
							e.printStackTrace();
						}
						tr.setStartPlace(listTourPlan1.get(0).getTourPlanDetail().get(i).getStartPlace());
						tr.setEndPlace(listTourPlan1.get(0).getTourPlanDetail().get(i).getEndPlace());
						ltr.add(tr);
					}
					
				}else {
					System.out.println("null pointer exception");
				}
				
				
			}catch(Exception e) {
				System.out.println("some joining error ");
				e.printStackTrace();
			}
			
			tourClaimReport.tourClaimReport(res, req, ltr);
			
		}
		
		return "redirect:tourClaimReports";
		
	}
	
	
	
//LOCAL CLAIM REORT
	
	@GetMapping("localclaimPage")
	public String localClaimReport(Model model,HttpSession session) {
		
		List<Employee> listEmployee = employeeService.getAllEmployees();
		if(listEmployee != null) {
			model.addAttribute("listEmployee", listEmployee);
		}
		model.addAttribute("object" , new TourPlan());
		return "LocalClaimReport"; //tourClaimReports.html
	}
	
	
	

}
