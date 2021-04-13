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
		
		//List<HashMap<String,Object>> leaveRequest = 
		return leaveRequestService.findAllByDeptCodeAndStatus(deptCode);
		
	}
	
	
	@ResponseBody
	@GetMapping("/approveLeaveRequest/{leaveid}")
	public String approveLeaveRequest(@PathVariable("leaveid") String leaveid, Model model ,HttpSession session) {
		
		LeaveRequest leaveRequest = leaveRequestService.findLeaveRequestById(Long.valueOf(leaveid));
		
		System.out.println("leave request approval : "+ leaveRequest);
		
		leaveRequest.setStatus("Y");
		leaveRequestService.updateLeaveRequest(leaveRequest);
		leaveApproval(model, session);
		return null;
	}
	
	@GetMapping("demo")
	public String demoHandler() {
		System.out.println("testing");
		return null;
	}
	
	
	
	
	
	
	

}
