package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hrms.model.Employee;
import com.hrms.model.MedicalReimbursement;
import com.hrms.model.MedicalReimbursementUtil;
import com.hrms.model.MenuModule;
import com.hrms.service.EmployeeService;
import com.hrms.service.MedicalReimbursementService;
import com.hrms.service.ModuleService;

@Controller
public class MedicalReimbursementController {
	@Autowired
	EmployeeService employeeService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	MedicalReimbursementService medicalReimbursementService;
	@GetMapping("/medicalReimbursement")
	public String medicalReimbursement(Model model, HttpSession session) {
		
		List<Employee> listEmployee = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", listEmployee);
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		return "medicalReimbursement";
	}
	
	@PostMapping("/saveMedicalReimbursement")
	String saveLeaveDetail(@ModelAttribute("medicalReimbursement") MedicalReimbursementUtil medicalReimbursement, Model model,
			HttpSession session,HttpServletRequest request){
		int flag = 0;
		int counter = 1;
		 MedicalReimbursement m2=new MedicalReimbursement();
		 Employee emp=new Employee();
		 emp.setEmpCode(medicalReimbursement.getEmpCode());
		 m2.setEmpCode(emp);
		 m2.setDateOfSlip(medicalReimbursement.getDateOfSlip());
		 m2.setNameOfPerson(medicalReimbursement.getNameOfPerson());
		 m2.setEmpRelation(medicalReimbursement.getEmpRelation());
		 m2.setMedIndOut(medicalReimbursement.getMedIndOut());
		 m2.setTreatmentType(medicalReimbursement.getTreatmentType());
		 m2.setTreatDescription(medicalReimbursement.getTreatDescription());
		 m2.setClaimingDate(medicalReimbursement.getClaimingDate());
		 
		 //medicalReimbursementService.addMedicalReimbursement(m2);
		

		
		try {
			boolean insertStatusMR = false;
			counter = Integer.parseInt(request.getParameter("_cr"));
			System.out.println("counter::::::::::::::::::::" + counter);
			
		
			for (int i = 0; i < counter; i++) 
			{
				if (request.getParameter("i" + i) != null) {
					m2.setSrNo(request.getParameter("i" + i));
				} else {
				m2.setSrNo("" + i);
				}
				
				if (request.getParameter("medStDr" + i) != null) {
					m2.setMedStDr(request.getParameter("medStDr" + i));
				} else {
				m2.setMedStDr("" + i);
				}
				
				if(request.getParameter("caseMemoNo" + i) != null) {
					m2.setCaseMemoNo(request.getParameter("caseMemoNo" + i));
				} else {
				m2.setCaseMemoNo("" + i);
				}
				
			
				if(request.getParameter("caseMemoDate" + i) != null) {
					m2.setCaseMemoDate(request.getParameter("caseMemoDate" + i));
				} else {
				m2.setCaseMemoDate("" + i);
				}
					
				if(request.getParameter("calmlAmount" + i) != null) {
					m2.setCalmlAmount(request.getParameter("calmlAmount" + i));
				} else {
				m2.setCalmlAmount("" + i);
				}
				
				if(request.getParameter("passedAmount" + i) != null) {
					m2.setPassedAmount(request.getParameter("passedAmount" + i));
				} else {
				m2.setPassedAmount("" + i);
				}
				
				if(request.getParameter("govtexemptionAmount" + i) != null) {
					m2.setGovtexemptionAmount(request.getParameter("govtexemptionAmount" + i));
				} else {
				m2.setGovtexemptionAmount("" + i);
				}
				
				if(request.getParameter("remark" + i) != null) {
					m2.setRemark(request.getParameter("remark" + i));
				} else {
				m2.setRemark("" + i);
				}
				
				insertStatusMR= medicalReimbursementService.addMedicalReimbursement(m2);
				
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
	
		return "redirect:/medicalReimbursement";

		
	}
	
	
	
	}


