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

import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.EmpMonOvertime;
import com.hrms.model.Employee;
import com.hrms.model.MenuModule;

import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.EmpMontOvertimeRegister;
import com.hrms.util.EmpMonOvertimeUtil;



@Controller
public class MonthlyOvertimeEvaluationController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	DesignationService designationService;
	@Autowired
	DepartmentService departmentService;
	@Autowired EmpMontOvertimeRegister empMontOvertimeRegister;
	
	@GetMapping("/monthlyOvertimeEvaluation")
	public String monthlyOvertimeEvaluation(Model model, HttpSession session) {
		List<Department> listDepartment = departmentService.getAllDepartments();
		model.addAttribute("listDepartment", listDepartment);
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<EmpMonOvertime> listOverTimeR = empMontOvertimeRegister.getAllMontOvertimeRegister();
		
		List<EmpMonOvertimeUtil>listOverTimeEval= new ArrayList<EmpMonOvertimeUtil>();
		  for (int i = 0; i < listOverTimeR.size(); i++) {
			  String empCode = listOverTimeR.get(i).getEmployee().getEmpCode();
			  EmpMonOvertimeUtil ovTime=new EmpMonOvertimeUtil();
			  
			  

			  Employee employee = employeeService.findEmployeeById(empCode);
			  Department department = departmentService.findDepartmentById(employee.getDepartmentCode());
			  Designation designation = designationService.findDesignationById(employee.getDesignationCode());
			    
			  ovTime.setEmpName(employee.getEmpName());
			  ovTime.setDeptName(department.getDeptName());
			  ovTime.setDesgName(designation.getDesgName());
			  
			  ovTime.setId(listOverTimeR.get(i).getId());
			  ovTime.setoTimeMonth(listOverTimeR.get(i).getoTimeMonth());
			  ovTime.setPayableAmt(listOverTimeR.get(i).getPayableAmt());
			  listOverTimeEval.add(ovTime);
			  model.addAttribute("overRegEval",listOverTimeEval); 
			  
		  }
		return "monthlyOvertimeEvaluation";

	}
		  
	
	//@CrossOrigin
	@ResponseBody
    @GetMapping("/viewMonthOverTimeRegisterBydepartment/{id}")
    public List<EmpMonOvertimeUtil>  getLocalConvyenceById(@PathVariable(value = "id") String id,Model model,HttpSession session) {
		Department d = departmentService.findDepartmentById(id);
		List<Employee> e = employeeService.findByDepartmentCode(d.getDepartmentCode());
		List<EmpMonOvertimeUtil> lisOvertimeRegisterUtil = new ArrayList<EmpMonOvertimeUtil>();
		for (int i = 0; i < e.size(); i++) {
			String empCode = e.get(i).getEmpCode();
			EmpMonOvertimeUtil lc = new EmpMonOvertimeUtil();
			Employee employee = employeeService.findEmployeeById(empCode);
			lc.setEmpCode(employee.getEmpCode());
			lc.setEmpName(employee.getEmpName());
			lisOvertimeRegisterUtil.add(lc);
		}
		return lisOvertimeRegisterUtil;

    }
	
	@ResponseBody
    @GetMapping("/viewMonthOverTimeRegisterByEmployee/{id}")
    public EmpMonOvertimeUtil  viewOverTimeRegisterByEmployee(@PathVariable(value = "id") String id,Model model,HttpSession session) {
		// Department d=departmentService.findDepartmentById(id);
		Employee employee = employeeService.findEmployeeById(id);
		String empCode = employee.getEmpCode();
		System.out.println(">>>>>>>>xxxxxx>>>>>>>>>>>>>>>>>>" + empCode);
		EmpMonOvertimeUtil listEmp = new EmpMonOvertimeUtil();

		Designation designation = designationService.findDesignationById(employee.getDesignationCode());
		listEmp.setDesgName(designation.getDesgName());
		listEmp.setEmpCode(employee.getEmpCode());
		listEmp.setEmpName(employee.getEmpName());

		return listEmp;

    }
	
	@PostMapping("/saveOvertimeEvaluation")
	public String saveOvertimeEvaluation(@ModelAttribute("monthOverTimeRegister")EmpMonOvertimeUtil u, Model model, HttpSession session,HttpServletRequest request) throws ParseException {
		String insertedBY = (String) session.getAttribute("USER_NAME");
		EmpMonOvertime OvertimeEval = new EmpMonOvertime();
		Employee e = new Employee();
		int flag = 0;
		int counter = 1;
			try {
				boolean insertStatusMR = false;
				counter = Integer.parseInt(request.getParameter("_cr"));
				System.out.println("counter::::::::::::::::::::" + counter);
				for (int i = 0; i < counter; i++) {
					System.out.println("counter::::::::::::::::::::" + i);

					if (request.getParameter("oTimeRate" + i) != null) {
						OvertimeEval.setoTimeRate(request.getParameter("oTimeRate" + i));
					} else {
						OvertimeEval.setoTimeRate("" + i);
					}

					if (request.getParameter("status" + i) != null) {
						OvertimeEval.setStatus(request.getParameter("status" + i));
					} else {
						OvertimeEval.setStatus("" + i);
					}

					if (request.getParameter("payableAmt" + i) != null) {
						OvertimeEval.setPayableAmt(request.getParameter("payableAmt" + i));
					} else {
						OvertimeEval.setPayableAmt("" + i);
					}

					if (request.getParameter("empCode" + i) != null) {
						e.setEmpCode(request.getParameter("empCode" + i));
						OvertimeEval.setEmployee(e);

					}

					OvertimeEval.setoTimeMonth(u.getoTimeMonth());
					OvertimeEval.setInsBy(insertedBY);
					insertStatusMR = empMontOvertimeRegister.addMontOvertimeRegister(OvertimeEval);

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

return "redirect:/monthlyOvertimeEvaluation";
}
	//
	@GetMapping(value = { "/deleteMonthOverTime/{id}" })
	public String deleteMonthOverTime(@PathVariable("id")long id , Model model, HttpSession session) {
		empMontOvertimeRegister.removeMonthOverTimeRegister(id);
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/monthlyOvertimeEvaluation";
	}
	
}
