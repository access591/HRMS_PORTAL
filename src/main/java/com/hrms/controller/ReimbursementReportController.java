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

import com.hrms.ReportUtil;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.model.MenuModule;
import com.hrms.model.TourPlan;
import com.hrms.reports.LocalClaimReport;
import com.hrms.reports.LtaReport;
import com.hrms.reports.TourClaimReport;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.LeaveDetailService;
import com.hrms.service.LeaveGrantRegisterService;
import com.hrms.service.LeaveRequestService;
import com.hrms.service.LeaveService;
import com.hrms.service.LocalConvyenceDetailService;
import com.hrms.service.LtaRequestService;
import com.hrms.service.ModuleService;
import com.hrms.service.TourPlanService;
import com.hrms.util.TourClaimReportUtil;

@Controller
public class ReimbursementReportController {

	@Autowired
	LeaveRequestService leaveRequestService;
	@Autowired
	LeaveGrantRegisterService leaveGrantService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	LeaveService leaveService;
	@Autowired
	LeaveDetailService leaveDetailService;
	@Autowired
	DesignationService designationService;
	@Autowired
	ModuleService moduleService;
	@Autowired
	LtaRequestService ltaRequestService;
	@Autowired
	LocalConvyenceDetailService localConvyenceDetailService;
	@Autowired
	LocalClaimReport localClaimReport;
	@Autowired
	TourPlanService tourPlanService;
	@Autowired
	TourClaimReport tourClaimReport;
	@Autowired
	LtaReport ltaReport;
	@Autowired
	ReportUtil reportUtil;

//1.TOUR CLAIM REPORT	

	@GetMapping("tourclaimPage")
	public String tourClaimReport(Model model, HttpSession session) {

		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}

		List<Employee> listEmployee = employeeService.getAllEmployees();
		if (listEmployee != null) {
			model.addAttribute("listEmployee", listEmployee);
		}
		model.addAttribute("object", new TourPlan());
		session.setAttribute("username", userCode);
		return "tourClaimReports"; // tourClaimReports.html
	}

	@PostMapping("generateTourClaim")
	public String genrateTourClaim(@ModelAttribute("object") TourPlan tourPlan, HttpSession session,
			@RequestParam("empName") String empCode, HttpServletRequest req, HttpServletResponse res) {

		String userCode = (String) session.getAttribute("username");
		System.out.println("employee type/name : " + empCode);
		if (!empCode.equals("ALL")) {
			List<TourClaimReportUtil> ltr = new ArrayList<TourClaimReportUtil>();

			try {

				List<TourPlan> listTourPlan = tourPlanService.findTourPlanByEmpCode(empCode);
				List<TourPlan> listTourPlan1 = new ArrayList<TourPlan>();
				if (listTourPlan.size() >= 1) {
					listTourPlan1.add(listTourPlan.get(0));

					for (int i = 0; i < listTourPlan1.get(0).getTourPlanDetail().size(); i++) {

						Designation desig = null;
						TourClaimReportUtil tr = new TourClaimReportUtil();

						System.out
								.println("claim id : " + listTourPlan.get(i).getTourPlanDetail().get(i).getEndPlace());
						tr.setTourPlanId(listTourPlan1.get(0).getTourPlanId());
						tr.setEmpCode(listTourPlan1.get(0).getEmpCode().getEmpCode());
						tr.setEmpName(listTourPlan1.get(0).getEmpCode().getEmpName());

						try {
							desig = designationService
									.findDesignationById(listTourPlan1.get(0).getDesgCode().getDesgCode());
							tr.setDesigName(desig.getDesgName());

						} catch (Exception e) {
							System.out.println("generate tour claim");
							e.printStackTrace();
						}
						tr.setStartPlace(listTourPlan1.get(0).getTourPlanDetail().get(i).getStartPlace());
						tr.setEndPlace(listTourPlan1.get(0).getTourPlanDetail().get(i).getEndPlace());
						ltr.add(tr);
					}

				} else {
					session.setAttribute("username", userCode);
					return "redirect:/tourclaimPage";
				}

			} catch (Exception e) {
				System.out.println("some joining error ");
				e.printStackTrace();
			}

			tourClaimReport.tourClaimReport(res, req, ltr);

		}

		session.setAttribute("username", userCode);
		return "redirect:/tourclaimPage";

	}

}