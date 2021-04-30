package com.hrms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrms.model.Department;
import com.hrms.model.LeaveRequest;
import com.hrms.model.MenuModule;
import com.hrms.model.UserEntity;
import com.hrms.service.DepartmentService;
import com.hrms.service.LeaveRequestService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;
import com.hrms.service.UserService;

@Controller
public class LeaveApprovalController {
	
	int pageno = 62;
	String reqPage = "/leaveApproval";
	
	@Autowired
	private ModuleService moduleService;
	@Autowired
	PageMappingService pageMappingService;
	@Autowired DepartmentService departmentService;
	@Autowired LeaveRequestService leaveRequestService;
	@Autowired UserService userService;
	
	@GetMapping("/leaveApproval")
	public String leaveApproval(Model model, HttpSession session) {

		System.out.println("leave approval methods");
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		List<Department> listDepartment = departmentService.getAllDepartments();
		List<LeaveRequest> listLeaveApproval = leaveRequestService.getEmployeeByStatusY();
		
		
		
		if(listLeaveApproval != null) {
			model.addAttribute("listLeaveApproval" , listLeaveApproval);
		}
		if (modules != null) {  
			model.addAttribute("modules", modules);
		}
		if(listDepartment != null) {
			model.addAttribute("listDepartment", listDepartment);
		}

		session.setAttribute("username", session.getAttribute("username"));

		return pageMappingService.PageRequestMapping(reqPage, pageno);
	}
	
	
	
	
	@ResponseBody
	@GetMapping("/leaverequest/{deptCode}")
	public List<LeaveRequest> getAllLeaveRequestBydept(@PathVariable("deptCode") String deptCode) {
		 
		return leaveRequestService.findAllByDeptCodeAndStatus(deptCode);
		
	}
	
	
	@ResponseBody
	@GetMapping("/approveLeaveRequest/{leaveRequestId}")
	public void approveLeaveRequest(@PathVariable("leaveRequestId") String leaveid, Model model ,HttpSession session) {
		
		String userCode = (String) session.getAttribute("username");
		LeaveRequest leaveRequest = leaveRequestService.findLeaveRequestById(Long.valueOf(leaveid));
		
		System.out.println("leave request approval : "+ leaveRequest);
		
		leaveRequest.setStatus("Y");
		leaveRequest.setApproevedBy("Rahul");
		leaveRequest.setApprovedDate(new Date().toString());
		leaveRequestService.updateLeaveRequest(leaveRequest);   
		
		List<LeaveRequest> listLeaveApproval = leaveRequestService.getEmployeeByStatusY();
		if(listLeaveApproval != null) {
			model.addAttribute("listLeaveApproval" , listLeaveApproval);
		}
			
		session.setAttribute("username", userCode);
		
		leaveApproval(model, session);
		
		//return null;
	}
	
	
	@GetMapping(value = { "/deleteLeaveApproval/{id}" })
	public String deleteActivity(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		String userCode = (String) session.getAttribute("username");
		this.leaveRequestService.removeLeaveRequest(id);
		session.setAttribute("username", userCode);
		return "redirect:/"+ pageMappingService.PageRequestMapping(reqPage, pageno);
	}
	
	
	@GetMapping(value = { "/viewLeaveApproval/{id}" })
	public String viewLeaveRequestByEmpId(@PathVariable("id")String leaveRequestId,
						Model model,HttpSession session) {
		int pagenoView = 61;
		String reqPageView = "/viewLeaveRequest";
		
		System.out.println("view Leave Approval module : ");
		LeaveRequest leaveRequest = this.leaveRequestService.findLeaveRequestById(Long.parseLong(leaveRequestId));
		
		if(leaveRequest != null) {
			model.addAttribute("leaveDetail", leaveRequest);
		}
		List<MenuModule> modules = moduleService.getAllModulesList(session.getAttribute("username").toString());
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		model.addAttribute("header", "View Leave Approval");
		model.addAttribute("myhref", "leaveApproval");
		return pageMappingService.PageRequestMapping(reqPageView, pagenoView);
	}
	
	
	
	
	
	
	

}
