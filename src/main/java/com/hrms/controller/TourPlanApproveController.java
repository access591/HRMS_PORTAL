package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hrms.model.MenuModule;
import com.hrms.model.TourPlan;
import com.hrms.service.ModuleService;
import com.hrms.service.TourPlanApprovalService;

@Controller
public class TourPlanApproveController {


	@Autowired private ModuleService moduleService;
	@Autowired TourPlanApprovalService tourPlanApprovalService;
	
	@GetMapping("/tourPlanApproval")
	public String tourPlanApproval(Model model, HttpSession session) {
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<TourPlan> ListTourPlan=tourPlanApprovalService.getAllTourPlan();
		model.addAttribute("ListTourPlan", ListTourPlan);
		
		session.setAttribute("username", session.getAttribute("username"));
		return"tourPlanApproval";
		
	}
	
	@GetMapping("approvedTourPlan/{id}")
	public String approvedTourPlan(@PathVariable("id") String id) {
		tourPlanApprovalService.approvedByTourPlanId(id);
		return "redirect:/mediclaimApproval";
	}
	
	
}
