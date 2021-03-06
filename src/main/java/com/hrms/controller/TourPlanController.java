package com.hrms.controller;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hrms.ImageUtil;
import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;

import com.hrms.model.MenuModule;
import com.hrms.model.TourPlan;

import com.hrms.model.TourPlanDetails;
import com.hrms.model.TourPlanUtil;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;
import com.hrms.service.TourPlanDetailService;
import com.hrms.service.TourPlanService;

@Controller
public class TourPlanController {
	
	int pageno = 51;
	String reqPage = "/tourPlan";
	@Autowired
	DepartmentService departmentService;
	@Autowired PageMappingService pageMappingService;
	@Autowired 
	ModuleService moduleService;
	@Autowired 
	 EmployeeService employeeService;
	@Autowired
    DesignationService designationService;
	@Autowired 
	TourPlanService tourPlanService;
	@Autowired
	TourPlanDetailService tourPlanDetailService;

	@GetMapping("/tourPlan")
	public String tourPlan(Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<TourPlan> listTourPlan=tourPlanService.getAllTourPlan();
		model.addAttribute("ListTourPlan", listTourPlan);
		List<Department> listDepartment = departmentService.getAllDepartments();
		model.addAttribute("listDepartment", listDepartment);
		
		List<Employee> lrt = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", lrt);
		
	
		List<Designation> listDesignation = designationService.getAllDesignations();
		model.addAttribute("listDesignation", listDesignation);
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));
		return "tourPlan";
	}  
	
	
	@PostMapping("/saveTourPlan")
	public String saveTourPlan(@ModelAttribute("tourPlan")TourPlanUtil u, Model model, HttpSession session,HttpServletRequest request) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		String insertedBY = (String) session.getAttribute("USER_NAME");
		TourPlan tourPlan=new TourPlan();
		TourPlanDetails tourPlanDetail=new TourPlanDetails();
		Employee emp = new Employee();
		emp.setEmpCode(u.getEmpCode());
		Department dept = new Department();
		dept.setDepartmentCode(u.getDepartmentCode());
		Designation des = new Designation();
		des.setDesgCode(u.getDesgCode());
		tourPlan.setEmpCode(emp);
		tourPlan.setDepartmentCode(dept);
		tourPlan.setDesgCode(des);
       
		tourPlan.setTourPlanDate(u.getTourPlanDate());
		tourPlan.setMobNumber(u.getMobNumber());
		tourPlan.setTourStartDate(u.getTourStartDate());
		tourPlan.setTourEndDate(u.getTourEndDate());
		tourPlan.setApprovalStatus("N");
		tourPlan.setInsBy(insertedBY);

		tourPlan.setInsBy(insertedBY);

		tourPlanService.addTourPlan(tourPlan);
		String tid=tourPlan.getTourPlanId();
		Date tdate=u.getTourPlanDate();
		 int flag = 0;
	   		int counter = 1;
			try {
				
				
				boolean insertStatusMR = false;
				counter = Integer.parseInt(request.getParameter("_cr"));
				System.out.println("counter::::::::::::::::::::" + counter);
				
			
				for (int i =0; i < counter; i++) 
				{
					System.out.println("for loop run time>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					if (request.getParameter("startPlace" + i) != null) {
						tourPlanDetail.setStartPlace(request.getParameter("startPlace" + i));
					} else {
						tourPlanDetail.setStartPlace("" + i);
					}
					
					if(request.getParameter("endPlace" + i) != null) {
						tourPlanDetail.setEndPlace(request.getParameter("endPlace" + i));
					} else {
						tourPlanDetail.setEndPlace("" + i);
					}
					
				
					if(request.getParameter("fromDate" + i) != null) {
						
						String sDate1 = request.getParameter("fromDate" + i);
						Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
						tourPlanDetail.setFromDate(date1);
						
					}
					
					if(request.getParameter("toDate" + i) != null) {
						String sDate1 = request.getParameter("toDate" + i);
						Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
						tourPlanDetail.setToDate(date1);
					} 
					
					
					if(request.getParameter("purpose" + i) != null) {
						tourPlanDetail.setPurpose(request.getParameter("purpose" + i));
					} else {
						tourPlanDetail.setPurpose("" + i);
					}
					
				
					
				
					tourPlan.setTourPlanId(tid);
					tourPlan.setTourPlanDate(tdate);
					tourPlanDetail.setTourPlanId(tourPlan);
					tourPlanDetail.setTourPlanDate(tourPlan);
					
					insertStatusMR= tourPlanDetailService.addTourPlanDetail(tourPlanDetail);
					
					if (insertStatusMR) {
						System.out.println("Counter" + flag);
						flag++;

					}
					
				}
				
				
				if (flag > 0) {
					session.setAttribute("Message", "Data added successfully.");
					
				} else {
					System.out.println("Enter into  failure part :");
					
				}

			
			} catch (Exception e) {
				
				
				e.printStackTrace();
			}
		
		
		
		
		
		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/tourPlan";
	}
	
	@GetMapping(value = {"/editTourPlan/{id}"})
	public String editTourPlan(@PathVariable("id")String id,  Model model,HttpSession session)
	 { 	if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<Department> listDepartment = departmentService.getAllDepartments();
		model.addAttribute("listDepartment", listDepartment);
		
		List<Employee> lrt = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", lrt);
		session.setAttribute("imgUtil", new ImageUtil());
	
		List<Designation> listDesignation = designationService.getAllDesignations();
		model.addAttribute("listDesignation", listDesignation);
		
		TourPlan tourPlanEdit =	tourPlanService.findByIdTourPlan(id);
		  model.addAttribute("tourPlanEdit", tourPlanEdit);

	   
	    return "editTourPlan";
	}

	@PostMapping("/updateTourPlan")
	  public String updateTourPlan(@ModelAttribute("tourPlanEdit") TourPlan tourPlan, Model model, HttpSession session){
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		tourPlan.setApprovalStatus("N");
		  this.tourPlanService.updateTourPlan(tourPlan);
	    	  
		  
		  return "redirect:/tourPlan";
	  }

	@GetMapping(value = { "/deleteTourPlan/{id}" })
public String deleteTourPlan(@PathVariable("id") String id, Model model,
		HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
	try {
		
		tourPlanService.removeTourPlan(id);
		session.setAttribute("username", session.getAttribute("username"));

	} catch (Exception e) {
		e.printStackTrace();
	}

	return "redirect:/tourPlan";
}
}
