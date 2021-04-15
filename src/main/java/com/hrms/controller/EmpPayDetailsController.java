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
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrms.model.Activities;
import com.hrms.model.Department;
import com.hrms.model.Employee;
import com.hrms.model.EmployeePayDetail;
import com.hrms.model.MenuModule;
import com.hrms.model.MiscAllowance;
import com.hrms.service.DepartmentService;
import com.hrms.service.EmployeePayDetailService;
//import com.hrms.service.EmployeePayDetailService;
import com.hrms.service.EmployeeService;
import com.hrms.service.MiscAllowanceDeductionService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;

@Controller
public class EmpPayDetailsController {

	int pageno = 47;
	String reqPage = "/empPayDetail";

	@Autowired
	private ModuleService moduleService;
	@Autowired
	PageMappingService pageMappingService;
	@Autowired
	EmployeeService employeeService;

	@Autowired
	private EmployeePayDetailService empPayDetailService;

	@Autowired
	DepartmentService departmentService;
	@Autowired
	MiscAllowanceDeductionService miscAllowanceDeductionService;

	@GetMapping("/empPayDetail")
	public String empPayDetail(Model model, HttpSession session) {

		List<MiscAllowance> listMiscAllowanceDeduction = miscAllowanceDeductionService.getAllMiscAllowanceDeduction();
		model.addAttribute("listMiscAllowanceDeduction", listMiscAllowanceDeduction);
		List<Employee> listEmployee = employeeService.getAllEmployees();
		List<EmployeePayDetail> listEmpPayDetail = empPayDetailService.getAllEmployeePayDetail();
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		if (listEmployee != null) {
			model.addAttribute("listEmployee", listEmployee);
		}
		if (listEmpPayDetail != null) {
		
			model.addAttribute("listEmpPayDetail", listEmpPayDetail);
		}

		session.setAttribute("username", session.getAttribute("username"));

		return pageMappingService.PageRequestMapping(reqPage, pageno);
	}

	@PostMapping("empPayDetail/{empCode}")
	@ResponseBody
	public Employee userInfo(@PathVariable("empCode") String empCode) {

		System.out.println("user info method : " + empCode);

		return employeeService.findEmployeeById(empCode);
	}



	@PostMapping("/saveemployepaydetail")
	public String saveEmployeePaydetail(@ModelAttribute("employeePayDetail") EmployeePayDetail employeePayDetail,
			HttpSession session, Model model) {

		System.out.println("save employe pay detail " + employeePayDetail);
	
		List<EmployeePayDetail> listEmployeePayDetail = empPayDetailService.getAllEmployeePayDetail();


		if (listEmployeePayDetail != null) {
			if (!listEmployeePayDetail.contains(employeePayDetail)) {
				empPayDetailService.addEmployeePayDetail(employeePayDetail);
			} else {

			}

		}
		model.addAttribute(listEmployeePayDetail);
		System.out.println(" empoloyee pay detail data :" + employeePayDetail.getEmpName());
		

	
		
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/" + pageMappingService.PageRequestMapping(reqPage, pageno);
	}

	@GetMapping(value = { "/editEmpPayDetail/{empCode}" })
	public String editEmpPayDetail(@PathVariable("empCode") String empCode, Model model, HttpSession session) {
		System.out.println("edit emp page " + empCode);
		int editPageNo = 48;
		String reqPageedit = "/editEmpPay";

		EmployeePayDetail employePay = empPayDetailService.findEmployeePayDetaildById(empCode);
		model.addAttribute("editEmpPayDetail", employePay);

		System.out.println(" pay employee : " + employePay.getEmpName());
		return pageMappingService.PageRequestMapping(reqPageedit, editPageNo);
	}
}
