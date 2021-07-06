package com.hrms.controller;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrms.model.Department;

import com.hrms.helper.Message1;
import com.hrms.model.LeaveRequest;
import com.hrms.model.MenuModule;
import com.hrms.model.UserEntity;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
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
	@Autowired EmployeeService employeeService;
	@Autowired DesignationService designationService;
	
	
	@ModelAttribute
	public void commonData(Model model,HttpSession session) {
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {  
			model.addAttribute("modules", modules);
		}
	}
	
	@GetMapping("/leaveApproval")
	public String leaveApproval(Model model, HttpSession session) {

		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		List<Department> listDepartment = departmentService.getAllDepartments();
		List<LeaveRequest> listLeaveApproval = leaveRequestService.getEmployeeByStatusN();

		if(listLeaveApproval != null) {
			model.addAttribute("listLeaveApproval" , listLeaveApproval);
		}
		
		if(listDepartment != null) {
			model.addAttribute("listDepartment", listDepartment);
		}

		session.setAttribute("username", session.getAttribute("username"));

		return "leaveApproval";
	}
	
	
	
	
	@ResponseBody
	@GetMapping("/leaverequest/{deptCode}")
	public List<LeaveRequest> getAllLeaveRequestBydept(@PathVariable("deptCode") String deptCode) {
		 
		
		
		return leaveRequestService.findAllByDeptCodeAndStatusN(deptCode);
		
	}
	
	
	
	@GetMapping("/approveLeaveRequest/{leaveRequestId}/{status}")
	public String approveLeaveRequest(@PathVariable("leaveRequestId") String leaveid, @PathVariable("status") String status,
			Model model ,HttpSession session) {
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		
		String userCode = (String) session.getAttribute("username");
		LeaveRequest leaveRequest = leaveRequestService.findLeaveRequestById(Long.valueOf(leaveid));
		
		System.out.println("leave request approval : "+ leaveRequest);
		String activeUser = "";
		try {
			UserEntity user = userService.findDataById(userCode);
			activeUser = user.getUserName();
			System.out.println("active user =====>"+ activeUser);
			
			if(status.equals("Y")) {
				leaveRequest.setStatus("Y");
				leaveRequest.setApproevedBy(activeUser);
				leaveRequest.setApprovedDate(new Date().toString());
				session.setAttribute("message", new Message1("Request has been Approved","alert-primary"));
				
			}else {
				leaveRequest.setStatus("C");
				leaveRequest.setCancelBy(activeUser);
				leaveRequest.setCancelDate(new Date().toString());
				session.setAttribute("message", new Message1("Request has been Canceled","alert-primary"));
			}
			
			leaveRequestService.updateLeaveRequest(leaveRequest);  
			
			
		}catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("message", new Message1("Something went Wrong !!","alert-info"));
		}
		
		
		
		List<LeaveRequest> listLeaveApproval = leaveRequestService.getEmployeeByStatusY();
		if(listLeaveApproval != null) {
			model.addAttribute("listLeaveApproval" , listLeaveApproval);
		}
			
		session.setAttribute("username", userCode);
		
		leaveApproval(model, session);
		
		return "redirect:/leaveApproval";
	}
	
	
	@GetMapping(value = { "/deleteLeaveApproval/{id}" })
	public String deleteActivity(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		String userCode = (String) session.getAttribute("username");
		this.leaveRequestService.removeLeaveRequest(id);
		session.setAttribute("username", userCode);
		return "redirect:/"+ pageMappingService.PageRequestMapping(reqPage, pageno);
	}
	
	
	

}
