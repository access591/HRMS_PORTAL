package com.hrms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrms.ImageUtil;
import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;


import com.hrms.model.MenuModule;

import com.hrms.model.StaffPostingDuties;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.StaffPostingDutiesService;
import com.hrms.util.SaffPostingDutiesUtil;



@Controller
public class StaffPostingDutiesController {
	
	@Autowired
	private ModuleService moduleService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	DepartmentService  departmentService;
	@Autowired
	DesignationService designationService;
	@Autowired
	StaffPostingDutiesService staffPostingDutiesService;
	
	
	@GetMapping("/staffPostingDuties")
	public String staffPostingDuties(Model model,HttpSession session) {
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		 List<Employee> listEmployee = employeeService.getAllEmployees();
		  model.addAttribute("listEmployee", listEmployee);
			session.setAttribute("imgUtil", new ImageUtil());
		  List<StaffPostingDuties> listOfstaffDuties = staffPostingDutiesService.getAllStaffPostingDuties();
		  
		  List<SaffPostingDutiesUtil> listSaffPostingDutiesUtil=new ArrayList<>();
		  for (int i = 0; i < listOfstaffDuties.size(); i++) {
			  String empCode = listOfstaffDuties.get(i).getEmpCode().getEmpCode();
			  SaffPostingDutiesUtil listofutil=new SaffPostingDutiesUtil();
			  Employee employee = employeeService.findEmployeeById(empCode);
			    Department department = departmentService.findDepartmentById(employee.getDepartmentCode());
			    Designation designation = designationService.findDesignationById(employee.getDesignationCode());
			    listofutil.setJobCode(listOfstaffDuties.get(i).getJobCode());
			    listofutil.setDeptName(department.getDeptName());
			    listofutil.setDesgName(designation.getDesgName());
			    listofutil.setEmpName(employee.getEmpName());
			    listSaffPostingDutiesUtil.add(listofutil);
			    model.addAttribute("listStaffDuties",listSaffPostingDutiesUtil);
			  
			  
			  
		  }
		  
		  
		  session.setAttribute("username",session.getAttribute("username"));
		  return"staffPostingDuties"; 
	}
	
	//@CrossOrigin
		@ResponseBody
	    @GetMapping("/viewEmployeeDetailsDuties/{id}")
	    public SaffPostingDutiesUtil  getLocalConvyenceById(@PathVariable(value = "id") String id) {
			Employee e = employeeService.findEmployeeById(id);
			Department d = departmentService.findDepartmentById(e.getDepartmentCode());
			Designation des=designationService.findDesignationById(e.getDesignationCode());
			return new SaffPostingDutiesUtil(e.getEmpName(),d.getDeptName(),des.getDesgName(),e.getEmployeePayeeCode());
			
	      
	    }
		
		@PostMapping("/savestaffDuties")
		public String savestaffDuties(@ModelAttribute("saffPostingDuties") SaffPostingDutiesUtil ux, Model model,HttpSession session) {
			
			StaffPostingDuties staffduties=new StaffPostingDuties();
			Employee emp = new Employee();
			emp.setEmpCode(ux.getEmpCode());
			staffduties.setEmpCode(emp);
			staffduties.setPositionCode(ux.getPositionCode());
			staffduties.setJobDesc(ux.getJobDesc());
			
			String insertedBY = (String) session.getAttribute("USER_NAME");
			staffduties.setInsBy(insertedBY);
			this.staffPostingDutiesService.addStaffPostingDuties(staffduties);
			

			session.setAttribute("username", session.getAttribute("username"));
			return "redirect:/staffPostingDuties";
			
		
		}
		
		
		
