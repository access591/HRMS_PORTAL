package com.hrms.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hrms.model.ArmsLicenseDetails;
import com.hrms.model.BudgetProvision;
import com.hrms.model.Department;
import com.hrms.model.MenuModule;
import com.hrms.reports.ArmsReport;
import com.hrms.reports.BudgetReport;
import com.hrms.service.ArmsLicenseService;
import com.hrms.service.BudgetProvisionService;
import com.hrms.service.DepartmentService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;

@Controller
public class BudgetProvisionController {
	
	@Autowired private ModuleService moduleService;
	@Autowired DepartmentService departmentService;
	@Autowired BudgetProvisionService budgetProvisionService;
	@Autowired EmployeeService employeeService;
	
	
	@ModelAttribute
	public void commonData(Model model,HttpSession session) {
		//budget provision page
		//edit budget provision 
		//budget report 
		List<Department> departmentList = departmentService.getAllDepartments();
		if (departmentList != null) {
			model.addAttribute("departmentList", departmentList);
		}
		String userCode = (String) session.getAttribute("username");
		session.setAttribute("username", userCode);
	}
	
	
	@InitBinder("budgetProvision")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
        binder.registerCustomEditor(Date.class, "advtDate",
                                    new CustomDateEditor(dateFormatter, true));
        binder.registerCustomEditor(Date.class, "dateOfSanction",
                new CustomDateEditor(dateFormatter, true));

       
    }
	
	@GetMapping("budgetprovisionpage")
	public String budgetProvisionPage(@ModelAttribute("budgetProvision")BudgetProvision budgetProvision,
			Model model,HttpSession session) {
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<BudgetProvision> listBudgetProvision = budgetProvisionService.getAllBudgetProvision();
		if (listBudgetProvision != null) {
			model.addAttribute("listBudgetProvision", listBudgetProvision);
		}
		
		return "budgetProvision";
		
	}
	
	@PostMapping("saveBudgetProvision")
	public String saveBudgetProvision(@ModelAttribute("budgetProvision")BudgetProvision budgetProvision,
			Model model,HttpSession session) {
		budgetProvisionService.saveBudgetProvision(budgetProvision);
		return "redirect:budgetprovisionpage";
	}
	
	@GetMapping("editBudgetProvision/{id}")
	public String editBudgetProvision(@PathVariable("id")String budgetProvisionCode,
			@ModelAttribute("budgetProvision")
							BudgetProvision budgetProvision,Model model) {
		
		BudgetProvision b = budgetProvisionService.findByBudgetProvisionId(
				Long.parseLong(budgetProvisionCode));
		
		
		
		if(b != null) {
			model.addAttribute("budgetProvision", b);
		}
		return "editBudgetProvision"; //editBudgetProvision.html
	}
	
	@PostMapping("updateBudgetProvision")
	public String updateBudgetProvision(@ModelAttribute("budgetProvision")BudgetProvision budgetProvision) {
		
		budgetProvisionService.updateBudgetProvision(budgetProvision);
		return "redirect:budgetprovisionpage";
	}
	
	@GetMapping(value = {"deleteBudgetProvision/{id}"})
	public String deleteBudgetProvision(@PathVariable("id") String orderTrackingId, Model model, HttpSession session) {
		
		
		budgetProvisionService.removeBudgetProvision(Long.parseLong(orderTrackingId));
		
		return "redirect:/budgetprovisionpage";
	}
	
	
	@Autowired ArmsReport armsReport;
	@Autowired ArmsLicenseService armsLicenseService;
	
	@GetMapping("armsreport")
	public String armsLicensesReport(HttpServletResponse response, HttpServletRequest request) throws IOException {
		
//		List<ArmsLicenseDetails> listArmsReport = armsLicenseService.armsLicenseDetailsList();
//		armsReport.createArmsLicensesReport(response, request, listArmsReport);
		return "orderIssueTracking";
		
	}
	
	@GetMapping("budgetReport")
	public String viewBudgetReport(Model model,HttpSession session) {
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		
		return "budgetReport";
	}
	
	@Autowired BudgetReport budgetReport;
	@PostMapping("createbudgetreport")
	public String createBudgetReport(@RequestParam("deptCode")String deptCode,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		
	
		if(deptCode.equals("ALL")) {
			List<BudgetProvision> budgetProvision = budgetProvisionService.getAllBudgetProvision();
			budgetReport.createBudgetReport(response, request, budgetProvision, "All");
		}
		else {
			List<BudgetProvision> budgetProvision = budgetProvisionService.findBudgetProvisionByDepartment(deptCode);
			
			String deptName = null;
			if(budgetProvision != null) {
				deptName = budgetProvision.get(0).getDepartment().getDeptName();
			}
			
			budgetReport.createBudgetReport(response, request, budgetProvision,deptName);
		}
		
		return null;
	}
	
	
	
	

}
