package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hrms.model.LeaveDetail;
import com.hrms.model.MenuModule;
import com.hrms.service.LeaveDetailService;
import com.hrms.service.ModuleService;
@Controller
public class LeaveDetailController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	LeaveDetailService leaveDetailService;
	
	@GetMapping("/leaveDetailMaster")
	public String leaveDetailMaster(Model model, HttpSession session) {
		List<LeaveDetail> listLeaveDetail = leaveDetailService.getAllLeaveDetails();
		model.addAttribute("listLeaveDetail", listLeaveDetail);
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		session.setAttribute("username", session.getAttribute("username"));

		return "leaveDetailMaster";
	}
	
	@PostMapping("/saveLeaveDetail")
	public String saveLeaveDetail(@ModelAttribute("leaveDetail")LeaveDetail leaveDetail, Model model, HttpSession session) {
		String insertedBY = (String) session.getAttribute("userlogin");
		leaveDetail.setInsBy(insertedBY);
		leaveDetailService.addLeaveDetail(leaveDetail);
		
		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/leaveDetailMaster";

	}
}
