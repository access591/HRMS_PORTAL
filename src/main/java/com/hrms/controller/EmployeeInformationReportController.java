package com.hrms.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrms.EmployeeGradationExcel;
import com.hrms.ReportUtil;
import com.hrms.model.Category;
import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.model.CommonUtil;
import com.hrms.model.MenuModule;
import com.hrms.reports.EmployeeJoiningLetter;
import com.hrms.reports.EmployeeOfferLetter;
import com.hrms.service.CategoryService;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;

@Controller
public class EmployeeInformationReportController {

	int pageNo = 54;
	String reqPage = "employeeInformation";
	
	int birthAniversaryPageNo = 55;
	String birthAniversaryPage = "birthAnniversary";
	
	int joiningLetterPageNo = 56;
	String joiningLetterPage = "joining_offerLetter";
	
	@Autowired private ModuleService moduleService;
	@Autowired PageMappingService pageMappingService;
	@Autowired ReportUtil reportUtil;
	@Autowired EmployeeService employeeService;
	@Autowired EmployeeGradationExcel employeeGradationExcel;
	@Autowired CategoryService categoryService;
	@Autowired DesignationService designationService;
	@Autowired DepartmentService departmentService;
	
	
	@Autowired EmployeeOfferLetter employeeOfferLetter;
	@Autowired EmployeeJoiningLetter employeeJoiningLetter;

//	Gradation / Employee controlller
	
	@GetMapping("/employeeInformation")
	public String employeeInformation(Model model, HttpSession session) {
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<Category> listCategory = categoryService.getAllCategory();
		if(listCategory != null) {
			model.addAttribute("listCategory", listCategory);
		}

		session.setAttribute("username", session.getAttribute("username"));

		return pageMappingService.PageRequestMapping(reqPage, pageNo);
	}
	
	@ResponseBody
	@PostMapping("/employeeDetailPdf")
	public String employeeDetailPdf(@RequestParam("designation_name") String designation , Model model, 
			HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		
		System.out.println("designation is : "+ designation);
		String reportName = "EmployeeDetail";
		
		List<Employee> listEmployee = employeeService.getAllEmployees();
		//System.out.println("employee batch number : "+ listEmployee.get(0).getBatchYear());
		
		System.out.println("list employee : "+ listEmployee);
		reportUtil.allEmployeeReport(request, response, reportName, listEmployee);
		return null;
	}
	
	/*
	 * @ResponseBody
	 * 
	 * @GetMapping("employeeExcel") public ResponseEntity<InputStreamResource>
	 * empoloyeeExcelReport(HttpServletResponse response) throws IOException{
	 * 
	 * 
	 * 
	 * ByteArrayInputStream in = employeeGradationExcel.generateExcel();
	 * 
	 * HttpHeaders headers = new HttpHeaders(); headers.add("Content-Disposition",
	 * "attachment ; filename=employee.xlsx");
	 * 
	 * 
	 * return ResponseEntity.ok().headers(headers).body(new
	 * InputStreamResource(in));
	 * 
	 * 
	 * }
	 */
	
	@ResponseBody
	@GetMapping("getEmployeeByCategory/{categoryName}")
	public List<Employee> getEmployeeByCategory(@PathVariable("categoryName") String categoryCode) {
		
		System.out.println("category code : " + categoryCode);
		List<Employee> listEmployeeByCategory = null;
		if(categoryCode.equals("0")) {
			System.out.println(" hiiii");
			listEmployeeByCategory = employeeService.getAllEmployees();
		}
		else {
			listEmployeeByCategory = employeeService.getEmployeeByCategoryCode(categoryCode);
			
			
		}
		return listEmployeeByCategory;
	}
	
	@PostMapping("/createExcelsheet")
	public ResponseEntity<InputStreamResource> createExcelsheet(HttpServletRequest req, HttpServletResponse re) {
		
		
		
		
		String categoryType = req.getParameter("select_category");
		String employeeType = req.getParameter("employeeName");
		
		System.out.println("category type : " + categoryType);
		System.out.println("employee type : " + employeeType);
		
		List<Employee> listEmployee = null;
		List<Employee> listEmployee1 = new ArrayList<Employee>();
		
		 if(categoryType.equals("") || categoryType.equals(null)) {
				System.out.println("all employee and category ");
				listEmployee = employeeService.getAllEmployees();
				
				listEmployee1.addAll(listEmployee);
				
			}
		else {
				System.out.println("else block ");
				//all employee of particular department
				if(employeeType.equals("") || employeeType.equals(null)) {
					System.out.println(" alll employee by category id");
					listEmployee = employeeService.getEmployeeByCategoryCode(categoryType);
					listEmployee1.addAll(listEmployee);
					
				}
				/* only one employee of particular department */
				else {
					System.out.println("one employee");
					Employee employee = employeeService.findEmployeeById(employeeType);
					listEmployee1.add(employee);
					
				}	
			}
		 System.out.println("resources size = "+listEmployee1.size());
		ByteArrayInputStream in = employeeGradationExcel.generateExcel(listEmployee1);
		
		 
		HttpHeaders headers = new HttpHeaders(); headers.add("Content-Disposition",
		  "attachment ; filename=employee.xlsx");
		  
		  
		  return ResponseEntity.ok().headers(headers).body(new
		  InputStreamResource(in));
		
		
	}
	
	
	
