package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.hrms.helper.Message1;
import com.hrms.model.EmployeeRequisition;
import com.hrms.model.MenuModule;
import com.hrms.service.DepartmentService;
import com.hrms.service.EmployeeRequisitionService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;

@Controller
public class EmployeeRequisitionApprovalController {

	@Autowired ModuleService moduleService;
	@Autowired PageMappingService pageMappingService;
	@Autowired EmployeeRequisitionService employeeRequisitionService;
	@Autowired DepartmentService departmentService;
	
	
	@ModelAttribute
	public void commonData(Model model,HttpSession session) {
		session.setAttribute("username", session.getAttribute("username"));
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		
	}
	
	
	@GetMapping("employeeRequisitionApproval")
	public String employeeRequisitionApproval(@ModelAttribute("employeeRequisition")EmployeeRequisition employeeRequisition ,
			Model model, HttpSession session) {
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
	
		List<EmployeeRequisition> listEmployeeReq = employeeRequisitionService.getAllPendingEmployeeRequisition();
	
		model.addAttribute("listCommonUtil", listEmployeeReq);

		//session.setAttribute("imgUtil", new ImageUtil());
		return "EmployeeRequisitionApproval"; 
		
		
	}
	
	
	@GetMapping("approveRequisition/{id}/{status}")
	public String approveRequisition(@PathVariable("id") String reqCode,@PathVariable("status") String approvalStatus,
			HttpSession session) {
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		try {
			employeeRequisitionService.approvedByReqCodeAndStatus(reqCode,approvalStatus);
			
			if(approvalStatus.equals("Y")) {
				session.setAttribute("message",new Message1("Employee Requisition has been Approved","alert-primary"));
			}else if(approvalStatus.equals("C")) {
				session.setAttribute("message",new Message1("Employee Requisition has been Canceled","alert-primary"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		return "redirect:/employeeRequisitionApproval";
	}
	
	
	
}