		@GetMapping(value = { "/editStaffPostingDuties/{id}" })
		public String editStaffPostingDuties(@PathVariable("id") String id, Model model, HttpSession session) {
			
			List<Employee> listEmployee = employeeService.getAllEmployees();
			  model.addAttribute("listEmployee", listEmployee);
			  
			  StaffPostingDuties staffPostingDutiesEdit = staffPostingDutiesService.StaffPostingDutieById(id);
			model.addAttribute("staffPostingDutiesEdit", staffPostingDutiesEdit);
			session.setAttribute("imgUtil", new ImageUtil());
			session.setAttribute("username", session.getAttribute("username"));
			 return "editStaffPostingDuties";
		}
		
		@PostMapping("/updateStaffDuties")
		public String updateStaffDuties(@ModelAttribute("saffPostingDuties") SaffPostingDutiesUtil ux, Model model,HttpSession session) {
			
			StaffPostingDuties staffduties=new StaffPostingDuties();
			Employee emp = new Employee();
			emp.setEmpCode(ux.getEmpCode());
			staffduties.setEmpCode(emp);
			staffduties.setPositionCode(ux.getPositionCode());
			staffduties.setJobDesc(ux.getJobDesc());
			staffduties.setJobCode(ux.getJobCode());
			this.staffPostingDutiesService.UpdateStaffPostingDuties(staffduties);
			

			session.setAttribute("username", session.getAttribute("username"));
			return "redirect:/staffPostingDuties";
			
		
		}
		
		
		
		/**
		 * Delete Staff
		 * @param id
		 * @param model
		 * @param session
		 * @return
		 */
			@GetMapping(value = { "/deleteStaffPostingDuties/{id}" })
			public String deleteStaffDuties(@PathVariable("id") String id, Model model, HttpSession session) {
				this.staffPostingDutiesService.removestaffDuties(id);
				session.setAttribute("username", session.getAttribute("username"));
				return "redirect:/staffPostingDuties";
			}
			
			
			@GetMapping(value = {"/reporStaffPostingDuties" })
			public String reporStaffPostingDuties(Model model, HttpSession session, HttpServletRequest request,
					HttpServletResponse response) {
				String val = null;
				 List<StaffPostingDuties> listOfstaffDuties = staffPostingDutiesService.getAllStaffPostingDuties();
				 session.setAttribute("username", session.getAttribute("username"));
				 List<SaffPostingDutiesUtil> dataList=new ArrayList<>();
				  for (int i = 0; i < listOfstaffDuties.size(); i++) {
					
					  String empCode = listOfstaffDuties.get(i).getEmpCode().getEmpCode();
					  SaffPostingDutiesUtil listofutil=new SaffPostingDutiesUtil();
					  Employee employee = employeeService.findEmployeeById(empCode);
					    Department department = departmentService.findDepartmentById(employee.getDepartmentCode());
					    Designation designation = designationService.findDesignationById(employee.getDesignationCode());
					    
					    listofutil.setJobCode(listOfstaffDuties.get(i).getJobCode());
					    listofutil.setDeptName(department.getDeptName());
					    listofutil.setDesgName(designation.getDesgName());
					    listofutil.setEmpName(employee.getEmpName());
					    listofutil.setEmail(employee.getEmail());
					    listofutil.setMobileNumber(employee.getMobileNumber1());
					    listofutil.setAddress(employee.getAddress1());
					    listofutil.setPositionCode(listOfstaffDuties.get(i).getPositionCode());
					    dataList.add(listofutil);
					 
			
				 
				 
				String reportFileName = "";

				
				if (request.getParameter("_ex") != null) {
					val = request.getParameter("_ex");
					
					if (val.equals("P")) {
						System.out.println("heloo0000000000" + val);

						reportFileName = "staffPostingDuties_pdf";
						staffPostingDutiesService.staffPostingDutiesGenratepdf(request, response, reportFileName, dataList);
					} else if (val.equals("E")) {
						//reportFileName = "bankwisereport_XLS";
						//String filename = "bankwisereport";

					}
					
					
					
				}
				
				  }
				
				return null;

			}
		

			}
			


