package com.hrms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import com.hrms.helper.Message1;
import com.hrms.model.Employee;
import com.hrms.model.Leave;
import com.hrms.model.LeaveRequest;
import com.hrms.model.MenuModule;
import com.hrms.model.UserEntity;
import com.hrms.service.EmployeeService;
import com.hrms.service.LeaveDetailService;
import com.hrms.service.LeaveGrantRegisterService;
import com.hrms.service.LeaveRequestService;
import com.hrms.service.LeaveService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;
import com.hrms.service.UserService;

@Controller
public class LeaveRequestController {
	
	int pageno = 49;
	private String reqPage = "/leaveRequest";
	
	@Autowired EmployeeService employeeService;
	@Autowired LeaveRequestService leaveRequestService;
	@Autowired PageMappingService pageMappingService;
	@Autowired LeaveService leaveService;
	@Autowired UserService userService;
	@Autowired LeaveGrantRegisterService leaveGrantRegisterService;
	@Autowired LeaveDetailService leaveDetailService;
	//@Autowired LeaveGrantService leaveGrantService;
	
	
	@Autowired
	private ModuleService moduleService;
	
	@InitBinder("leaveRequest")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
        binder.registerCustomEditor(Date.class, "applyDate",
                                    new CustomDateEditor(dateFormatter, true));
        binder.registerCustomEditor(Date.class, "toDate",
                new CustomDateEditor(dateFormatter, true));
        
        binder.registerCustomEditor(Date.class, "fromDate",
                new CustomDateEditor(dateFormatter, true));
       
       
    }
	
	
	@ModelAttribute
	public void commonData(Model model , HttpSession session) {
		
		String userCode = (String) session.getAttribute("username");
		
		
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		
		
		session.setAttribute("username" , userCode);
		
	}
	
	@GetMapping("/leaveRequest")
	public String empPayDetail(@ModelAttribute("leaveRequest")LeaveRequest leaveRequest,Model model, HttpSession session) {
		
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		List<Employee> listEmployee = employeeService.getAllEmployees();
		
		UserEntity user = null;
		List<LeaveGrantRegister> grant;
//		try {
//			user = userService.findUserById(session.getAttribute("username").toString());
//			String employeCode = user.getEmpCode().getEmpName();
//			
//			grant = leaveGrantService.findLeaveGrantByEmpCode(employeCode);
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		

		
		
		
		List<Leave> listLeave = leaveService.getAllLeaves();
		if(listLeave != null) {
			model.addAttribute("listLeave", listLeave);
		}
		
		
		
		List<LeaveRequest> listLeaveRequest = leaveRequestService.getAllLeaves();
		
		//System.out.println("leave request size : " + listLeaveRequest.size());
		
		if(listLeaveRequest != null) {
			model.addAttribute("listRequest",listLeaveRequest);
		}
		
		if(listEmployee != null)
		{
			model.addAttribute("listEmployee" , listEmployee);
		}
	
		
		return "leaveRequest";
	}
	
	@PostMapping("/saveLeaveRequest")
	public String saveLeaveRequest(@Valid @ModelAttribute("leaveRequest")LeaveRequest leaveRequest,HttpSession session
			) {
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		
		try {
			leaveRequestService.addLeave(leaveRequest);
			session.setAttribute("message",new Message1("Data has been saved Successfully","alert-primary"));
		}catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("message", new Message1("Something went Wrong !!","alert-info"));
		}
		
		return "redirect:leaveRequest";
	}
	
	
	
	
	
	
	@GetMapping(value = { "/deleteLeaveRequest/{id}" })
	public String deleteActivity(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		try {
			this.leaveRequestService.removeLeaveRequest(id);
			session.setAttribute("message",new Message1("Data has been Removed","alert-primary"));
		}catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("message",new Message1("Something went wrong","alert-primary"));
		}
		
		return "redirect:/"+ pageMappingService.PageRequestMapping(reqPage, pageno);
	}
		
}
