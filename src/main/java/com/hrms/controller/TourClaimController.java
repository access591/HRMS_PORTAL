package com.hrms.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.model.LoanApplicationUtil;
import com.hrms.model.MenuModule;
import com.hrms.model.TourClaim;
import com.hrms.model.TourClaimUtil;
import com.hrms.model.TourPlan;
import com.hrms.model.TravelingExpenses;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.TourClaimService;
import com.hrms.service.TourPlanService;

@Controller
public class TourClaimController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	TourClaimService tourClaimService; 
	@Autowired
	EmployeeService employeeService;
	@Autowired
	DepartmentService  departmentService;
	@Autowired
	DesignationService designationService;
	@Autowired 
	TourPlanService tourPlanService;
	@GetMapping("/tourClaim")
	public String tourClaim(Model model, HttpSession session) {

	
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		 List<Employee> listEmployee = employeeService.getAllEmployees();
		  model.addAttribute("listEmployee", listEmployee);
		  List<TourPlan> ListTourPlan=tourPlanService.getAllTourPlan();
			model.addAttribute("ListTourPlan", ListTourPlan);
			
		session.setAttribute("username", session.getAttribute("username"));
		 return "tourClaim";
	}
	

	@PostMapping("/saveTourClaim")
	public String saveTourClaim(@ModelAttribute("tourClaim")TourClaimUtil tourClaimUtil, Model model, HttpSession session,HttpServletRequest request) throws ParseException {
		String insertedBY = (String) session.getAttribute("USER_NAME");
		TourClaim tourClaim= new TourClaim();
		TravelingExpenses travelingExpenses=new TravelingExpenses();
		Employee emp = new Employee();
		emp.setEmpCode(tourClaimUtil.getEmpCode());
		tourClaim.setEmpCode(emp);
		
		TourPlan tourPl=new TourPlan();
		tourPl.setTourPlanId(tourClaimUtil.getTourPlanId());
		tourClaim.setTourPlanId(tourPl);
		
		tourClaim.setInsBy(insertedBY);
		tourClaim.setApprovalStatus("N");
		tourClaim.setTourClaimDate(tourClaimUtil.getTourClaimDate());
		tourClaim.setTourPlanDate(tourClaimUtil.getTourPlanDate());
		tourClaim.setStartPlace(tourClaimUtil.getStartPlace());
		tourClaim.setVisitPlace(tourClaimUtil.getVisitPlace());
		tourClaim.setAdvancePaid(tourClaimUtil.getAdvancePaid());
		tourClaim.setVisitPurpose(tourClaimUtil.getVisitPurpose());
		this.tourClaimService.AddTourClaim(tourClaim);
		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/tourClaim";
		
		
		
	}


	//@CrossOrigin
	@ResponseBody
    @GetMapping("/viewEmployeeTourClaim/{id}")
    public TourClaimUtil  getempById(@PathVariable(value = "id") String id) {
		Employee e = employeeService.findEmployeeById(id);
		

		
		Department d = departmentService.findDepartmentById(e.getDepartmentCode());
		Designation des=designationService.findDesignationById(e.getDesignationCode());
		TourClaimUtil l=new TourClaimUtil(e.getEmpName(),d.getDeptName(),
				des.getDesgName());
		
        return l;
    }

}
