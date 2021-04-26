package com.hrms.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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
import com.hrms.model.Employee;
import com.hrms.model.MenuModule;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;

@Controller
public class EmployeeInformationReportController {

	int pageNo = 54;
	String reqPage = "employeeInformation";
	
	@Autowired private ModuleService moduleService;
	@Autowired PageMappingService pageMappingService;
	@Autowired ReportUtil reportUtil;
	@Autowired EmployeeService employeeService;
	@Autowired EmployeeGradationExcel employeeGradationExcel;
	
	
	@GetMapping("/employeeInformation")
	public String employeeInformation(Model model, HttpSession session) {
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
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
	
	@ResponseBody
	@GetMapping("employeeExcel")
	public ResponseEntity<InputStreamResource>  empoloyeeExcelReport(HttpServletResponse response) throws IOException{
		
		
		
		ByteArrayInputStream in = employeeGradationExcel.generateExcel();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment ; filename=employee.xlsx");
		
		
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
		
		
	}
	
}
