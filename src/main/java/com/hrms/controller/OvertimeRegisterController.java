package com.hrms.controller;

import java.text.ParseException;
import java.util.ArrayList;
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

import com.hrms.model.AttendenceRegisterUtil;
import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.model.MenuModule;
import com.hrms.model.OvertimeRegister;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.OvertimeRegisterService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.util.OvertimeRegisterUtil;
@Controller
public class OvertimeRegisterController {
	
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	DesignationService designationService;
	@Autowired
	DepartmentService departmentService;

	@Autowired
	OvertimeRegisterService  overtimeRegisterService;
	
	@GetMapping("/overtimeRegister")
	public String overtimeRegister(Model model, HttpSession session) {
		List<Department> listDepartment = departmentService.getAllDepartments();
		model.addAttribute("listDepartment", listDepartment);
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<OvertimeRegister> listOverTimeR = overtimeRegisterService.getAllOvertimeRegister();
		List<OvertimeRegisterUtil>listOverTime= new ArrayList<OvertimeRegisterUtil>();
		  for (int i = 0; i < listOverTimeR.size(); i++) {
			  
			  String empCode = listOverTimeR.get(i).getEmployee().getEmpCode();
			  OvertimeRegisterUtil ovTime=new OvertimeRegisterUtil();
			  
			  Employee employee = employeeService.findEmployeeById(empCode);
			  Department department = departmentService.findDepartmentById(employee.getDepartmentCode());
			  Designation designation = designationService.findDesignationById(employee.getDesignationCode());
			    
			  ovTime.setEmpName(employee.getEmpName());
			  ovTime.setDeptName(department.getDeptName());
			  ovTime.setDesgName(designation.getDesgName());
			  
			  ovTime.setId(listOverTimeR.get(i).getId());
			  ovTime.setOverTimeDate(listOverTimeR.get(i).getOverTimeDate());
			  ovTime.setEsiYn(listOverTimeR.get(i).getEsiYn());
			  ovTime.setOverTime(listOverTimeR.get(i).getOverTime());
			  ovTime.setRemarks(listOverTimeR.get(i).getRemarks());
			  ovTime.setTimeIN(listOverTimeR.get(i).getTimeIN());
			  ovTime.setTimeOut(listOverTimeR.get(i).getTimeOut());
			  listOverTime.add(ovTime);
			  model.addAttribute("overReg", listOverTime); 
			  
			  
		  }
		  
		
		
		return "overtimeRegister";

	}
	
	//@CrossOrigin
	@ResponseBody
    @GetMapping("/viewOverTimeRegisterBydepartment/{id}")
    public List<OvertimeRegisterUtil>  getLocalConvyenceById(@PathVariable(value = "id") String id,Model model,HttpSession session) {
		Department d=departmentService.findDepartmentById(id);
		List<Employee> e = employeeService.findByDepartmentCode(d.getDepartmentCode());
		  List<OvertimeRegisterUtil> lisOvertimeRegisterUtil = new ArrayList<OvertimeRegisterUtil>();
		  for (int i = 0; i < e.size(); i++) 
		  {
			  String empCode = e.get(i).getEmpCode();
			  OvertimeRegisterUtil lc = new OvertimeRegisterUtil();
			  Employee employee = employeeService.findEmployeeById(empCode);
			  lc.setEmpCode(employee.getEmpCode());
			  lc.setEmpName(employee.getEmpName());
			  lisOvertimeRegisterUtil.add(lc);
		  }
		  return lisOvertimeRegisterUtil;
       
    }
	
	@ResponseBody
    @GetMapping("/viewOverTimeRegisterByEmployee/{id}")
    public OvertimeRegisterUtil  viewOverTimeRegisterByEmployee(@PathVariable(value = "id") String id,Model model,HttpSession session) {
		//Department d=departmentService.findDepartmentById(id);
		Employee employee = employeeService.findEmployeeById(id);
		String empCode = employee.getEmpCode();
		System.out.println(">>>>>>>>xxxxxx>>>>>>>>>>>>>>>>>>" + empCode);
		OvertimeRegisterUtil listEmp = new OvertimeRegisterUtil();
			  
			 
			  Designation designation = designationService.findDesignationById(employee.getDesignationCode());
			  listEmp.setDesgName(designation.getDesgName());
			  listEmp.setEmpCode(employee.getEmpCode());
			  listEmp.setEmpName(employee.getEmpName());
			  
		
		  return listEmp;
       
    }
	
	
	@PostMapping("/saveOverTimeRegister")
	public String saveAttendenceRegister(@ModelAttribute("overTimeRegister")OvertimeRegisterUtil u, Model model, HttpSession session,HttpServletRequest request) throws ParseException {
		String insertedBY = (String) session.getAttribute("USER_NAME");	
		OvertimeRegister overReg=new OvertimeRegister();
		 Employee e=new    Employee();		
	   
	     
	     
	     int flag = 0;
			int counter = 1;
	
	try {
		boolean insertStatusMR = false;
		counter = Integer.parseInt(request.getParameter("_cr"));
		System.out.println("counter::::::::::::::::::::" + counter);
		for (int i =0; i < counter; i++) 
		{
			
			
			
			System.out.println("counter::::::::::::::::::::" + i);
			
			
			if (request.getParameter("overTimeRate" + i) != null) {
				overReg.setOverTimeRate(request.getParameter("overTimeRate" + i));
			} else {
				overReg.setOverTimeRate("" + i);
			}

			if (request.getParameter("timeIN" + i) != null) {
				String timeIn = request.getParameter("timeIN" + i);
				overReg.setTimeIN(timeIn);
			} else {
				overReg.setTimeIN("" + i);
			}

			if (request.getParameter("timeOut" + i) != null) {
				String sDate1 = request.getParameter("timeOut" + i);
				overReg.setTimeOut(sDate1);
			} else {
				overReg.setTimeOut("" + i);

			}
	
			
			if (request.getParameter("esiYn" + i) != null) {
				overReg.setEsiYn(request.getParameter("esiYn" + i));
			} else {
				overReg.setEsiYn("" + i);
			}
			
			
			if (request.getParameter("overTime" + i) != null) {
				overReg.setOverTime(request.getParameter("overTime" + i));
			} else {
				overReg.setOverTime("" + i);
			}
			
			
			if (request.getParameter("remarks" + i) != null) {
				overReg.setRemarks(request.getParameter("remarks" + i));
			} else {
				overReg.setRemarks("" + i);
			}
			

			if (request.getParameter("empCode" + i) != null) {
				e.setEmpCode(request.getParameter("empCode" + i));
				overReg.setEmployee(e);

			}

			overReg.setOverTimeDate(u.getOverTimeDate());
			
			System.out.println("Date >>>>>>>>>>>>>.overTime>>>>>>>>>>>>>"+u.getOverTimeDate());
			overReg.setInsBy(insertedBY);
		insertStatusMR= overtimeRegisterService.addOvertimeRegister(overReg);
			
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

		
	} catch (Exception x) {
		// TODO: handle exception
	}
		  session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/overtimeRegister";
		}
	
	
	
	@GetMapping(value = { "/deleteOverTime/{id}" })
	public String deleteOverTime(@PathVariable("id")long id , Model model, HttpSession session) {

		overtimeRegisterService.removeOverTimeRegister(id);

		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/overtimeRegister";
	}
}
