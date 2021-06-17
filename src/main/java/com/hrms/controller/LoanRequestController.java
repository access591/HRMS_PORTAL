package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrms.ImageUtil;
import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.model.Loan;
import com.hrms.model.LoanApplication;
import com.hrms.util.LoanApplicationUtil;

import com.hrms.model.MenuModule;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.LoanMaterService;
import com.hrms.service.LoanRequestService;
import com.hrms.service.ModuleService;

@Controller
public class LoanRequestController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	DepartmentService  departmentService;
	@Autowired
	LoanMaterService loanMaterService;
	@Autowired
	DesignationService designationService;
	@Autowired
	LoanRequestService loanRequestService;
	@GetMapping("/loanRequest")
	public String loanApplication(Model model, HttpSession session) {
		List<Loan> listLoan = loanMaterService.getAllLoans();
		model.addAttribute("listLoan", listLoan);
		 List<Employee> listEmployee = employeeService.getAllEmployees();
		  model.addAttribute("listEmployee", listEmployee);
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<LoanApplication> listloanApplication=loanRequestService.getAllRequest();
		model.addAttribute("listloanApplication", listloanApplication);
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));
		 return "/loanRequest";
	}
	
	

	//@CrossOrigin
	@ResponseBody
    @GetMapping("/viewEmployeeDetailsLoan/{id}")
    public LoanApplicationUtil  getLocalConvyenceById(@PathVariable(value = "id") String id) {
		Employee e = employeeService.findEmployeeById(id);
		

		
		Department d = departmentService.findDepartmentById(e.getDepartmentCode());
		Designation des=designationService.findDesignationById(e.getDesignationCode());
		 return new LoanApplicationUtil(e.getEmpName(),d.getDeptName(),
				des.getDesgName()
				,e.getEmployeePayeeCode());
		
      
    }

	
	@PostMapping("/saveLoanRequest")
	public String saveLoanRequest(@ModelAttribute("LoanRequest")LoanApplicationUtil loanAppUtil, Model model, HttpSession session) {
		LoanApplication loanRequest = new LoanApplication();
		Employee emp = new Employee();
		emp.setEmpCode(loanAppUtil.getEmpCode());
		loanRequest.setEmpCode(emp);

		Loan lo = new Loan();
		lo.setLoanCode(loanAppUtil.getLoanCode());
		loanRequest.setLoanCode(lo);
		String insertedBY = (String) session.getAttribute("USER_NAME");
		loanRequest.setInsBy(insertedBY);
		loanRequest.setApprovalStatus("N");

		loanRequest.setAppDate(loanAppUtil.getAppDate());

		loanRequest.setLoanType(loanAppUtil.getLoanType());

		loanRequest.setEffScheduleDate(loanAppUtil.getEffScheduleDate());

		loanRequest.setAmountRequired(loanAppUtil.getAmountRequired());

		loanRequest.setAmountSanctioned(loanAppUtil.getAmountSanctioned());

		loanRequest.setLoanStatus(loanAppUtil.getLoanStatus());
	

		this.loanRequestService.addLoanRequest(loanRequest);

		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/loanRequest";

	}
	@GetMapping(value = {"/editLoanRequest/{id}"})
	public String editLoanRequest(@PathVariable("id")String id,  Model model,HttpSession session)
	 { 
		List<Employee> em = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", em);
		List<Loan> listLoan = loanMaterService.getAllLoans();
		model.addAttribute("listLoan", listLoan);
		LoanApplication loanRequestEdit =	loanRequestService.findByIdLoanReq(id);
		  model.addAttribute("loanRequestEdit", loanRequestEdit);
	   
	    return "editLoanRequest";
	}
	
	
	
	@PostMapping("/updateLoanRequest")
	public String updateLoanRequest(@ModelAttribute("LoanRequest") LoanApplicationUtil loanAppUtil, Model model, HttpSession session) {
	
	try {
		
		LoanApplication loanRequest = new LoanApplication();
		
		Loan lo = new Loan();
		lo.setLoanCode(loanAppUtil.getLoanCode());
		loanRequest.setLoanCode(lo);
		loanRequest.setAppDate(loanAppUtil.getAppDate());
		loanRequest.setLoanType(loanAppUtil.getLoanType());
		loanRequest.setEffScheduleDate(loanAppUtil.getEffScheduleDate());
		loanRequest.setAmountRequired(loanAppUtil.getAmountRequired());
		loanRequest.setAmountSanctioned(loanAppUtil.getAmountSanctioned());
		loanRequest.setLoanStatus(loanAppUtil.getLoanStatus());
		loanRequest.setAppNo(loanAppUtil.getAppNo());
	   this.loanRequestService.updateLoanRequest(loanRequest);
		return "redirect:/loanRequest";
			
	} catch (Exception e) {
	
		System.out.println(" "+e);
	}
	return "redirect:/loanRequest";

	
	}
	
	@GetMapping(value = {"/deleteLoanRequest/{id}"})
	public String deleteLoanRequest(@PathVariable("id")String id,  Model model,HttpSession session)
	 { 
		  this.loanRequestService.removeLoanRequest(id);
		  return "redirect:/loanRequest";

	}
	
}
