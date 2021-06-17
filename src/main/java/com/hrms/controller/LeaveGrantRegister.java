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

import com.hrms.ImageUtil;
import com.hrms.model.Employee;
import com.hrms.model.Leave;
import com.hrms.model.LeaveGrant;
import com.hrms.util.LeaveGrantUtil;
import com.hrms.model.MenuModule;
import com.hrms.service.EmployeeService;
import com.hrms.service.LeaveGrantRegisterService;
import com.hrms.service.LeaveService;
import com.hrms.service.ModuleService;

@Controller
public class LeaveGrantRegister {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	LeaveService leaveService;
	@Autowired
	LeaveGrantRegisterService leaveGrantRegisterService;
	@GetMapping("/leaveGrant")
	public String leaveGrantRegister(Model model,HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<Employee> listEmployee = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", listEmployee);
		List<Leave> listLeave = leaveService.getAllLeaves();
		model.addAttribute("listLeave", listLeave);
		List<LeaveGrant> listLeaveGrant = leaveGrantRegisterService.getAllLeaveGrants();
		model.addAttribute("listLeaveGrant", listLeaveGrant);
		String userCode = (String) session.getAttribute("username");
		session.setAttribute("imgUtil", new ImageUtil());
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username",session.getAttribute("username"));
			return "leaveGrant";
		}
	
	@PostMapping("/saveLeaveGrant")
	public String saveLeaveGrant(@ModelAttribute("leaveGrant")LeaveGrantUtil leaveGrantUtil, Model model, HttpSession session) {
		Employee emp=new Employee();
		emp.setEmpCode(leaveGrantUtil.getEmpCode());
		Leave leave=new Leave();
		leave.setLevCode(leaveGrantUtil.getLevCode());
		LeaveGrant leaveGrant=new  LeaveGrant();
		leaveGrant.setNoOfLeavesGranted(leaveGrantUtil.getNoOfLeavesGranted());
		leaveGrant.setEmpCode(emp);
		leaveGrant.setLevCode(leave);
		leaveGrant.setYear(leaveGrantUtil.getYear());
		leaveGrant.setClosingBal(leaveGrantUtil.getClosingBal());
		leaveGrant.setLeaveAvailed(leaveGrantUtil.getLeaveAvailed());
		leaveGrant.setPreviousYrBalance(leaveGrantUtil.getPreviousYrBalance());
		leaveGrantRegisterService.addLeaveGrant(leaveGrant);
		List<LeaveGrant> listLeaveGrant = leaveGrantRegisterService.getAllLeaveGrants();
		model.addAttribute("listLeaveGrant", listLeaveGrant);
		session.setAttribute("username", session.getAttribute("username"));

		 return"redirect:/leaveGrant";

	}
	
	
	
	@GetMapping(value = {"/editLeaveGrant/{id}"})
	  public String editdesignation(@PathVariable("id")String id,  Model model,HttpSession session)
	   { 
		List<Employee> listEmployee = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", listEmployee);
		List<Leave> listLeave = leaveService.getAllLeaves();
		model.addAttribute("listLeave", listLeave);
		LeaveGrant leaveGrantEdit = leaveGrantRegisterService.findLeaveGrantById(id);
		  model.addAttribute("leaveGrantEdit", leaveGrantEdit);
	
	      session.setAttribute("username",session.getAttribute("username")); 
	         return "/editLeaveGrant"; 
	  }
	
	
	@PostMapping("/updateLeaveGrant")
	public String updateLeaveGrant(@ModelAttribute("leaveGrant")LeaveGrantUtil leaveGrantUtil , Model model)
	{
		Employee emp=new Employee();
		emp.setEmpCode(leaveGrantUtil.getEmpCode());
		Leave leave=new Leave();
		leave.setLevCode(leaveGrantUtil.getLevCode());
		LeaveGrant leaveGrant=new  LeaveGrant();
		leaveGrant.setNoOfLeavesGranted(leaveGrantUtil.getNoOfLeavesGranted());
		leaveGrant.setLeaveGrantCode(leaveGrantUtil.getLeaveGrantCode());
		leaveGrant.setEmpCode(emp);
		leaveGrant.setLevCode(leave);
		leaveGrant.setYear(leaveGrantUtil.getYear());
		leaveGrant.setClosingBal(leaveGrantUtil.getClosingBal());
		leaveGrant.setLeaveAvailed(leaveGrantUtil.getLeaveAvailed());
		leaveGrant.setPreviousYrBalance(leaveGrantUtil.getPreviousYrBalance());
		  this.leaveGrantRegisterService.updateLeaveGrant(leaveGrant);
		  return"redirect:/leaveGrant";
		
	}
	
	
	@GetMapping(value = {"/deleteLeaveGrant/{id}"})
	public String deleteLeaveGrant(@PathVariable("id")String id,  Model model,HttpSession session)
	 { 
		  this.leaveGrantRegisterService.removeLeaveGrant(id);
	    session.setAttribute("username",session.getAttribute("username")); 
	    return"redirect:/leaveGrant";
	}



}
