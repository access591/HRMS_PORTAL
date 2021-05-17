package com.hrms.controller;

import java.text.ParseException;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.model.LocalConvyence;
import com.hrms.model.LocalConvyenceDetail;
import com.hrms.model.LocalConvyenceUtil;
import com.hrms.model.MenuModule;

import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.LocalConvyenceDetailService;
import com.hrms.service.LocalConvyenceService;
import com.hrms.service.ModuleService;

@Controller
public class LocalConvyenceController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	DepartmentService  departmentService;
	@Autowired
	DesignationService designationService;
	@Autowired
	LocalConvyenceService  localConvyenceService;
	@Autowired
	LocalConvyenceDetailService localConvyenceDetailService;
	
	@GetMapping("/localConvyence")
	public String localConvyence(Model model,HttpSession session) {

		  List<Employee> listEmployee = employeeService.getAllEmployees();
		  model.addAttribute("listEmployee", listEmployee);
		
	 
		String userCode= (String)session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
	  
	  session.setAttribute("username",session.getAttribute("username"));
	  return"localConvyence";
		}

	
	//@CrossOrigin
	@ResponseBody
    @GetMapping("/viewEmployeeDetails/{id}")
    public LocalConvyenceUtil  getLocalConvyenceById(@PathVariable(value = "id") String id) {
		Employee e = employeeService.findEmployeeById(id);
		

		
		Department d = departmentService.findDepartmentById(e.getDepartmentCode());
		Designation des=designationService.findDesignationById(e.getDesignationCode());
		LocalConvyenceUtil l=new LocalConvyenceUtil(
					e.getEmpName(),d.getDeptName(),des.getDesgName(),e.getEmployeePayeeCode());
		
        return l;
    }

	
	@PostMapping("/saveLocalConvyence")
	public String saveLocalConvyence(@ModelAttribute("localConvyence")LocalConvyenceUtil localCon, Model model, HttpSession session,HttpServletRequest request) throws ParseException {
		String insertedBY = (String) session.getAttribute("USER_NAME");
		LocalConvyence lc=new LocalConvyence();
		LocalConvyenceDetail ld=new LocalConvyenceDetail();
		
		
		Employee emp = new Employee();
		emp.setEmpCode(localCon.getEmpCode());
		lc.setEmpCode(emp);
		lc.setLocalConvDate(localCon.getLocalConvDate());
		lc.setInsBy(insertedBY);
		lc.setApprovalStatus("N");
		lc.setTotalClaim(localCon.getTotalClaim());
		lc.setTotalPas(localCon.getTotalPas());
		localConvyenceService.addlocalConvyence(lc);
		
		String localCVid=lc.getLocalConvId();
		//String tdate=u.getTourPlanDate();
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
						ld.setStartPlace(request.getParameter("startPlace" + i));
					} else {
						ld.setStartPlace("" + i);
					}
					
					if(request.getParameter("vistPlace" + i) != null) {
						ld.setVistPlace(request.getParameter("vistPlace" + i));
					} else {
						ld.setVistPlace("" + i);
					}
					
				
					if(request.getParameter("purposeOfVist" + i) != null) {
						ld.setPurposeOfVist(request.getParameter("purposeOfVist" + i));
					} else {
						ld.setPurposeOfVist("" + i);
					}
					
					/*
					 * if(request.getParameter("fromDate" + i) != null) {
					 * ld.setFromDate(request.getParameter("fromDate" + i)); } else {
					 * ld.setFromDate("" + i); }
					 */
					
					
					if(request.getParameter("modeOfTravel" + i) != null) {
						ld.setModeOfTravel(request.getParameter("modeOfTravel" + i));
					} else {
						ld.setModeOfTravel("" + i);
					}
					
					
					if(request.getParameter("distanceKm" + i) != null) {
						ld.setDistanceKm(request.getParameter("distanceKm" + i));
					} else {
						ld.setDistanceKm("" + i);
					}
					
				
					
				
					/*
					 * tourPlan.setTourPlanId(tid); tourPlan.setTourPlanDate(tdate);
					 * tourPlanDetail.setTourPlanId(tourPlan);
					 * tourPlanDetail.setTourPlanDate(tourPlan);
					 */
					
					insertStatusMR= localConvyenceDetailService.addTourPlanDetail(ld);
					
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

		return "redirect:/localConvyence";
	}
	
 
	
}
