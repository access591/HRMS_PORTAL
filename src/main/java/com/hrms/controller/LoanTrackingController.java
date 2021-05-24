package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.model.Loan;
import com.hrms.model.LoanApplication;
import com.hrms.model.LoanApplicationUtil;
import com.hrms.model.MenuModule;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.LoanMaterService;
import com.hrms.service.LoanRequestService;
import com.hrms.service.ModuleService;

@Controller
public class LoanTrackingController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	DepartmentService  departmentService;
	@Autowired
	DesignationService designationService;
	@Autowired
	LoanRequestService loanRequestService;
	@Autowired
	LoanMaterService loanMaterService;
	
	@GetMapping("/loanTracking")
	public String loanTracking(Model model, HttpSession session) {
		  List<Employee> listEmployee = employeeService.getAllEmployees();
		  model.addAttribute("listEmployee", listEmployee);
		
			List<Loan> listLoan = loanMaterService.getAllLoans();
			model.addAttribute("listLoan", listLoan);
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username", session.getAttribute("username"));
		List<LoanApplication> listloanApplication=loanRequestService.getAllRequest();
		model.addAttribute("listloanApplication", listloanApplication);
		
		 return "/loanTracking";
	}

	
	
	@ResponseBody
    @GetMapping("/viewLoanTracking/{id}")
    public LoanApplicationUtil  viewLoanTracking(@PathVariable(value = "id") String id) {
		
		LoanApplication loanApp=loanRequestService.findByIdLoanReq(id);
		String empCode=loanApp.getEmpCode().getEmpCode();
		
		Employee e = employeeService.findEmployeeById(empCode);
		Department d = departmentService.findDepartmentById(e.getDepartmentCode());
		Designation des=designationService.findDesignationById(e.getDesignationCode());
		LoanApplicationUtil l=new LoanApplicationUtil(e.getEmpCode(),
					e.getEmpName(),d.getDeptName(),des.getDesgName(),e.getEmployeePayeeCode());
		
		l.setEmpCode(empCode);
		l.setLoanCode(loanApp.getLoanCode().getLoanCode());
		l.setAppDate(loanApp.getAppDate());
		l.setEffScheduleDate(loanApp.getEffScheduleDate());
		l.setAmountRequired(loanApp.getAmountRequired());
		l.setAmountSanctioned(loanApp.getAmountSanctioned());
		l.setLoanStatus(loanApp.getLoanStatus());
        return l;
    }
}