	//Birth Report Controller
	
	@GetMapping("/birthAnniversary")
	public String viewBirthAniversaryUi(Model model, HttpSession session) {
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		

		session.setAttribute("username", session.getAttribute("username"));

		return "birthAnniversary";
	}
	
	@PostMapping("/createbirtAnnReport")
	public String createBirthAniversaryUi(@RequestParam("month")String month,@RequestParam("reportType")String reportType,
											Model model, HttpSession session,HttpServletRequest req,
											HttpServletResponse response) {
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		System.out.println("report type value : "+reportType);
		System.out.println("month value : "+month);
		
		List<CommonUtil> em = new ArrayList<CommonUtil>();
		CommonUtil empl;
		List<Employee> employeeList ;
		
		if(month.equals("All")) {
			employeeList = employeeService.getAllEmployees();
		}else {
			employeeList = employeeService.findByDateOfJoiningMonth( Integer.parseInt(month.toString()));
		}
		
		if(reportType.equals("B")) {
			for(int i =0 ;i<employeeList.size();i++) {
				
				Department department = departmentService.findDepartmentById(employeeList.get(i).getDepartmentCode());
				
				empl = new CommonUtil(employeeList.get(i).getEmpCode(),employeeList.get(i).getEmpName(),
						department.getDeptName(),employeeList.get(i).getMartialStatus(),employeeList.get(i).getDateOfJoining());
				em.add(empl);
			}
			System.out.println("ëm size : "+ em.size());
			String reportFileName = "birtReport";
			reportUtil.birthAnniversaryReport(req, response, reportFileName,em);
		}else if(reportType.equals("A")) {
			for(int i =0 ;i<employeeList.size();i++) {
				Department department = departmentService.findDepartmentById(employeeList.get(i).getDepartmentCode());
				
				empl = new CommonUtil(employeeList.get(i).getEmpCode(),employeeList.get(i).getEmpName(),
						department.getDeptName(),employeeList.get(i).getMartialStatus(),employeeList.get(i).getDateOfJoining());
				em.add(empl);
			}
			System.out.println("ëm size : "+ em.size());
			String reportFileName = "Anniversary";
			reportUtil.birthAnniversaryReport(req, response, reportFileName,em);
		}
		
		
		
		session.setAttribute("username", session.getAttribute("username"));

		return "birthAnniversary";
	}
	
	
//EMPLOYEE JOINING / OFFER LETTER REPORT 
	
	@GetMapping("/joining_offerLetter")
	public String employeeJoiningReport(Model model, HttpSession session) {
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<Employee> employeeList = employeeService.getAllEmployees();
		if(employeeList != null) {
			model.addAttribute("employeeList", employeeList);
		}

		session.setAttribute("username", session.getAttribute("username"));

		return "joining_offerLetter"; //joining_offerLetter.html
	}
	
	
	@PostMapping("/createJoinOfferLetter")
	public String createJoiningOfferLetter(@RequestParam("empName")String empCode,@RequestParam("reportType")String reportType,
											Model model, HttpSession session,HttpServletRequest req,
											HttpServletResponse response) throws IOException {
		
		String userCode = (String) session.getAttribute("username");

		System.out.println("report type value : "+reportType);
		System.out.println("employee code value : "+empCode); 
		
		List<CommonUtil> em = new ArrayList<CommonUtil>();
		CommonUtil empl;
		
		try {
			Employee employee = employeeService.findEmployeeById(empCode);
			
			if(employee != null) {
				Department department = departmentService.findDepartmentById(employee.getDepartmentCode());
				Designation designation = designationService.findDesignationById(employee.getDesignationCode());
				empl = new CommonUtil(employee.getEmpName(),
						department.getDeptName(),designation.getDesgName());
				em.add(empl);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String reportFileName = null;
		if(reportType.equals("J")) {
			reportFileName = "joiningLetter";
			//reportUtil.employeeJoiningLetter2(req, response, reportFileName, em);
			employeeJoiningLetter.employeeJoiningLetter(req, response, reportFileName, em);
		}
		else {
			reportFileName = "OfferLetter";
			employeeOfferLetter.employeeOfferLetter(req, response, reportFileName, em);
		}
		
		 
		
		
		session.setAttribute("username", session.getAttribute("username"));

		return null;
	}
}
