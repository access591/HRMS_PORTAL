package com.hrms.controller;


import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;

import com.hrms.model.Department;
import com.hrms.model.Employee;
import com.hrms.model.MenuModule;
import com.hrms.model.OrderIssueTracking;
import com.hrms.reports.OrderTrackingReport;
import com.hrms.service.DepartmentService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.OrderIssueTrackingService;

@Controller
public class OrderIssueTrackingController {

	@Autowired ModuleService moduleService;
	@Autowired OrderIssueTrackingService orderIssueTrackingService;
	@Autowired EmployeeService employeeService;
	@Autowired DepartmentService departmentService;
	
	@InitBinder("orderIssueTracking")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
        
        binder.registerCustomEditor(Date.class, "orderTrackingDate",
                new CustomDateEditor(dateFormatter, true));

       
    }
	
	
	@GetMapping("orderissuetracking")
	public String orderIssueTrackingPage(@ModelAttribute("orderIssueTracking")OrderIssueTracking orderIssueTracking,
			Model model,HttpSession session) {
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<Employee> employeeList = employeeService.getAllEmployees();
		if (employeeList != null) {
			model.addAttribute("employeeList", employeeList);
		}
		List<OrderIssueTracking> listOrderIssueTracking = orderIssueTrackingService.getAllOrderIssueTracking();
		if(listOrderIssueTracking != null) {
			model.addAttribute("listOrderIssueTracking", listOrderIssueTracking);
		}
		
		session.setAttribute("username", userCode);
		return "orderIssueTracking";
		
	}
	
	@PostMapping("saveOrderIssueTracking")
	public String saveOrderIssueTracking(@ModelAttribute("orderIssueTracking")OrderIssueTracking orderIssueTracking,
			Model model,HttpSession session,@RequestParam("orderFile") MultipartFile orderFile) throws IOException {
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		orderIssueTracking.setOrderFileName(orderFile.getOriginalFilename());
		orderIssueTrackingService.saveOrderIssueTracking(orderIssueTracking);
		
		
		return "redirect:orderissuetracking";
	}
	
	@GetMapping("editOrderTracking/{id}")
	public String editOrderIssueTracking(@PathVariable("id")String orderIssueTrackingId,
			@ModelAttribute("orderIssueTracking")OrderIssueTracking orderIssueTracking
			,Model model,HttpSession session) {
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		
		
		List<Department> departmentList = departmentService.getAllDepartments();
		if (departmentList != null) {
			model.addAttribute("departmentList", departmentList);
		}
		
		if(orderIssueTrackingService.findOrderIssueTrackingById(Long.parseLong(orderIssueTrackingId)) != null) {
			model.addAttribute("orderIssueTracking", orderIssueTrackingService.findOrderIssueTrackingById(Long.parseLong(orderIssueTrackingId)));
		}
		List<Employee> employeeList = employeeService.getAllEmployees();
		if (employeeList != null) {
			model.addAttribute("employeeList", employeeList);
		}
		
		return "editOrderIssueTracking"; 
	}
	
	@PostMapping("updateOrderTracking")
	public String updateOrderIssueTracking(@ModelAttribute("orderIssueTracking")OrderIssueTracking orderIssueTracking,
			@RequestParam("orderFile") MultipartFile orderFile,HttpSession session) {
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		orderIssueTracking.setOrderFileName(orderFile.getOriginalFilename());
		orderIssueTrackingService.updateOrderIssueTracking(orderIssueTracking);
		return "redirect:orderissuetracking";
	}
	
	@GetMapping(value = {"deleteOrderTracking/{id}"})
	public String deleteOrderIssueTracking(@PathVariable("id") String orderTrackingId, Model model, HttpSession session) {
		System.out.println("=====================>");
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		orderIssueTrackingService.removeOrderIssueTracking(Long.parseLong(orderTrackingId));
		
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:orderissuetracking";
	}
	
	
	@GetMapping("ordertrackreport")
	public String viewOrderTrackReport(Model model,HttpSession session) {
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		List<Employee> employeeList = employeeService.getAllEmployees();
		if (employeeList != null) {
			model.addAttribute("employeeList", employeeList);
		}
		
		return "orderIssuedReport";
	}
	
	@Autowired OrderTrackingReport orderTrackReport;
	@PostMapping("createordertrackreport")
	public String createOrderTrackReport(@RequestParam("empCode")String empCode,
			HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		List<OrderIssueTracking> orderTracking = new ArrayList<>();
		if(empCode.equals("ALL")) {
			orderTracking = orderIssueTrackingService.getAllOrderIssueTracking();
			System.out.println("budget list : ====>"+ orderTracking.get(0).getOrderFileName());
			orderTrackReport.createOrderTrackReport(response, request, orderTracking, "All");
		}else {
			OrderIssueTracking order = orderIssueTrackingService.findOrderIssueTrackingByIssuedby(empCode);
			orderTracking.add(order);
			orderTrackReport.createOrderTrackReport(response, request, orderTracking, empCode);
		}
		
		return null;
	}
	
}
