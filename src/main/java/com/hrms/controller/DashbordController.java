package com.hrms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrms.ImageUtil;
import com.hrms.model.AttendenceRegister;
import com.hrms.model.Category;
import com.hrms.model.Employee;
import com.hrms.model.InterviewMaster;
import com.hrms.model.MenuModule;
import com.hrms.model.OrderIssueTracking;
import com.hrms.model.TrackallEnquiries;
import com.hrms.service.AttendenceRegisterService;
import com.hrms.service.BudgetProvisionService;
import com.hrms.service.CategoryService;
import com.hrms.service.EmployeeService;
import com.hrms.service.InterviewMasterService;
import com.hrms.service.ModuleService;
import com.hrms.service.OrderIssueTrackingService;
import com.hrms.service.TrackallEnquiriesService;
import com.hrms.util.PaiChart;

@Controller
public class DashbordController {

	@Autowired
	private ModuleService moduleService;
	
	@Autowired EmployeeService employeeService;
	@Autowired BudgetProvisionService budgetProvisionService;
	
	@Autowired InterviewMasterService interviewMasterService;
	@Autowired AttendenceRegisterService attendenceRegisterService;
	@Autowired CategoryService categoryService;
	@Autowired OrderIssueTrackingService orderIssueTrackingService;
	@Autowired TrackallEnquiriesService trackallEnquiriesService; 

	@GetMapping("/getDashBoardData")
	public String getDashBoardData(Model model,HttpSession session) {
		String userCode= (String)session.getAttribute("username");
		session.setAttribute("imgUtil", new ImageUtil());
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		return "redirect:/";

	}
	
	@GetMapping("home")
	public String homePage(Model model , HttpSession session) {
		
		if(session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		return "dashboard";
	}
	
	
	
	
	@ResponseBody
	@GetMapping("getCategoryData")
	public Map<String, Long> getCategory() {
		
		System.out.println("================calling dashboad controller");
		
		Map<String, Long> countCategory = employeeService.countRecordByCategory();
		
		
		
		
		return countCategory;
	}
	
	@ResponseBody
	@GetMapping("demoChart")
	public Map<String, Long> getCategory1() {
		
		System.out.println("================calling dashboad controller========");
		
		List<PaiChart> paiChart = new ArrayList<>();
		
		Map<String, Integer> countCategory = new HashMap<>();
		countCategory.put("vt1st", 12);
		countCategory.put("vt2nd", 15);
        
		paiChart.add(new PaiChart("Chrome","18.55D"));
        paiChart.add(new PaiChart("Firefoc","19.99D"));
        paiChart.add(new PaiChart("IE","54.13D"));
        paiChart.add(new PaiChart("Oher","0.49D"));
		
        
        Map<String, Long> result = budgetProvisionService.findBudgetTrackDepartment();
		
		return result;
	}
	

}
